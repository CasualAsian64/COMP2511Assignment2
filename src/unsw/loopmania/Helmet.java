package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents an equipped or unequipped helmet in the backend world
 */
public class Helmet extends Equipment {
    // TODO = add more weapon/item types
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        stats = new Statistics(0, 0, 2, 0, 0);
        this.type = "Helmet";
    }

    @Override
    public Statistics getStats() {
        return stats;
    }

    //@Override
    //public int reduceAttack(int attack) {
     //   return attack - 3;
    //}
    
}
