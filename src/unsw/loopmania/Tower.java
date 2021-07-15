package unsw.loopmania;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a Tower building in the backend game world
 */
public class Tower extends Building {
    public Tower(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "Tower";
    }    

    // Maintain a list of enemies, when the detectEnemyInRadius is called, 
    // add the enemy to the list
    // then then tower will deal damage to each enemy in the radius, potentially killing 
    // and removing the enemy from the LMW's list of enemies. 
    private ArrayList<Enemy> enemiesInRange = new ArrayList<Enemy>();



	// Use the pythagoras stuff 
	// @Override
	// public void detectEnemyInRadius() {

	// }
	

}
