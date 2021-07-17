package test;

import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.Building;
import unsw.loopmania.Enemy;
import unsw.loopmania.Gold;
import unsw.loopmania.Item;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Slug;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class LoopManiaWorldTest {
    


    @Test
    public void testGold() {
        Helper helper = new Helper();
        //  First create world based on map in helper
        LoopManiaWorld world = helper.createWorld(1);
        // Then create a character and place at a path index. For this e.g. I set it to be 0
        helper.createCharacterSetup(0, world);
        
        world.runTickMoves();
    }
}