package edu.pitt.battleshipgame.common.ships;

import edu.pitt.battleshipgame.common.board.*;

public class Destroyer extends Ship {
    public static final ShipType TYPE = ShipType.DESTROYER;
    public static final int LENGTH = 2;
    public static final int MAX_ALLOWED = 1;
    public static final String NAME = "Destroyer";

    public Destroyer(Coordinate start, Coordinate end, Board board) {
        // Call the parent constructor and set the length to 5.
        super(start, end, board);
    }
    
    public Destroyer(){
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
