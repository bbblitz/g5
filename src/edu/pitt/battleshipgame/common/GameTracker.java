package edu.pitt.battleshipgame.common;

import java.util.ArrayList;
import java.util.List;

import edu.pitt.battleshipgame.common.board.Board;
import edu.pitt.battleshipgame.common.board.Coordinate;
import edu.pitt.battleshipgame.common.ships.Ship;

public class GameTracker {
    public static final int MAX_PLAYERS = 2;
    public final int MOVE_TIMEOUT = 30000;
    public final int PLACE_TIMEOUT = 120000;
    private int registeredPlayers = 0;
    private ArrayList<Board> gameBoards;
    private Coordinate feedback; //The last player's attack to happen
    private GameState state = GameState.INIT;
    private int playerTurn = 0;
    private long[] lastrequesttime = new long[2];
    Object lock;
    private boolean quit = false;
    private int loser = 0;
    private boolean surrender = false;
    
    public GameTracker() {
        // Exists to protect this object from direct instantiation
        lock = new Object();
        gameBoards = new ArrayList<Board>(MAX_PLAYERS);
        System.out.println("Server constructed.");
    }

    /*
     * Returns -1 if the player cannot be registered for any reason
     */
    public int registerPlayer() {
	if(registeredPlayers > MAX_PLAYERS){
		System.out.println("Attempted to register more than 2 players for a game!");
		return -1;
	}
        synchronized(lock) {
            registeredPlayers++;
            gameBoards.add(new Board("Player " + (registeredPlayers - 1) + " board"));
        }
	if(registeredPlayers == MAX_PLAYERS){
	    state=GameState.PLACEING;
	}
	lastrequesttime[registeredPlayers - 1] = System.currentTimeMillis();
        return registeredPlayers - 1;
    }

    public void wait(int playerID) {
        switch (state) {
            case INIT:
            {
                System.out.println("Player " + playerID + " is waiting for other players");
                while(registeredPlayers < MAX_PLAYERS)
		    Thread.yield();
                state = GameState.PLAYING;
		break;
            }
            case PLAYING:
            {
                while(playerTurn != playerID)
		    Thread.yield();
		break;
            }
            default:
                break;
        }
    }
    
    public List<Board> getBoards() {
        return gameBoards;
    }

    public int getTurn(){
	//This gets called continuously, so check to see if a player has taken too long here
	long delta = System.currentTimeMillis() - lastrequesttime[playerTurn];
	System.out.println("Delta time for" + playerTurn + ":" + delta);
	if(delta > MOVE_TIMEOUT){
	    System.out.println("Player " + playerTurn + "Has timed out!");
	    loser = playerTurn;
	    quit = true;
	}
	lastrequesttime[getOtherPlayerId(playerTurn)] = System.currentTimeMillis();
	return playerTurn;
    }

    //Gets the other player's ID equivalent to playerID == 1?0:1
    public int getOtherPlayerId(int playerID){
	if(playerID == 1) return 0;
	else return 1;
    }

    //TODO:complete these
    public boolean canAttack(int playerID, Coordinate c){
	//Make sure it's our turn
	if(playerTurn != playerID) return false;
	return gameBoards.get(getOtherPlayerId(playerID)).canAttack(c);
    }

    public MoveResult doAttack(int playerID, Coordinate c){
	System.out.println("Called doAttack");
	if(!canAttack(playerID,c))
	    System.out.println("Player " + playerID + " was cheating! they tried to attack where they couldn't!");
	feedback = c;
	System.out.printf("After attack was performed, player's boards are:\nPlayer 0:\n%s\nPlayer 1:\n%s",gameBoards.get(0),gameBoards.get(1));
	lastrequesttime[playerTurn] = System.currentTimeMillis();
	playerTurn = getOtherPlayerId(playerTurn);
	return gameBoards.get(getOtherPlayerId(playerID)).doAttack(c);
    }

    public boolean canPlaceShipOnBoard(int playerID, Ship s){
	System.out.println("Called canPlaceShipOnBoard");
	return gameBoards.get(playerID).canShipFit(s);
    }

    public void placeShipOnBoard(int playerID, Ship s){
	System.out.println("Called placeShipOnBoard");

	//Make sure we can place the ship
	if(!canPlaceShipOnBoard(playerID,s))
		System.out.println("Player " + playerID + " was cheating! they tried to palce a ship where they couldn't!");
	gameBoards.get(playerID).addShip(s);

	//Check if we're done adding ships to the board
	if(gameBoards.get(0).getShipList().size() == 5 && gameBoards.get(1).getShipList().size() == 5){
	    System.out.println("Setting state to playing");
	    state = GameState.PLAYING;
	}
	if(System.currentTimeMillis() - lastrequesttime[playerID] > PLACE_TIMEOUT){
	    System.out.println("player " + playerID + " took too long placeing their ships!");
	    loser = playerID;
	    quit = true;
	}
	lastrequesttime[playerID] = System.currentTimeMillis();
    }
    
    public void setBoards(ArrayList<Board> boards) {
        gameBoards = boards;
        playerTurn = (playerTurn + 1) % registeredPlayers;
	throw new IllegalArgumentException("Someone tried calling setBoards() instead of placeShipOnBoard() or doAttack()!");
    }

    public Coordinate getFeedback(){
	return feedback;
    }

    public GameState getState(){
	return state;
    }
    
    public boolean isGameOver() {
        System.out.println("Checking if the game is over...");
        if(this.quit){
            System.out.println("A player has quit. The game is over");
            return true;
        }
        for(Board board : gameBoards) {
            if(board.areAllShipsSunk()) {
                return true;
            }
        }
        return false;
    }
    
            
    public int getWinner() {
        int count = 0;
        for (Board board : gameBoards) {
            if (board.areAllShipsSunk()) {
                return count + 1;
            } else {
                count++;
            }
        }
        return -1;
    }
    
    public boolean GameOver(int player, String dummy){
        if(dummy.equalsIgnoreCase("q")){
           // state = GameState.FIN;
            this.quit = true;
            this.loser = player;
            return true;
        }
        if(dummy.equalsIgnoreCase("s")){
            this.surrender = true;
            this.loser = player;
        }
        return false;
    }
    
    public void Surrender(int player, String dummy){
        if(dummy.equalsIgnoreCase("s")){
            this.surrender = true;
            this.loser = player;
        }
    } 
    
    public boolean getQuit(){
        return this.quit;
    }
    
    public boolean getSurrender(){
        return this.surrender;
    }
    
    public int getLoser(){
        return this.loser;
    }
    public void exit(){
        System.exit(0);
    }
}
