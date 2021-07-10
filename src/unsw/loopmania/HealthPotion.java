package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class HealthPotion extends Consumable {
    // TODO 
    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "HealthPotion";
    }    
}
