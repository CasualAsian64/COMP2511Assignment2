package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class BuildingSelector {
    private static final int CAMPFIRE = 0;
    private static final int TRAP = 1;
    private static final int VILLAGE = 2;
    private static final int ZOMBIEPIT = 3;
    private static final int VAMPIRECASTLE = 4;
    private static final int TOWER = 5;
    private static final int BARRACKS = 6;


    public Building getBuilding(int buildingSelector, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Building building;
        switch(buildingSelector) {
            case CAMPFIRE:
                building = new Campfire(x, y);
                return building;
        }

        return null;
    }
}
