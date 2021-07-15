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

            case TRAP:
                building = new Trap(x, y);
                return building;

            case VILLAGE:
                building = new Village(x, y);
                return building;

            case ZOMBIEPIT:
                building = new ZombiePit(x, y);
                return building;

            case VAMPIRECASTLE:
                building = new VampireCastle(x, y);
                return building;

            case TOWER:
                building = new Tower(x, y);
                return building;

            case BARRACKS:
                building = new Barracks(x, y);
                return building;
        }

        return null;
    }

    public Building getBuilding(String buildingSelector, SimpleIntegerProperty x, SimpleIntegerProperty y, boolean buildingOnPath) {
        Building building;
        switch(buildingSelector) {
            case "VampireCastleCard":
                if (!buildingOnPath) {
                    building = new VampireCastle(x, y);
                    return building;
                }
                break;
            case "ZombiePitCard":
                building = new ZombiePit(x, y);
                return building;
            case "TowerCard":
                building = new Tower(x, y);
                return building;
            case "VillageCard":
                building = new Village(x, y);
                return building;
            case "BarracksCard":
                building = new Barracks(x, y);
                return building;
            case "TrapCard":
                building = new Trap(x, y);
                return building;
            case "CampfireCard":
                building = new Campfire(x, y);
                return building;
        }
        return null;
    }
}
