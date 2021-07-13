package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents an equipped or unequipped shield in the backend world
 */
public class Shield extends Equipment {
    // TODO = add more weapon/item types
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "Shield";
        stats = new Statistics(0, 0, 3, 0, 0);
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
