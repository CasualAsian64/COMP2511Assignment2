package test;

import unsw.loopmania.Enemy;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Shield;
import unsw.loopmania.Armour;
import unsw.loopmania.Barracks;
import unsw.loopmania.Character;
import unsw.loopmania.Statistics;
import unsw.loopmania.Goals;
import unsw.loopmania.Helmet;
import unsw.loopmania.Slug;
import unsw.loopmania.Zombie;
import unsw.loopmania.Vampire;

import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuildingTest {
    /**
     * Tests for all building types (barracks, campfire, heroscastle, tower, trap, vampirecastle, village, zombiepit)
     */  
 
    private static final int MAP1 = 1;

    /**
     * Test barracks
     */
    @Test
    public void barracksSpawnAllyTest() {
        Helper helper = new Helper();
        Character character = helper.testCharacterSetup(0, MAP1);
        
        // assert character has no current allies
        assertEquals(character.getAllies().size(), 0);

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Barracks barracks = new Barracks(x, y);
        barracks.spawnAlliedSoldier(character.getAllies());

        // assert character has one new ally
        assertEquals(character.getAllies().size(), 1);

        // spawn ally using performActionOnCharacter method
        barracks.performActionOnCharacter(character);

        // assert character has two allies
        assertEquals(character.getAllies().size(), 2);
    }
}
