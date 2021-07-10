package unsw.loopmania;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Items that can be attached to the character.
 */

public abstract class Item extends StaticEntity {

    protected String type;

    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public String getType() {
        return type;
    }
}