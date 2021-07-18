package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents an equipped or unequipped armour in the backend world
 */
public class Armour extends Equipment {
    /**
     * constructor for Armour
     * @param x
     * @param y
     */
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "Armour";
    }

    /**
     * method to reduce attack
     * @param attack
     * @return int
     */
    public int reduceAttack(int attack) {
        return 0;
    }
}
