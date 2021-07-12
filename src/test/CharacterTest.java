package test;

import unsw.loopmania.Character;
import unsw.loopmania.Statistics;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterTest {
    private static final int MAP1 = 1;
    private static final int MAP2 = 2;

    /**
     * Unit tests
     */

     /**
      * Have the character move around MAP 1 and assert that the character travels down the path correctly
      */
    @Test
    public void test_character_movement_MAP1(){
        Helper helper = new Helper();
        Character c = helper.testCharacterSetup(0, MAP1);
        assertEquals(c.getX(), 0);
        assertEquals(c.getY(), 0);
        c.moveDownPath();
        assertEquals(c.getX(), 1);
        assertEquals(c.getY(), 0);
        c.moveDownPath();
        assertEquals(c.getX(), 2);
        assertEquals(c.getY(), 0);
        c.moveDownPath();
        c.moveDownPath();
        c.moveDownPath();
        assertEquals(c.getX(), 4);
        assertEquals(c.getY(), 1);
    }

    /**
      * Have the character move around MAP 2 and assert that the character travels down the path correctly
      */
    @Test
    public void test_character_movement_MAP2(){
        Helper helper = new Helper();
        Character c = helper.testCharacterSetup(0, MAP2);
        assertEquals(c.getX(), 0);
        assertEquals(c.getY(), 0);
        c.moveDownPath();
        assertEquals(c.getX(), 1);
        assertEquals(c.getY(), 0);
        c.moveDownPath();
        assertEquals(c.getX(), 2);
        assertEquals(c.getY(), 0);
        c.moveDownPath();
        assertEquals(c.getX(), 2);
        assertEquals(c.getY(), 1);
        c.moveDownPath();
        assertEquals(c.getX(), 2);
        assertEquals(c.getY(), 2);
        c.moveDownPath();
        assertEquals(c.getX(), 1);
        assertEquals(c.getY(), 2);
        c.moveDownPath();
        assertEquals(c.getX(), 0);
        assertEquals(c.getY(), 2);
        c.moveDownPath();
        assertEquals(c.getX(), 0);
        assertEquals(c.getY(), 1);
        c.moveDownPath();
        assertEquals(c.getX(), 0);
        assertEquals(c.getY(), 0);
    }

    /**
      * Assert that the character's stats when using the constructor are expected values
      */
    @Test
    public void test_character_stats(){
        Helper helper = new Helper();
        Character c = helper.testCharacterSetup(0, MAP1);
        Statistics characterStats = c.getStats();
        assertEquals(characterStats.getHealth(), 100);
        assertEquals(characterStats.getAttack(), 5);
        assertEquals(characterStats.getDefense(), 2);
        assertEquals(characterStats.getGold(), 0);
        assertEquals(characterStats.getExp(), 0);
    }

    /**
     * Integration test
     */
}
