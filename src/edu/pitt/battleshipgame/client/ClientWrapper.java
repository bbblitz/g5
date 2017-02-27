package edu.pitt.battleshipgame.client;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.util.ArrayList;

import edu.pitt.battleshipgame.common.Serializer;
import edu.pitt.battleshipgame.common.board.*;
import edu.pitt.battleshipgame.common.ships.*;
import edu.pitt.battleshipgame.common.*;

public class ClientWrapper implements GameInterface {
    ServerInterface serverInterface = null;
    int myPlayerID;

    private static ServerInterface getServer(String host) throws MalformedURLException {
        URL url = new URL(host);
        /*try {
            url = new URL("http://localhost:9999/battleship?wsdl");
        } catch (MalformedURLException e) {
            System.err.println(e);
        }*/
        QName qname = new QName("http://server.battleshipgame.pitt.edu/", "ServerWrapperService");
        Service service = Service.create(url, qname);
        return service.getPort(ServerInterface.class);
    }
    
    public ClientWrapper(String host) throws MalformedURLException {
        serverInterface = getServer(host);
    }
    
    public int registerPlayer() {
        return serverInterface.registerPlayer();
    }
    
    public void wait(int playerID) {
        serverInterface.wait(playerID);
    }
    
    public void setBoards(ArrayList<Board> boards) {
        serverInterface.setBoards(Serializer.toByteArray(boards));
    }

    public void placeShipOnBoard(int playerID, Ship s){
	serverInterface.placeShipOnBoard(playerID,Serializer.toByteArray(s));
    }

    public MoveResult doAttack(int playerID, Coordinate c){
	return (MoveResult) Serializer.fromByteArray(serverInterface.doAttack(playerID,Serializer.toByteArray(c)));
    }
    
    /*
      Client side wrapper around the 
      @return 
     */
    public ArrayList<Board> getBoards() {
        return (ArrayList<Board>) Serializer.fromByteArray(serverInterface.getBoards());
    }
    
    public boolean isGameOver() {
        return serverInterface.isGameOver();
    }
    
    public boolean GameOver(String quit){
        return serverInterface.GameOver(quit);
    }
    
    public boolean getQuit(){
        return serverInterface.getQuit();
    }

    public Coordinate getFeedback(){
	return (Coordinate) Serializer.fromByteArray(serverInterface.getFeedback());
    }

    public int getTurn(){
	return serverInterface.getTurn();
    }

    public GameState getState(){
	return serverInterface.getState();
    }
    
    public void exit(){
        System.exit(0);
    }
}
