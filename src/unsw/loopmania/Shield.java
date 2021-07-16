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
    }

    @Override
    public int reduceAttack(int attack) {
        // TODO Auto-generated method stub
        return 0;
    }    
}
