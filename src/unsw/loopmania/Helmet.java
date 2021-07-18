package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents an equipped or unequipped helmet in the backend world
 */
public class Helmet extends Equipment {
    // TODO = add more weapon/item types
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "Helmet";
    }

    public int getIncrease() {
        return 2;
    }

}
