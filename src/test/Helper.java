package test;

import unsw.loopmania.Character;
import unsw.loopmania.Goals;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.PathTile;
import unsw.loopmania.Slug;
import unsw.loopmania.Vampire;
import unsw.loopmania.Zombie;

import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Entity;

public class Helper {
    private static final int MAP1 = 1;
    private static final int MAP2 = 2;

    public LoopManiaWorld createWorld(int currentPositionInPath, int mapNo) {
        int height = 0;
        int width = 0;
        switch(mapNo) {
            case MAP1:
                width = 8;
                height = 14;
                break;
            case MAP2:
                width = 3;
                height = 3; 
                break; 
        }
        int start_posX = 0;
        int start_posY = 0;
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        JSONObject path = createJSONMap(start_posX, start_posY, mapNo);
        orderedPath = loadPathTiles(path, width, height);
        // Create a path position where the character is at the first part of the path
        LoopManiaWorld world = new LoopManiaWorld(width, height, orderedPath);

        return world;
    }

    public Character testCharacterSetup(int currentPositionInPath, int mapNo) {
        int height = 0;
        int width = 0;
        switch(mapNo) {
            case MAP1:
                width = 8;
                height = 14;
                break;
            case MAP2:
                width = 3;
                height = 3; 
                break; 
        }
        int start_posX = 0;
        int start_posY = 0;
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        JSONObject path = createJSONMap(start_posX, start_posY, mapNo);
        orderedPath = loadPathTiles(path, width, height);
        // Create a path position where the character is at the first part of the path
        PathPosition pathPosition = new PathPosition(currentPositionInPath, orderedPath);
        Character c = new Character(pathPosition);
        return c;
    }

    /**
     * Function to create Slug entity on the first tile path
     * @param currentPositionInPath
     * @param mapNo
     * @return
     */
    public Slug testSlugSetup(int currentPositionInPath, int mapNo) {
        int height = 0;
        int width = 0;
        switch(mapNo) {
            case MAP1:
                width = 8;
                height = 14;
                break;
            case MAP2:
                width = 3;
                height = 3; 
                break; 
        }
        int start_posX = 0;
        int start_posY = 0;
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        JSONObject path = createJSONMap(start_posX, start_posY, mapNo);
        orderedPath = loadPathTiles(path, width, height);
        // Create a path position where the Slug is at the first part of the path
        PathPosition pathPosition = new PathPosition(currentPositionInPath, orderedPath);
        Slug slug = new Slug(pathPosition);
        return slug;
    }

    /**
     * Function to create Zombie entity on the first tile path
     * @param currentPositionInPath
     * @param mapNo
     * @return
     */
    public Zombie testZombieSetup(int currentPositionInPath, int mapNo) {
        int height = 0;
        int width = 0;
        switch(mapNo) {
            case MAP1:
                width = 8;
                height = 14;
                break;
            case MAP2:
                width = 3;
                height = 3; 
                break; 
        }
        int start_posX = 0;
        int start_posY = 0;
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        JSONObject path = createJSONMap(start_posX, start_posY, mapNo);
        orderedPath = loadPathTiles(path, width, height);
        // Create a path position where the Slug is at the first part of the path
        PathPosition pathPosition = new PathPosition(currentPositionInPath, orderedPath);
        Zombie zombie = new Zombie(pathPosition);
        return zombie;
    }

    /**
     * Function to create Vampire entity on the first tile path
     * @param currentPositionInPath
     * @param mapNo
     * @return
     */
    public Vampire testVampireSetup(int currentPositionInPath, int mapNo) {
        int height = 0;
        int width = 0;
        switch(mapNo) {
            case MAP1:
                width = 8;
                height = 14;
                break;
            case MAP2:
                width = 3;
                height = 3; 
                break; 
        }
        int start_posX = 0;
        int start_posY = 0;
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        JSONObject path = createJSONMap(start_posX, start_posY, mapNo);
        orderedPath = loadPathTiles(path, width, height);
        // Create a path position where the Slug is at the first part of the path
        PathPosition pathPosition = new PathPosition(currentPositionInPath, orderedPath);
        Vampire vampire = new Vampire(pathPosition);
        return vampire;
    }

    public JSONObject createJSONMap(int start_posX, int start_posY, int pathNo) {
        JSONObject world = new JSONObject();
        world.put("type", "path_tile");
        world.put("x", 0);
        world.put("y", 0);
        JSONArray jsonPath = new JSONArray();
        switch(pathNo) {
            case 1:
                jsonPath = Path1();
                break;
            case 2:
                jsonPath = Path2();
                break;
        }
        world.put("path", jsonPath);
        String message = world.toString();
        System.out.println(message);
        return world;
    }

    
    public List<Pair<Integer, Integer>> loadPathTiles(JSONObject path, int width, int height) {
        if (!path.getString("type").equals("path_tile")) {
            // ... possible extension
            throw new RuntimeException(
                    "Path object requires path_tile type.  No other path types supported at this moment.");
        }
        PathTile starting = new PathTile(new SimpleIntegerProperty(path.getInt("x")), new SimpleIntegerProperty(path.getInt("y")));
        if (starting.getY() >= height || starting.getY() < 0 || starting.getX() >= width || starting.getX() < 0) {
            throw new IllegalArgumentException("Starting point of path is out of bounds");
        }
        // load connected path tiles
        List<PathTile.Direction> connections = new ArrayList<>();
        for (Object dir: path.getJSONArray("path").toList()){
            connections.add(Enum.valueOf(PathTile.Direction.class, dir.toString()));
        }

        if (connections.size() == 0) {
            throw new IllegalArgumentException(
                "This path just consists of a single tile, it needs to consist of multiple to form a loop.");
        }

        // load the first position into the orderedPath
        PathTile.Direction first = connections.get(0);
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(Pair.with(starting.getX(), starting.getY()));

        int x = starting.getX() + first.getXOffset();
        int y = starting.getY() + first.getYOffset();

        // add all coordinates of the path into the orderedPath
        for (int i = 1; i < connections.size(); i++) {
            orderedPath.add(Pair.with(x, y));
            
            if (y >= height || y < 0 || x >= width || x < 0) {
                throw new IllegalArgumentException("Path goes out of bounds at direction index " + (i - 1) + " (" + connections.get(i - 1) + ")");
            }
            
            PathTile.Direction dir = connections.get(i);
            x += dir.getXOffset();
            y += dir.getYOffset();
            if (orderedPath.contains(Pair.with(x, y)) && !(x == starting.getX() && y == starting.getY())) {
                throw new IllegalArgumentException("Path crosses itself at direction index " + i + " (" + dir + ")");
            }
            //onLoad(tile, connections.get(i - 1), dir);
        }
        // we should connect back to the starting point
        if (x != starting.getX() || y != starting.getY()) {
            throw new IllegalArgumentException(String.format(
                    "Path must loop back around on itself, this path doesn't finish where it began, it finishes at %d, %d.",
                    x, y));
        }
        //onLoad(starting, connections.get(connections.size() - 1), connections.get(0));
        return orderedPath;
    }

    public JSONArray Path1() {
        String[] path = {"RIGHT", "RIGHT", "DOWN", "RIGHT", "RIGHT", "UP", "RIGHT", "RIGHT", "RIGHT",
        "DOWN", "DOWN", "DOWN", "DOWN", "DOWN", "DOWN", "DOWN", "DOWN", "DOWN",
        "LEFT", "LEFT", "LEFT", "UP", "UP", "RIGHT",
        "UP", "UP", "UP", "UP", "LEFT", "LEFT", "LEFT",
        "DOWN", "DOWN", "DOWN", "DOWN", "DOWN", "DOWN", "DOWN", "DOWN",
        "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "DOWN", "DOWN",
        "LEFT", "LEFT", "LEFT", "LEFT", "LEFT", "LEFT", "LEFT",
        "UP", "UP", "UP", "UP", "UP", "UP", "UP", "UP", "UP", "UP", "UP", "UP", "UP"};
        JSONArray jsonPath = new JSONArray();
        for (String direction: path) {
            jsonPath.put(direction);
        }
        return jsonPath;
    }

    public JSONArray Path2() {
        String[] path = {"RIGHT", "RIGHT", "DOWN", "DOWN", "LEFT", "LEFT", "UP", "UP"};
        JSONArray jsonPath = new JSONArray();
        for (String direction: path) {
            jsonPath.put(direction);
        }
        return jsonPath;
    }

    public JSONObject goalCondition1() {
        JSONObject goalCondition = new JSONObject();
        goalCondition.put("goal", "AND");
        JSONArray ANDarray = new JSONArray();
        JSONObject ANDgoals = new JSONObject();
        ANDgoals.put("goal", "experience");
        ANDgoals.put("quantity", 100);
        ANDarray.put(ANDgoals);
        JSONObject SubGoals = new JSONObject();
        SubGoals.put("goal", "OR");
        JSONArray ORArray = new JSONArray();
        JSONObject ORgoals = new JSONObject();
        ORgoals.put("goal", "experience");
        ORgoals.put("quantity", 123456);
        ORArray.put(ORgoals);
        JSONObject ORgoals2 = new JSONObject();
        ORgoals2.put("goal", "gold");
        ORgoals2.put("quantity", 1000);
        ORArray.put(ORgoals2);
        SubGoals.put("subgoals", ORArray);       
        ANDarray.put(SubGoals);
        goalCondition.put("subgoals", ANDarray);
        System.out.println(goalCondition.toString());
        return goalCondition;
    }

    public JSONObject goalCondition2() {
        JSONObject goalCondition = new JSONObject();
        goalCondition.put("goal","experience");
        goalCondition.put("quantity", 123456);
        System.out.println(goalCondition.toString());
        return goalCondition;
    }


}
