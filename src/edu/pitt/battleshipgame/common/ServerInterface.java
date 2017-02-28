package edu.pitt.battleshipgame.common;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import edu.pitt.battleshipgame.common.board.*;
import edu.pitt.battleshipgame.common.ships.*;
import edu.pitt.battleshipgame.common.*;
import java.util.ArrayList;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface ServerInterface {
    @WebMethod int registerPlayer();
    @WebMethod void placeShipOnBoard(int playerID, byte[] s);
    @WebMethod byte [] doAttack(int playerID, byte[] c);
    @WebMethod void wait(int playerID);
    @WebMethod byte [] getBoards();
    @WebMethod void setBoards(byte [] boards);
    @WebMethod byte [] getFeedback();
    @WebMethod int getTurn();
    @WebMethod GameState getState();
    @WebMethod boolean isGameOver();
    @WebMethod boolean GameOver(int player, String dummy);
    @WebMethod boolean getQuit();
    @WebMethod int getLoser();
    @WebMethod boolean getSurrender();
    
    
}
