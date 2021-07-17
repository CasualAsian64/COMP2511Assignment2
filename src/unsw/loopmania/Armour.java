package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents an equipped or unequipped armour in the backend world
 */
public class Armour extends Equipment {
    // TODO = add more weapon/item types
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "Armour";
    }

    public int reduceAttack(int attack) {
        // TODO Auto-generated method stub
        return 0;
    }
}
