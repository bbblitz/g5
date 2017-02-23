package edu.pitt.battleshipgame.client;

import java.util.ArrayList;
import java.util.Scanner;

import edu.pitt.battleshipgame.common.board.*;
import edu.pitt.battleshipgame.common.ships.*;
import edu.pitt.battleshipgame.common.GameInterface;
import edu.pitt.battleshipgame.common.GameTracker;

import java.net.MalformedURLException;


public class Client {
    public static GameInterface gi;
    public static int myPlayerID;
    public static ArrayList<Board> gameBoards;
    public static Scanner scan = new Scanner(System.in);
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
     * 	Init: Wait for user to enter server address
     * 						                 ^
     * 						                 |
     * 	Connecting: Wait for connection to server (connects) (timeout)
     * 	 				   	       |
     * 	 				               V
     * 	Placeing: Placeing ships, loop on placement if invalid. order:
     * 		Carrier(5),Battleship(4),Cruiser(3),Submarine(3),Destroyer(2)
     * 			|
     * 			V
     * 	Playing: Game loop
     * 			|
     * 			V
     * 	Fin: Server tells us we won, or we lost
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
        //gi = new ClientWrapper();
        myPlayerID = gi.registerPlayer();
        System.out.println("You have registered as Player " + myPlayerID);
        System.out.println("Please wait for other players to join");
        gi.wait(myPlayerID);
        System.out.println("Both Players have joined, starting the game.");
        gameBoards = gi.getBoards();
        placeShips(gameBoards.get(myPlayerID));
        System.out.println("Your board:");
        System.out.println(gameBoards.get(myPlayerID).toString(true));
        gi.setBoards(gameBoards);
        gameLoop();
    }

    public static String get_server_address(){
	Scanner sc = new Scanner(System.in);
	System.out.printf("Enter host:");
	String hostname = sc.nextLine();
	return hostname;
    }

    public static void placeShips(Board board) {
        System.out.println("Your Board:");
        System.out.println(board.toString(true));
        for(Ship.ShipType type : Ship.ShipType.values()) {
            if(type != Ship.ShipType.NONE) {
                while (true) {
                    System.out.println("Please enter a start coordinate to place your " + ShipFactory.getNameFromType(type));
                    Coordinate start = new Coordinate(scan.nextLine());
                    System.out.println("Please enter an end coordinate to place your " + ShipFactory.getNameFromType(type));
                    Coordinate end = new Coordinate(scan.nextLine());
                    // We don't need to track a reference to the ship since it will be
                    // on the board.
                    if (ShipFactory.checkForDiagonal(start, end) && ShipFactory.checkShipLength(type, start, end)){
                        ShipFactory.newShipFromType(type, start, end, board);
                        break;
                    } else {
                        System.out.println("Error placing ship. Please try again.");
                    }
                }
            }
        }
    }

    public static void gameLoop() {
        System.out.println("The game is starting!");
        do {
            // Wait for our turn
            gi.wait(myPlayerID);
            // Get the updated boards
            gameBoards = gi.getBoards();
            System.out.println("Where would you like to place your move?");
            Coordinate move = new Coordinate(scan.nextLine());
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
        } while(!gi.isGameOver());
        System.out.println("The Game is Over!");
    }
}
