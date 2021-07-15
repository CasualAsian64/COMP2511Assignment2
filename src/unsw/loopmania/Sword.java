package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */

public class Sword extends Weapon {
    // TODO = add more weapon/item types
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "Sword";
    }

    public int getAttackIncrease(Enemy enemy) {
        return 10;
    }
}
