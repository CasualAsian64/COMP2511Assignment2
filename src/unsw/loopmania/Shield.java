package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents an equipped or unequipped shield in the backend world
 */
public class Shield extends Equipment {
    /**
     * constructor for Armour
     * @param x
     * @param y
     */
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "Shield";
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
