package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Items that can be attached to the character.
 */
public abstract class Item extends StaticEntity {
    /**
     * attributes for Item
     * EXPREWARD and GOLDREWARD: integers for the gold and exp rewards
     */
    private static final int EXPREWARD = 20;
    private static final int GOLDREWARD = 100;
    protected String type;
    Statistics stats;

    /**
     * constructor for Item
     * @param x
     * @param y
     */
    public Item(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * getter for Type
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * getter for Statistics
     * @return Statistics
     */
    public Statistics getStats() {
        return this.getStats();
    }
    
    /**
     * getter for Attack
     * @return int
     */
    public int getAttack(){
        Statistics stats = this.getStats();
        return stats.getAttack();
    }

    /**
     * getter for Defense
     * @return int
     */
    public int getDefense(){
        Statistics stats = this.getStats();
        return stats.getDefense();
    } 

    /**
     * method to award exp and gold for when a character removes a card
     * @param character
     */
    public void removeCardAward(Character character) {
        Statistics stats = character.getStats();
        stats.setExp(stats.getExp() + EXPREWARD);
        stats.setGold(stats.getGold() + GOLDREWARD);
    }

    /**
     * abstract getter for Item Type
     * @return String
     */
    public abstract String getItemType();
}