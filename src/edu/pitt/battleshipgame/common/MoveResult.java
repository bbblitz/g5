/*
 * This class is used as a retrun form GameTracker.doAttack(), it tells us weather we hit, and if we did, wether we sunk anything
 */

package edu.pitt.battleshipgame.common;
import edu.pitt.battleshipgame.common.ships.Ship;
import java.io.Serializable;
public class MoveResult implements Serializable {
    public boolean hit; //True if we hit something, false otherwise

    //The ship we sunk, or null otherwise.
    //If this field is set, hit MUST be set.
    //If hit is set, this may still be null (If we hit something but did not sink it.)
    public Ship.ShipType sunk; 
    
    public MoveResult(boolean h, Ship.ShipType s){
	hit = h;
	sunk = s;
    }
}
