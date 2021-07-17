package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Items that can be attached to the character.
 */

public abstract class Equipment extends Item {
    public Equipment(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public String getItemType() {
        return "Equipment";
    }
    //public abstract int reduceAttack(int attack);
}