package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a Tower building in the backend game world
 */
public class Tower extends Building {
    public Tower(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "Tower";
    }    
}
