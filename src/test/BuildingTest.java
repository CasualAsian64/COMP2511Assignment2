package test;

import unsw.loopmania.Character;
import unsw.loopmania.Barracks;
import unsw.loopmania.HerosCastle;
import unsw.loopmania.Slug;
import unsw.loopmania.Trap;
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
     * Test if barracks spawn allies
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

    /**
     * Test if hero'sCastle detects if player is ontop of it
     */
    @Test
    public void herosCastleShopTest() {
        Helper helper = new Helper();
        Character character = helper.testCharacterSetup(0, MAP1);
        
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        HerosCastle herosCastle = new HerosCastle(x, y);

        // as player spawns ontop of the hero's castle, assert true
        assertEquals(herosCastle.atShop(character.getX(), character.getY()), true);

        character.moveDownPath();

        // as player is moving away from hero's castle, assert false
        assertEquals(herosCastle.atShop(character.getX(), character.getY()), false);
    }

    /**
     * Test if trap inflicts damage on enemy
     */
    @Test
    public void trapTest() {
        // create enemies and the trap
        Helper helper = new Helper();
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Trap trap = new Trap(x, y);

        Slug slug = helper.testSlugSetup(0, MAP1);
        Vampire vampire = helper.testVampireSetup(0, MAP1);

        // as trap kills slugs, assert true
        assertEquals(trap.inflictDamage(slug, slug.getStats()), true);

        // as vampires survive the trap, assert false and the remaining health should be 25
        assertEquals(vampire.getHealth(), 50);
        assertEquals(trap.inflictDamage(vampire, vampire.getStats()), false);
        assertEquals(vampire.getHealth(), 25);
    }
}
