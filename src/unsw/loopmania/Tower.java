package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

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


    // Pythagoras: a^2+b^2 < radius^2 to see if within radius
	// Use the pythagoras stuff 
	@Override
	public void detectEnemyInRadius(List<Enemy> enemies) {

        // Loop through all the enemies in LoopManiaWorld
        for (Enemy e: enemies){ 
            if (Math.pow((this.getX() - e.getX()), 2) + Math.pow((this.getY() - e.getY()), 2) < e.getBattleRadius()) {
                enemiesInRange.add(e);
            }
	    }

        for (Enemy e: enemiesInRange) {

            towerInflictDamage(e.getStats());

        }
    }


    public void towerInflictDamage(Statistics s) {
        
        System.out.println("Before being attacked the enemy has health " + s.getHealth());

        // deal 5 damage 
        s.setHealth(s.getHealth() - 5 ); 

        System.out.println("An enemy within the radius took damage from the tower and now has health" + s.getHealth());
    }
	

}
