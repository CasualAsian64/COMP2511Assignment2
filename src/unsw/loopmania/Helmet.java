package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents an equipped or unequipped helmet in the backend world
 */
public class Helmet extends Equipment {
    /**
     * constructor for Helmet
     * @param x
     * @param y
     */
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        stats = new Statistics(0, 0, 2, 0, 0);
        this.type = "Helmet";
    }

    /**
     * getter for statistics
     */
    @Override
    public Statistics getStats() {
        return stats;
    }
}
