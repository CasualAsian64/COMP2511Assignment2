package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a Village building in the backend game world
 */
public class Village extends Building {
    public Village(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    

    // function that every "step-on" building can call if stepped on
    @Override
    public void performAction(Character character) {
        healCharacter(character.getStats());
    }

    public void healCharacter(Statistics stats) {
        

        System.out.println("The characters health before healing is " + stats.getHealth());

        // if heal amount is greater than 100, set health to 100 (max health)
        if (stats.getHealth() >= 90) {
            stats.setHealth(100);
        }

        else {
            // Heal the character for 10 points
            stats.setHealth(stats.getHealth() + 10);
        }    

        System.out.println("The characters health after healing is " + stats.getHealth());
    }
}
