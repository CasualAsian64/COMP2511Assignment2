package unsw.loopmania;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
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

    public void performActionOnEnemy(Enemy enemy, List<Enemy> enemies) {
    }


    
}