package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a Barracks building in the backend game world
 */
public class Barracks extends Building {
    public Barracks(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}
