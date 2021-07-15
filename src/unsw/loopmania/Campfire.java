package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a Campfire building in the backend game world
 */
public class Campfire extends Building {
    public Campfire(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "Campfire";
    }    


    @Override
    public void performActionOnCharacter(Character character) {
        amplifyDamage(character.getStats());
    }


    // only a temporary buff, will have to revert statistics back once outside of the radius
    public void amplifyDamage(Statistics s){ 


    }

    // need to figure out how to 
    public void performActionOnEnemy(Enemy enemy) {


    }
}
