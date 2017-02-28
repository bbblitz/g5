package edu.pitt.battleshipgame.common;

import edu.pitt.battleshipgame.common.board.*;
import edu.pitt.battleshipgame.common.ships.*;
import java.util.ArrayList;

public interface GameInterface {
    int registerPlayer();
    void wait(int playerID);
    ArrayList<Board> getBoards();
    void setBoards(ArrayList<Board> boards);
    void placeShipOnBoard(int playerID, Ship s);
    MoveResult doAttack(int playerID, Coordinate c);
    Coordinate getFeedback();
    int getTurn();
    GameState getState();
    boolean isGameOver();
    boolean GameOver(int player, String dummy);
    boolean getQuit();
    boolean getSurrender();
    int getLoser();
}
