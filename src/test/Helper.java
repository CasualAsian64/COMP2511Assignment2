package test;

import unsw.loopmania.PathTile;

import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Helper {

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

}
