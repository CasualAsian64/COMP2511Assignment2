package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private boolean isBuffed;
    private ArrayList<AlliedSoldier> allies;
    private IntegerProperty alliesNumValue = new SimpleIntegerProperty(this, "alliesNumValue");

    // TODO = potentially implement relationships between this class and other
    // classes

    public Character(PathPosition position) {
        super(position, new Statistics(100, 5, 2, 0, 0));
        allies = new ArrayList<AlliedSoldier>();
        isBuffed = false;
    }

    public void move() {

    }

    public void collectRewards(Enemy e) {
        Statistics eStats = e.getStats();
        stats.setGold(eStats.getGold() + stats.getGold());
        stats.setExp(eStats.getExp() + stats.getExp());
    }

    @Override
    public Statistics getStats() {
        return stats;
    }

    public void setIsBuffed(boolean isBuffed) {
        this.isBuffed = isBuffed;
    }

    public void attack(Statistics enemyStats, List<Item> equippedItems) {
        int attack = stats.getAttack();
        enemyStats.reduceHealth(attack);
    }

    public void updateStatistics(List<Item> equippedItems) {
        stats.setAttack(5);
        stats.setDefense(2);
        int attack = stats.getAttack();
        int defense = stats.getDefense();
        for (Item equippedItem : equippedItems) {
            if (equippedItem != null && equippedItem instanceof Weapon) {
                attack += equippedItem.getIncrease();
            } else if (equippedItem != null && equippedItem instanceof Equipment) {
                defense += equippedItem.getIncrease();
            }
        }
        stats.setAttack(attack);
        stats.setDefense(defense);
    }

    public int increaseAttack(int attack, Item equippedItem) {
        if (equippedItem != null) {
            attack += equippedItem.getIncrease();
        }
        return attack;
    }

    public ArrayList<AlliedSoldier> getAllies() {
        return allies;
    }

    public IntegerProperty alliesNumValueProperty() {
        return alliesNumValue;
    }

    public int getAlliesNumValueProperty() {
        return alliesNumValue.get();
    }

    public void incrementAlliesNumValueProperty() {
        this.alliesNumValue.set(getAlliesNumValueProperty() + 1);
    }

}
