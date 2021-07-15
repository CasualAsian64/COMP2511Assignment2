package test;

import unsw.loopmania.Slug;
import unsw.loopmania.Zombie;
import unsw.loopmania.Vampire;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyTest {
    /**
     * Tests for all enemy types (slugs, zombies, and vampires)
     */  
 
    private static final int MAP2 = 2;

    /**
     * Test slug enemy type
     */
    @Test
    public void slugMovementTest() {

        Helper helper = new Helper();
        Slug slug = helper.testSlugSetup(0, MAP2);

        assertEquals(slug.getX(), 0);
        assertEquals(slug.getY(), 0);
        slug.moveDownPath();
        assertEquals(slug.getX(), 1);
        assertEquals(slug.getY(), 0);
        slug.moveDownPath();
        assertEquals(slug.getX(), 2);
        assertEquals(slug.getY(), 0);
        slug.moveDownPath();
        assertEquals(slug.getX(), 2);
        assertEquals(slug.getY(), 1);
        slug.moveDownPath();
        assertEquals(slug.getX(), 2);
        assertEquals(slug.getY(), 2);
        slug.moveDownPath();
        assertEquals(slug.getX(), 1);
        assertEquals(slug.getY(), 2);
        slug.moveDownPath();
        assertEquals(slug.getX(), 0);
        assertEquals(slug.getY(), 2);
        slug.moveDownPath();
        assertEquals(slug.getX(), 0);
        assertEquals(slug.getY(), 1);
        slug.moveDownPath();
        assertEquals(slug.getX(), 0);
        assertEquals(slug.getY(), 0);
    }

    @Test
    public void slugBattleTest() {
        /**
         * Test slug battle range
         */
    }

    /**
     * Tests for zombie enemy type
     */
    @Test
    public void zombieMovementTest() {

        Helper helper = new Helper();
        Zombie zombie = helper.testZombieSetup(0, MAP2);

        assertEquals(zombie.getX(), 0);
        assertEquals(zombie.getY(), 0);
        zombie.moveDownPath();
        assertEquals(zombie.getX(), 1);
        assertEquals(zombie.getY(), 0);
        zombie.moveDownPath();
        assertEquals(zombie.getX(), 2);
        assertEquals(zombie.getY(), 0);
        zombie.moveDownPath();
        assertEquals(zombie.getX(), 2);
        assertEquals(zombie.getY(), 1);
        zombie.moveDownPath();
        assertEquals(zombie.getX(), 2);
        assertEquals(zombie.getY(), 2);
        zombie.moveDownPath();
        assertEquals(zombie.getX(), 1);
        assertEquals(zombie.getY(), 2);
        zombie.moveDownPath();
        assertEquals(zombie.getX(), 0);
        assertEquals(zombie.getY(), 2);
        zombie.moveDownPath();
        assertEquals(zombie.getX(), 0);
        assertEquals(zombie.getY(), 1);
        zombie.moveDownPath();
        assertEquals(zombie.getX(), 0);
        assertEquals(zombie.getY(), 0);
    }

    @Test

    public void zombieSlowerMovementTest() {
        /**
         * Zombie entities should move 1 tile after the player moves 2 tiles
         */
    
        /*
        Helper helper = new Helper();

        LoopManiaWorld world = helper.createWorld(0, MAP1);
        
        Character character = helper.testCharacterSetup(0, MAP1);
        Goals world_goals = new Goals(helper.goalCondition1());
        Zombie zombie = helper.testZombieSetup(0, MAP1);

        world.setCharacter(helper.testCharacterSetup(0, MAP1));
        world.setWorldGoals(world_goals);

        world.runTickMoves();
        */
    }

    /**
     * Tests for vampire enemy type
     */
    @Test
    public void vampireMovementTest() {

        Helper helper = new Helper();
        Vampire vampire = helper.testVampireSetup(0, MAP2);

        assertEquals(vampire.getX(), 0);
        assertEquals(vampire.getY(), 0);
        vampire.moveDownPath();
        assertEquals(vampire.getX(), 1);
        assertEquals(vampire.getY(), 0);
        vampire.moveDownPath();
        assertEquals(vampire.getX(), 2);
        assertEquals(vampire.getY(), 0);
        vampire.moveDownPath();
        assertEquals(vampire.getX(), 2);
        assertEquals(vampire.getY(), 1);
        vampire.moveDownPath();
        assertEquals(vampire.getX(), 2);
        assertEquals(vampire.getY(), 2);
        vampire.moveDownPath();
        assertEquals(vampire.getX(), 1);
        assertEquals(vampire.getY(), 2);
        vampire.moveDownPath();
        assertEquals(vampire.getX(), 0);
        assertEquals(vampire.getY(), 2);
        vampire.moveDownPath();
        assertEquals(vampire.getX(), 0);
        assertEquals(vampire.getY(), 1);
        vampire.moveDownPath();
        assertEquals(vampire.getX(), 0);
        assertEquals(vampire.getY(), 0);
    }   
}