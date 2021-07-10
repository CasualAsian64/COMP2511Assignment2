package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a Trap building in the backend game world
 */
public class Trap extends Building {
    public Trap(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}
