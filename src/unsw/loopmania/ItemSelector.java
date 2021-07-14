package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class ItemSelector {
    private static final int SWORD = 0;
    private static final int STAKE = 1;
    private static final int ARMOUR = 2;
    private static final int HELMET = 3;
    private static final int SHIELD = 4;
    private static final int POTION = 5;
    private static final int STAFF = 6;
    private static final int ONERING = 7;

    public Item getItem(int itemSelection, boolean rareItem, int x, int y) {
        Item item;
        switch(itemSelection) {
            case SWORD:
                item = new Sword(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
            case STAKE:
                item = new Stake(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
        }
        return null;
    }
}
