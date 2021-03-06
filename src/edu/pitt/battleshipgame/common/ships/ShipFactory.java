package edu.pitt.battleshipgame.common.ships;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import edu.pitt.battleshipgame.common.ships.*;
import edu.pitt.battleshipgame.common.board.*;

public abstract class ShipFactory {
    public static Ship newShipFromType(Ship.ShipType type, Coordinate start, Coordinate end, Board board) {
        switch (type) {
            case BATTLESHIP:
                return new Battleship(start, end, board);
            case CARRIER:
                return new Carrier(start, end, board);
            case CRUISER:
                return new Cruiser(start, end, board);
            case SUBMARINE:
                return new Submarine(start, end, board);
            case DESTROYER:
                return new Destroyer(start, end, board);
            default:
                throw new IllegalArgumentException(type + " does not identify a valid ShipType.");
        }
    }

    public static Ship newShipFromType(Ship.ShipType t){
	switch(t){
	    case BATTLESHIP:
		return new Battleship();
	    case CARRIER:
		return new Carrier();
	    case CRUISER:
		return new Cruiser();
	    case SUBMARINE:
		return new Submarine();
	    case DESTROYER:
		return new Destroyer();
	    default:
		throw new IllegalArgumentException(t + " does not identify a valid ship type.");
	}
    }

    public static int maxAllowedFromType(Ship.ShipType type) {
        switch (type) {
            case BATTLESHIP:
                return Battleship.MAX_ALLOWED;
            case CARRIER:
                return Carrier.MAX_ALLOWED;
            case CRUISER:
                return Cruiser.MAX_ALLOWED;
            case SUBMARINE:
                return Submarine.MAX_ALLOWED;
            case DESTROYER:
                return Destroyer.MAX_ALLOWED;
            default:
                throw new IllegalArgumentException(type + " does not identify a valid ShipType.");
        }
    }
    
    public static String getNameFromType(Ship.ShipType type) {
        switch (type) {
            case BATTLESHIP:
                return Battleship.NAME;
            case CARRIER:
                return Carrier.NAME;
            case CRUISER:
                return Cruiser.NAME;
            case SUBMARINE:
                return Submarine.NAME;
            case DESTROYER:
                return Destroyer.NAME;
            default:
                throw new IllegalArgumentException(type + " does not identify a valid ShipType.");
        }
    }

    public static boolean checkShipLength(Ship.ShipType type, Coordinate start, Coordinate end) {
        int length = 0;
        switch (type) {
            case BATTLESHIP:
                length = 4;
                break;
            case CARRIER:
                length = 5;
                break;
            case CRUISER:
                length = 3;
                break;
            case SUBMARINE:
                length = 3;
                break;
            case DESTROYER:
                length = 2;
                break;
            default:
                throw new IllegalArgumentException(type + " does not identify a valid ShipType.");  
        }
        
        if (start.getCol() == end.getCol() && (end.getRow() - start.getRow()) != length-1)
            return false;
        else if (start.getRow() == end.getRow() && (end.getCol() - start.getCol()) != length-1)
            return false;
        
        return true;
    }
    
    public static boolean checkForDiagonal(Coordinate start, Coordinate end) {
        if (start.getCol() != end.getCol() && start.getRow() != end.getRow())
            return false;
        else
            return true;
    }
}
