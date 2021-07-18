package unsw.loopmania;

import java.util.ArrayList;

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
        character.incrementAlliesNumValueProperty();
    }

    public void spawnAlliedSoldier(ArrayList<AlliedSoldier> allies, PathPosition currentPosition) {
        PathPosition position = new PathPosition(currentPosition.getCurrentPositionInPath(),
                currentPosition.getOrderedPath());
        AlliedSoldier newAlly = new AlliedSoldier(position);
        allies.add(newAlly);
    }
}