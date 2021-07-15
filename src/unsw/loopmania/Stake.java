package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Stake in the backend world
 */
public class Stake extends Weapon {
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "Stake";
    }
}
