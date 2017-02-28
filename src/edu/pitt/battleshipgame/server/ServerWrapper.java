package edu.pitt.battleshipgame.server;

import javax.jws.WebService;
import javax.xml.ws.WebServiceProvider;
import java.util.ArrayList;
import java.util.List;

import edu.pitt.battleshipgame.common.board.*;
import edu.pitt.battleshipgame.common.ships.*;
import edu.pitt.battleshipgame.common.Serializer;
import edu.pitt.battleshipgame.common.GameTracker;
import edu.pitt.battleshipgame.common.GameState;
import edu.pitt.battleshipgame.common.ServerInterface;

//Service Implementation
@WebService(endpointInterface = "edu.pitt.battleshipgame.common.ServerInterface")
/**
 * This Wrapper exists to translate network requests to API compatible requests
 */
public class ServerWrapper implements ServerInterface {
    // We have a pseudo singleton around the Server object.
    private static GameTracker tracker = null;

    public ServerWrapper() {
        tracker = getInstance();
    }
    
    public static GameTracker getInstance() {
        if(tracker == null) {
            tracker = new GameTracker();
        }
        return tracker;
    }

    /*
     * @see Server.registerPlayer
     * 
     * @return The id of the registered player.
     */
    @Override
    public int registerPlayer() {
        return tracker.registerPlayer();
    }

    /*
     * @see Server.waitForPlayers
     * 
     * @param playerID The ID of the player that is waiting.
     */
    @Override
    public void wait(int playerID) {
        tracker.wait(playerID);
    }
    
    /*
     * The Network version of @see Server.getBoards will convert the array list
     * to a byte array.
     * 
     * @return The serialized version of the boards array. 
     */
    @Override
    public byte [] getBoards() {
        return Serializer.toByteArray(new ArrayList<Board>(tracker.getBoards()));
    }
    
    /*
     * The Network version of @see Server.registerBoard. It will convert the
     * byte [] board to a Board object to be passed to the Server.
     * 
     * @param playerID The ID of the player registering a board.
     * 
     * @param board The serialized representation of the board the player wants
     *              to register.
     */
    @Override
    public void setBoards(byte [] boards) {
        tracker.setBoards((ArrayList<Board>)Serializer.fromByteArray(boards));
    }

    @Override
    public void placeShipOnBoard(int playerID, byte[] sh){
	Ship s = (Ship) Serializer.fromByteArray(sh);
	tracker.placeShipOnBoard(playerID,s);
    }
    @Override
    public byte[] doAttack(int playerID, byte[] co){
	Coordinate c = (Coordinate) Serializer.fromByteArray(co);
	return Serializer.toByteArray(tracker.doAttack(playerID,c));
    }
    
    @Override
    public byte[] getFeedback(){
	return Serializer.toByteArray(tracker.getFeedback());
    }

    @Override
    public GameState getState(){
	return tracker.getState();
    }

    @Override
    public int getTurn(){
	return tracker.getTurn();
    }

    public boolean isGameOver(){
        return tracker.isGameOver();
    }
    
    public int getWinner(){
        return tracker.getWinner();
    }
    
    public boolean GameOver(String quit){
        return tracker.GameOver(quit);
    }
    
    public boolean getQuit(){
        return tracker.getQuit();
    }
    
    
    
    public void exit(){
        System.exit(0);
    }
}
