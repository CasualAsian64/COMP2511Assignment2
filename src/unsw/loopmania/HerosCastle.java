package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a Hero's Castle building in the backend game world
 */
public class HerosCastle extends Building {
    public HerosCastle(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "HerosCastle";
    }    

    public boolean atShop(int x, int y) {
        if (this.getX() == x && this.getY() == y) {
            return true;
        }
        return false;
    }

}