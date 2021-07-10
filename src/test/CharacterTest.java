package test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.LoopManiaWorldControllerLoader;
import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;

import org.javatuples.Pair;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class CharacterTest {
    // Multiple setups for enemies, a character, buildings etc.
    public Character testCharacterSetup() throws FileNotFoundException {
        String filename = "world_with_twists_and_turns.json";
        LoopManiaWorldControllerLoader worldControllerLoader = new LoopManiaWorldControllerLoader(filename);
        // throws IO 
        LoopManiaWorld world = worldControllerLoader.load();
        List<Pair<Integer, Integer>> orderedPath = world.getOrderedPath();
        // Create a path position where the character is at the first part of the path
        PathPosition pathPosition = new PathPosition(0, orderedPath);
        Character c = new Character(pathPosition);
        System.out.println(c.getHealth());
        return c;
    }

    /**
     * Unit test
     * @throws FileNotFoundException
     */
    @Test
    public void test_character_movement() throws FileNotFoundException {
        Character c = testCharacterSetup();
        System.out.println("Hello world");
        System.out.println(c.getHealth());
    }

    /**
     * Integration test
     */
}
