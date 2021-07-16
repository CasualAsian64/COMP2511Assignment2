package test;

import unsw.loopmania.Shield;
import unsw.loopmania.Armour;
import unsw.loopmania.Character;
import unsw.loopmania.Helmet;

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
    public void equipmentDebuffTest() {
        /**
         * Check if helmet reduces player attack
         * Check if armour and shield reduces player attack
         */

        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();

        Helper helper = new Helper();
        Character character = helper.testCharacterSetup(0, MAP1);

        // assert armour does not reduce player attack
        Armour armour = new Armour(x, y);
        assertEquals(armour.reduceAttack(character.getAttack()), 0);

        // assert shield does not reduce player attack
        Shield shield = new Shield(x, y);
        assertEquals(shield.reduceAttack(character.getAttack()), 0);

        // assert helmet does reduce player attack
        Helmet helmet = new Helmet(x, y);
        assertEquals(helmet.reduceAttack(character.getAttack()), 2);
    }


    /**
     * Other types of tests to potentially complete (might not contribute to coverage...)
     */
    @Test
    public void swordAttackBuffTest() {
        /**
         * Check if sword buffs player attack
         */
    }
    @Test
    public void armourDefenseTest() {
        /**
         * Check if armour reduces enemy attack
         */
    }
}
