package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Character;
import unsw.loopmania.Enemy;
import unsw.loopmania.Goals;

public class BattleTest {
    @Test
    public void basicBattleWithSlug() {
        Helper helper = new Helper();
        //  First create world based on map in helper
        LoopManiaWorld world = helper.createWorld(1);
        // Then create a character and place at a path index. For this e.g. I set it to be 0
        Character character = helper.createCharacterSetup(0, world);
        // Create a enemy and place at a path index. For this e.g. I set it to be 1 and 0 is the enemy selector which selects slug
        Enemy enemy = helper.createEnemySetup(0, 1, world);
        Goals goals = helper.createGoalsSetup(1, world);
        // Grab list of enemies from defeated enemies
        List<Enemy> enemies = world.runBattles();
        assertEquals(1, enemies.size());
        assertEquals(enemies.get(0).getType(), "Slug");
        world.runTickMoves();
    }
}
