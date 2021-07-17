package unsw.loopmania;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a Barracks building in the backend game world
 */
public class Barracks extends Building {
    public Barracks(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.type = "Barracks";
    }

    @Override
    public void performActionOnCharacter(Character character) {
        spawnAlliedSoldier(character.getAllies(), character.getPathPosition());
    }

    public void spawnAlliedSoldier(ArrayList<AlliedSoldier> allies, PathPosition currentPosition) {
        PathPosition pathPosition = new PathPosition(currentPosition.getCurrentPositionInPath(),
                currentPosition.getOrderedPath());
        AlliedSoldier newAlly = new AlliedSoldier(pathPosition);

        allies.add(newAlly);
        System.out.println("The character visited the Barracks");
        System.out.println("The number of allies the character has is " + allies.size());
    }
}
