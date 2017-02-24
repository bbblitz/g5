package edu.pitt.battleshipgame.common.ships;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.lang.Math.*;
import edu.pitt.battleshipgame.common.board.*;

public abstract class Ship implements Serializable {
    public enum ShipType {
        CARRIER,
        BATTLESHIP,
        CRUISER,
        SUBMARINE,
        DESTROYER,
        NONE        //This is a special ShipType that cannot be instantiated.
    }
    
    /**
     * Base class should provide
     * public static final int LENGTH
     * public static final int MAX_ALLOWED
     */
    
    private int hitCount;
    // Keep a backreference to the board that this ship is placed on
    private Board myBoard = null;
    Coordinate start, end;
    
    public Ship(Coordinate start, Coordinate end, Board board) {
	//Make sure the ship is either vertical or horizontal, not diagonal
	if(!(start.getCol() == end.getCol() || start.getRow() == end.getRow()))
		throw new IllegalArgumentException("Ship must be placed either vertically or horizontally");
	//Make sure the ship is the correct length, 
	//adding 1 is needed because the board start from 1, not 0
	if(end.getRow() - start.getRow() + 1 != getLength() && end.getCol() - start.getCol() + 1 != getLength())
		throw new IllegalArgumentException("This ship must be " + getLength() + " blocks long");
        this.start = start;
        this.end = end;
        addBoard(board);
    }

    public Ship(){
	this.start = null;
	this.end = null;
	this.myBoard = null;
    }

    public void SetStartEnd(Coordinate s, Coordinate e){
	this.start = s;
	this.end = e;
    }
    
    public List<Coordinate> getCoordinates() {
        LinkedList coordinates = new LinkedList<Coordinate>();
        if (start.getRow() == end.getRow()) {
            // This ship is oriented column wise
            for (int i = start.getCol(); i <= end.getCol(); i++) {
                coordinates.add(new Coordinate(start.getRow(),i));
            }
        } else {
            // This ship is oriented length wise
            for (int i = start.getRow(); i <= end.getRow(); i++) {
                coordinates.add(new Coordinate(i, start.getCol()));
            }
        }
        return coordinates;
    }
    
    public boolean isSunk() {
        return (hitCount == getLength());
    }
    
    public void addBoard(Board board) {
        if (myBoard == null) {
            myBoard = board;
        } else {
            throw new IllegalArgumentException("This ship is already placed on a board: " + myBoard.getName());
        }
        //board.addShip(this);
    }
    
    public void registerHit() {
        hitCount++;
    }
    
    /*
      Get the length of this ship instance.
      @return 
     */
    public abstract int getLength();
    
    /*
      Get the maximum amount of ships of this type allowed. This function is
      only here to "force" the base class to have a
      public static final int MAX_ALLOWED.
      @return 
     */
    public abstract int maxAllowed();

    /*
      Get the name of the Ship.
     */
    public abstract String getName();
    
    public abstract ShipType getType();
}
