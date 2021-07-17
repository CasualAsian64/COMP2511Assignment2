package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Building;
import unsw.loopmania.Character;
import unsw.loopmania.Enemy;
import unsw.loopmania.Goals;

public class BattleTest {

    private static final int SLUG = 0;
    private static final int ZOMBIE = 1;
    private static final int VAMPIRE = 2;



    @Test
    public void basicBattleWithSlug() {
        Helper helper = new Helper();
        //  First create world based on map in helper
        LoopManiaWorld world = helper.createWorld(1);
        // Then create a character and place at a path index. For this e.g. I set it to be 0
        helper.createCharacterSetup(0, world);
        // Create a enemy and place at a path index. For this e.g. I set it to be 1 and 0 is the enemy selector which selects slug
        helper.createEnemySetup(SLUG, 1, world);
        helper.createGoalsSetup(1, world);
        // Grab list of enemies from defeated enemies
        List<Enemy> enemies = world.runBattles();
        assertEquals(1, enemies.size());
        assertEquals(enemies.get(0).getType(), "Slug");
        world.runTickMoves();
    }                   

    @Test
    public void basicBattleWithZombie() {
        Helper helper = new Helper();
        //  First create world based on map in helper
        LoopManiaWorld world = helper.createWorld(1);
        // Then create a character and place at a path index. For this e.g. I set it to be 0
        helper.createCharacterSetup(0, world);
        // Create a enemy and place at a path index. For this e.g. I set it to be 1 and 0 is the enemy selector which selects slug
        helper.createEnemySetup(ZOMBIE, 1, world);
        helper.createGoalsSetup(1, world);
        // Grab list of enemies from defeated enemies
        List<Enemy> enemies = world.runBattles();
        assertEquals(1, enemies.size());
        assertEquals(enemies.get(0).getType(), "Zombie");
        world.runTickMoves();
    }

    @Test
    public void basicBattleWithVampire() {
        Helper helper = new Helper();
        //  First create world based on map in helper
        LoopManiaWorld world = helper.createWorld(1);
        // Then create a character and place at a path index. For this e.g. I set it to be 0
        helper.createCharacterSetup(0, world);
        // Create a enemy and place at a path index. For this e.g. I set it to be 1 and 0 is the enemy selector which selects slug
        helper.createEnemySetup(VAMPIRE, 1, world);
        helper.createGoalsSetup(1, world);
        // Grab list of enemies from defeated enemies
        List<Enemy> enemies = world.runBattles();
        assertEquals(1, enemies.size());
        assertEquals(enemies.get(0).getType(), "Vampire");
        world.runTickMoves();
    }

    @Test
    public void createFalseExistingEnemyType() {
        Helper helper = new Helper();
        //  First create world based on map in helper
        LoopManiaWorld world = helper.createWorld(1);
        // Then create a character and place at a path index. For this e.g. I set it to be 0
        helper.createCharacterSetup(0, world);
        // Create a enemy and place at a path index. For this e.g. I set it to be 1 and 0 is the enemy selector which selects slug
        Enemy enemy = helper.createEnemySetup(3, 1, world);
        helper.createGoalsSetup(1, world);

        assertEquals(enemy, null);
    }

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! TODO: FIX CAMPFIRE TEST !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Test
    public void battleWithBuff() {
        Helper helper = new Helper();
        //  First create world based on map in helper
        LoopManiaWorld world = helper.createWorld(1);
        // Then create a character and place at a path index. For this e.g. I set it to be 0
        helper.createCharacterSetup(0, world);
        // Create a enemy and place at a path index. For this e.g. I set it to be 1 and 0 is the enemy selector which selects slug
        helper.createEnemySetup(0, 2, world);
        helper.createGoalsSetup(1, world);

        helper.createBuildingSetup("BarracksCard", 50, world, 3);

        helper.createBuildingSetup("TowerCard", 10, world, 3);

        helper.createBuildingSetup("CampfireCard", 1, world, 1);

        world.runTickMoves();

        // Grab list of enemies from defeated enemies
        List<Enemy> enemies = world.runBattles();
        assertEquals(1, enemies.size());
        assertEquals(enemies.get(0).getType(), "Slug");
        world.runTickMoves();
    }
}