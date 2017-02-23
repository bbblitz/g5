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
                checkShipLength(4, start, end);
                return new Battleship(start, end, board);
            case CARRIER:
                checkShipLength(5, start, end);
                return new Carrier(start, end, board);
            case CRUISER:
                checkShipLength(3, start, end);
                return new Cruiser(start, end, board);
            case SUBMARINE:
                checkShipLength(3, start, end);
                return new Submarine(start, end, board);
            case DESTROYER:
                checkShipLength(2, start, end);
                return new Destroyer(start, end, board);
            default:
                throw new IllegalArgumentException(type + " does not identify a valid ShipType.");
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
    
    public static void checkShipLength(int length, Coordinate start, Coordinate end) {
        if (start.getCol() == end.getCol() && (end.getRow() - start.getRow()) != length)
            throw new IllegalArgumentException("Ship length must be " + length);
        else if (start.getRow() == end.getRow() && (end.getCol() - start.getCol()) != length)
            throw new IllegalArgumentException("Ship length must be " + length);
    }
}