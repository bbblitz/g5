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
    int getWinner();
    GameState getState();
    boolean isGameOver();
    boolean GameOver(String dummy);
    boolean getQuit();
}
