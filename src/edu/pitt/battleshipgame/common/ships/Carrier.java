package edu.pitt.battleshipgame.common.ships;

import edu.pitt.battleshipgame.common.board.*;

import java.io.Serializable;
public class Carrier extends Ship implements Serializable{
    public static final ShipType TYPE = ShipType.CARRIER;
    public static final int LENGTH = 5;
    public static final int MAX_ALLOWED = 1;
    public static final String NAME = "Carrier";

    public Carrier (Coordinate start, Coordinate end, Board board) {
        // Call the parent constructor and set the length to 5.
        super(start, end, board);
    }

    public Carrier (){
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
