package edu.pitt.battleshipgame.common.board;

import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;

import edu.pitt.battleshipgame.common.ships.Ship;
import edu.pitt.battleshipgame.common.MoveResult;

public class Board implements Serializable {
    public static final int BOARD_DIM = 10;
    // We could track a Ship-Bool pair but it is just as easy to have
    // two arrays. The Ships array will keep track of the ships on the
    // game board. The moves array will be initialized to false and will
    // change to true when that move is made.
    //private ArrayList < ArrayList < Ship > > theShips;
    private Ship [][] theShips;
    private boolean [][] moves;

    //Only use this on the client side to keep track of hits on your enemy's board.
    private boolean [][] hits;
    // Keep a list of all ships on this board for quick searching.
    LinkedList<Ship> shipList;
    private String name;

    public Board (String _name) {
        theShips = new Ship[BOARD_DIM][BOARD_DIM];
        moves = new boolean[BOARD_DIM][BOARD_DIM];
	hits = new boolean[BOARD_DIM][BOARD_DIM];
        shipList = new LinkedList<Ship>();
        name = _name;
    }
    
    public String getName() {
        return name;
    }

    public void applyHitMarker(Coordinate c){
	moves[c.getRow()][c.getCol()] = true;
	hits[c.getRow()][c.getCol()] = true;
    }

    public void applyMissMarker(Coordinate c){
	moves[c.getRow()][c.getCol()] = true;
    }
    
    public void addShip(Ship ship) {
        if (!canShipFit(ship)) {
            throw new IllegalArgumentException("This board already has the maximum amount of: " + ship.getName());
        }
        for (Coordinate coord : ship.getCoordinates()){
            theShips[coord.getRow()][coord.getCol()] = ship;
        }
        shipList.add(ship);
    }

    //Checks if an attack is reasonable
    public boolean canAttack(Coordinate c){
	//Make sure we havn't already attacked this spot
	if(moves[c.getRow()][c.getCol()]) return false;
	return true;
    }

   
    //Performs an attack, returns wether or not we hit
    public MoveResult doAttack(Coordinate c){
	moves[c.getRow()][c.getCol()] = true;
	Ship s = theShips[c.getRow()][c.getCol()];
	if(s != null){
	    s.registerHit();
	    if(s.isSunk())
		return new MoveResult(true,s.getType());
	    else
		return new MoveResult(true,null);
	}
	return new MoveResult(false,null);
    }

    public Ship makeMove(Coordinate move) {
        moves[move.getRow()][move.getCol()] = true;
        Ship ship = theShips[move.getRow()][move.getCol()];
        if(ship != null) {
            ship.registerHit();
        }
        return ship;
    }
    
    public boolean canShipFit(Ship ship) {
	//Make sure the ship is not already on the board
	for(Ship s : shipList)
	    if(s.getClass() == ship.getClass())
		return false;

	//Make sure there is not already a shit blocking our path
	for(Coordinate c : ship.getCoordinates())
	    if(theShips[c.getRow()][c.getCol()] != null)
		return false;
	
	//If we've made it to here, we're good to go!
	return true;
    }
    
    public List<Ship> getShipList() {
        return shipList;
    }
    
    public boolean areAllShipsSunk() {
        for (Ship s : shipList) {
            if (! s.isSunk()) {
                return false;
            }
        }
        return true;
    }
    
    public String toString() {
        return toString(false);
    }
    
    public String toString(boolean showShips) {
        StringBuilder sb = new StringBuilder();
        // Buld an intermediate representation of the board as a character array
        char [][] boardRepresentation = new char[BOARD_DIM+1][BOARD_DIM+1];
        boardRepresentation[0][0] = '+';
        for (int row = 1; row < BOARD_DIM+1; row++) {
            // The first column will be filled with the row labels
            boardRepresentation[row][0] = Integer.toString(row).charAt(0);
        }
        for (int col = 1; col < BOARD_DIM+1; col++) {
            boardRepresentation[0][col] = Coordinate.reverseColumnLookup(col-1);
        }
        for (int row = 0; row < BOARD_DIM; row++) {
            for (int col = 0; col < BOARD_DIM; col++) {
                if (moves[row][col]) {
                    if (theShips[row][col] != null || hits[row][col]) {
                        boardRepresentation[row+1][col+1] = 'X';
                    } else {
                        boardRepresentation[row+1][col+1] = 'O';
                    }
                }
		else if (showShips && theShips[row][col] != null) {
                    boardRepresentation[row+1][col+1] = 'S';
                }
                else{
                    boardRepresentation[row+1][col+1] = ' ';
                }
            }
        }
        for (char [] row : boardRepresentation) {
            sb.append(row);
            sb.append('\n');
        }
        return sb.toString();
    }
}
