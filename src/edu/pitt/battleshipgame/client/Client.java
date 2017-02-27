package edu.pitt.battleshipgame.client;

import java.util.ArrayList;
import java.util.Scanner;

import edu.pitt.battleshipgame.common.board.*;
import edu.pitt.battleshipgame.common.ships.*;
import edu.pitt.battleshipgame.common.*;

import java.net.MalformedURLException;


public class Client {
    public static GameInterface gi;
    public static int myPlayerID;
    //public static ArrayList<Board> gameBoards;
    public static Board myboard;
    public static Board enemyboard;
    public static Scanner scan = new Scanner(System.in);
    public static final int MAX_PLAYERS = 2;
    public enum Client_State {
        S_INIT,
        S_CONNECTING,
        S_PLACEING,
        S_PLAYING,
        S_FIN
    }
    public static Client_State state = Client_State.S_INIT;
    /*
     * States:
     *         Init: Wait for user to enter server address
     *                                                                  ^
     *                                                                  |
     *         Connecting: Wait for connection to server (connects) (timeout)
     *                                                            |
     *                                                         V
     *         Placeing: Placeing ships, loop on placement if invalid. order:
     *                 Carrier(5),Battleship(4),Cruiser(3),Submarine(3),Destroyer(2)
     *                         |
     *                         V
     *         Waiting: Waiting for the other player to finish placeing their ships
     *                         |
     *                         V
     *         Playing: Game loop
     *                         |
     *                         V
     *         Fin: Server tells us we won, or we lost
     */
    public static void main(String [] args) {
        //Connect to the server
        while(state == Client_State.S_INIT){
                String host = get_server_address();
                state = Client_State.S_CONNECTING;
                try{
                        gi = new ClientWrapper(host);
                        state = Client_State.S_PLACEING;
                }catch(Exception e) {
                        System.out.printf("Could not connect to server! Error:\n\t%s\nTry again!\n",e.toString());
                        state = Client_State.S_INIT;
                }
        }
        myPlayerID = gi.registerPlayer();
        //+1 to show as "Player 1" or "Player 2" instead of 0 and 1
        System.out.println("You have registered as Player " + myPlayerID + 1);
        System.out.println("Please wait for other players to join");
        while(gi.getState() == GameState.CONNECTING)
            Thread.yield();
        //gi.wait(myPlayerID);
        System.out.println("Both Players have joined, starting the game.");
        //Instance our gameboards
        myboard = new Board("");
        enemyboard = new Board("");
        //gameBoards = gi.getBoards();
        placeShips(myboard);
        System.out.println("You're done placeing your ships, please wait for the other player to finish placeing their ships.");
        System.out.println("Your board:");
        System.out.println(myboard.toString(true));
        //gi.setBoards(gameBoards);
        while(gi.getState() == GameState.PLACEING)
            Thread.yield();
        gameLoop();
    }

    public static String get_server_address(){
        Scanner sc = new Scanner(System.in);
        System.out.printf("Enter host:");
        String hostname = sc.nextLine();
        return hostname;
    }

    private static Coordinate getcoord(String prompt){
        System.out.println(prompt);
        while(true){
            try{
                String dummy = scan.nextLine();
                if(dummy.equalsIgnoreCase("quit")){
                }
                gi.GameOver(dummy);
                if(gi.getQuit()){
                    System.out.println("A player has quit the game. The game is over");
                    System.exit(0);
                }
                return new Coordinate(dummy);
            }catch(Exception e){
                System.out.printf("Unaccpetable coordinate!\n%s\n",prompt);
            }
        }
    }

    //Adds a single ship to the board
    private static void placeship(Ship.ShipType t, Board b){
    if(t == Ship.ShipType.NONE) return;
        while(true){
            Coordinate start = getcoord("Please enter a start coordinate to place your " + ShipFactory.getNameFromType(t));
            Coordinate end = getcoord("Please enter an end coordinate to place your " + ShipFactory.getNameFromType(t));
            //Make sure that the ship is the correct length and not diagonal
            Ship s = null;
            try{
                s = ShipFactory.newShipFromType(t,start,end,b);
            }catch(IllegalArgumentException e){
                System.out.println("You can't make a ship like that!");
                continue;
            }
            //Make sure it's not overlapping another ship
            if(b.canShipFit(s)){
                b.addShip(s);
                gi.placeShipOnBoard(myPlayerID,s);
                break;
            }else{
                System.out.println("That is an unacceptable place to put your " + ShipFactory.getNameFromType(t));
            continue;
            }
        }
    }

    public static void placeShips(Board board) {
        System.out.println("Your Board:");
        System.out.println(board.toString(true));
        for(Ship.ShipType type : Ship.ShipType.values()) {
            placeship(type,board);
            System.out.println(board.toString(true));
        }
    }

    public static void gameLoop() {
        System.out.println("The game is starting!");
        do {
            // Wait for our turn
            while(gi.getTurn() != myPlayerID){
                if(gi.getQuit()){
                System.out.println("A player has quit the game. The game is over.");
                System.exit(0);
                }
                Thread.yield();
            }
            //Find where the other player moved
            Coordinate om = gi.getFeedback();

            
            //And apply it to our board
            if(om != null) //This is false the first attack.
                    myboard.doAttack(om);

            // Get the updated boards

            //gameBoards = gi.getBoards();
            Coordinate move = getcoord("Where would you like to place your move?");
            
            while (!enemyboard.canAttack(move)) {
                move = getcoord("You can't attack there. Try again.");
            }
            MoveResult r = gi.doAttack(myPlayerID + 1, move);
            if(r.hit){
                enemyboard.applyHitMarker(move);
                System.out.printf("Hit! ");
                if(r.sunk != null){
                    System.out.printf("You sunk the other player's " + ShipFactory.getNameFromType(r.sunk) + "!\n");
                }
            }else{
                enemyboard.applyMissMarker(move);
                System.out.println("Miss!");
            }
            System.out.printf("Your board:\n%s\nEnemy board:\n%s\n",myboard.toString(true),enemyboard.toString(true));
            //System.out.println(r);
            /*
            Ship ship = gameBoards.get((myPlayerID + 1) % GameTracker.MAX_PLAYERS).makeMove(move);
            if(ship == null) {
                System.out.println("Miss");
            } else if (ship.isSunk()) {
                System.out.println("You sunk " + ship.getName());
            } else {
                System.out.println("Hit");
            }
            // Send the updated boards.
            gi.setBoards(gameBoards);
            */
            if(gi.getQuit()){
                System.out.println("A player has quit the game. The game is over.");
                System.exit(0);
            }
        } while(!gi.isGameOver());
        System.out.println("The Game is Over!");
    }
}
