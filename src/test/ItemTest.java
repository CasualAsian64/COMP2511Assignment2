package test;

import unsw.loopmania.Shield;
import unsw.loopmania.Armour;
import unsw.loopmania.Character;
import unsw.loopmania.Helmet;
import unsw.loopmania.LoopManiaWorld;

import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    /**
     * Tests for all item types 
     * weapons: sword, stake, staff
     * equipment: armour, shield, helmet
     * consumables: health potion, one ring
     */  
 
    private static final int MAP1 = 1;

    @Test
    public void testAddUnequipItem() {
        Helper helper = new Helper();
        //  First create world based on map in helper
        LoopManiaWorld world = helper.createWorld(1);
        // Then create a character and place at a path index. For this e.g. I set it to be 0
        helper.createCharacterSetup(0, world);

        world.addUnequippedItem();

        for (int i = 0; i < 100; i++) {
            world.addUnequippedItem();
        }
    }
}
