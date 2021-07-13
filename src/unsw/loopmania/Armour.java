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
        stats = new Statistics(0, 0, 5, 0, 0);
    }

    @Override
    public Statistics getStats() {
        return stats;
    }

    @Override
    public int reduceAttack(int attack) {
        return 0;
    }
}
