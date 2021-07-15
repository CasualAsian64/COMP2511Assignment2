package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Buildings that can be placed on the map.
 */

public abstract class Building extends StaticEntity {

    protected String type; 

    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public String getType() {
        return type;
    }

    

    public void performActionOnCharacter(Character character) {
    }

    public boolean performActionOnEnemy(Enemy enemy) {
        return false;
    }

    public void detectEnemyInRadius(List<Enemy> enemies){

    }
    
}