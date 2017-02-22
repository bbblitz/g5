package edu.pitt.battleshipgame.common.ships;

import edu.pitt.battleshipgame.common.board.*;

public class Battleship extends Ship {
    public static final ShipType TYPE = ShipType.BATTLESHIP;
    public static final int LENGTH = 4;
    public static final int MAX_ALLOWED = 1;
    public static final String NAME = "Battleship";

    public Battleship(Coordinate start, Coordinate end, Board board) {
        // Call the parent constructor and set the length to 5.
        super(start, end, board);
    }

    public Battleship(){
	super();
    }
    
    @Override
    public final int getLength() {
        return LENGTH;
    }
    
    @Override
    public int maxAllowed() {
        return MAX_ALLOWED;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
    
    @Override
    public ShipType getType() {
        return TYPE;
    }
}
