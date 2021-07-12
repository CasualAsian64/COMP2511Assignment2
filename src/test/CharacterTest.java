package test;

import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;

import org.javatuples.Pair;
import org.json.JSONObject;
import org.junit.Test;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CharacterTest {

    // Multiple setups for enemies, a character, buildings etc.
    public Character testCharacterSetup() {
        Helper helper = new Helper();
        int height = 14;
        int width = 8;
        int start_posX = 0;
        int start_posY = 0;
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        JSONObject path = helper.createJSONMap(start_posX, start_posY, 1);
        orderedPath = helper.loadPathTiles(path, width, height);
        // Create a path position where the character is at the first part of the path
        PathPosition pathPosition = new PathPosition(0, orderedPath);
        Character c = new Character(pathPosition);
        return c;
    }

    /**
     * Unit test
     * @throws FileNotFoundException
     */
    @Test
    public void test_character_movement(){
        Character c = testCharacterSetup();
        System.out.println(c.getHealth());
        System.out.println(c.getX() + "," + c.getY());
        c.moveDownPath();
        System.out.println(c.getX() + "," + c.getY());
        c.moveDownPath();
        System.out.println(c.getX() + "," + c.getY());
        c.moveDownPath();
        c.moveDownPath();
        c.moveDownPath();
        System.out.println(c.getX() + "," + c.getY());
    }

    /**
     * Integration test
     */
}
