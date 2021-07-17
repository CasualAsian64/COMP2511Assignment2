package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Items that can be attached to the character.
 */

public abstract class Item extends StaticEntity {

    protected String type;
    Statistics stats;

    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public String getType() {
        return type;
    }

    public Statistics getStats() {
        return this.getStats();
    }
    
    public int getAttack(){
        Statistics stats = this.getStats();
        return stats.getAttack();
    }
    
    public int getDefense(){
        Statistics stats = this.getStats();
        return stats.getDefense();
    }

    public abstract String getItemType();
}