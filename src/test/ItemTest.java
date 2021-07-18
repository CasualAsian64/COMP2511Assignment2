package test;

import unsw.loopmania.Character;
import unsw.loopmania.Item;
import unsw.loopmania.LoopManiaWorld;

import org.json.JSONArray;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

/**
 * Tests for all item types 
 * weapons: sword, stake, staff
 * equipment: armour, shield, helmet
 * consumables: health potion, one ring
 */  
public class ItemTest {
    /**
     * test the equipping and unequipping of items 
     */
    @Test
    public void testAddRemoveUnequipItem() {
        Helper helper = new Helper();
        //  First create world based on map in helper
        LoopManiaWorld world = helper.createWorld(1);
        // Then create a character and place at a path index. For this e.g. I set it to be 0
        helper.createCharacterSetup(0, world);

        // add 21 items (only 16 slots exist)
        for (int i = -1; i < 7; i++) {
            world.addUnequippedItem(i);
        }
        for (int i = -1; i < 7; i++) {
            world.addUnequippedItem(i);
        }
        for (int i = -1; i < 7; i++) {
            world.addUnequippedItem(i);
        }
        
        // assert items have been added to the unequipment inventory
        assertEquals(world.getUnequippedInventoryItems().size(), 16);
        world.removeUnequippedInventoryItemByCoordinates(0, 0);
        assertEquals(world.getUnequippedInventoryItems().size(), 15);
        assertEquals(world.checkItemInEquippedInventory("Armour"), false);
    }

    /**
     * test the if the one ring item revives the player
     */
    @Test
    public void testOneRingItem() {
        Helper helper = new Helper();
        //  First create world based on map in helper
        LoopManiaWorld world = helper.createWorld(1);
        // Then create a character and place at a path index. For this e.g. I set it to be 0
        Character character = helper.createCharacterSetup(0, world);
        helper.createGoalsSetup(1, world);

        assertEquals(world.checkTheOneRingInUnequippedItems(), false);

        JSONArray rareItems = new JSONArray();
        rareItems.put("the_one_ring");
        world.setRareItems(rareItems);

        world.addUnequippedItem(7);

        assertEquals(world.getUnequippedInventoryItems().size(), 1);
        assertEquals(world.checkTheOneRingInUnequippedItems(), true);

        assertEquals(character.getHealth(), 100);

        helper.createEnemySetup(2, 1, world);
        helper.createEnemySetup(2, 1, world);
        helper.createEnemySetup(2, 1, world);

        world.runBattles();

        // one ring is now destroyed
        assertEquals(world.checkTheOneRingInUnequippedItems(), false);
        assertEquals(character.getHealth(), 37);

        helper.createEnemySetup(2, 1, world);

        world.runBattles();
        assertEquals(character.getHealth(), 0);

        assertEquals(world.isGameWon(), false);
        assertEquals(world.isGameOver(), true);
    }

    /**
     * test if the health potion heals the player character
     */
    @Test
    public void testHealthPotion() {
        Helper helper = new Helper();
        //  First create world based on map in helper
        LoopManiaWorld world = helper.createWorld(1);
        // Then create a character and place at a path index. For this e.g. I set it to be 0
        Character character = helper.createCharacterSetup(0, world);
        helper.createGoalsSetup(1, world);

        helper.createEnemySetup(2, 1, world);
        world.runBattles();

        world.possiblySpawnEnemies();

        assertEquals(character.getHealth(), 37);

        world.addUnequippedItem(5);
        assertEquals(world.getUnequippedInventoryItems().size(), 1);
        List<Item> uneuippedItems = world.getUnequippedInventoryItems();
        assertEquals(uneuippedItems.get(0).getType(), "HealthPotion");

        world.usePotion(uneuippedItems.get(0));

        assertEquals(character.getHealth(), 57);
    }
}
