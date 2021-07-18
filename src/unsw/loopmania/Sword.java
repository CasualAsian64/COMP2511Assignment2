package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */

public class Sword extends Weapon {
    /**
     * constructor for Staff
     * @param x
     * @param y
     */
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "Sword";
    }

    /**
     * getter for AttackIncrease
     */
    public int getAttackIncrease(Enemy enemy) {
        return 10;
    }
}

