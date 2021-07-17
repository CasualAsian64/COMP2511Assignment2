package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped Staff in the backend world
 */
public class Staff extends Weapon {
    // TODO = add more weapon/item types
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "Staff";
    }

    public int getAttackIncrease(MovingEntity opponent) {
        return 2;
    }
}
