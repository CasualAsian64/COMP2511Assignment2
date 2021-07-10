package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a Village building in the backend game world
 */
public class Village extends Building {
    public Village(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}
