package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Weapon extends Item {
    public Weapon(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}
