package unsw.loopmania;

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



    @Override
    public boolean performActionOnEnemy(Enemy e) {
        return towerInflictDamage(e.getStats());
    }


    public boolean towerInflictDamage(Statistics s) {
        
        System.out.println("Before being attacked by the tower, the enemy has health " + s.getHealth());

        // Killed the enemy 
        if (s.getHealth() - 5  <= 0) {

            System.out.println("The tower killed the enemy!");
            return true; 
        }


        // else deal 5 damage 
        else { 
            s.setHealth(s.getHealth() - 5 ); 
    
            System.out.println("An enemy within the radius took damage from the tower and now has health " + s.getHealth());

            return false;
        }
    }
	

}
