package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a vampire castle card in the backend game world
 */
public class AlliedSoldier {
    private int battleRadius;
    private int supportRadius;
    private Statistics stats;
    private boolean isZombie;

    public AlliedSoldier() {
        this.stats = new Statistics(20, 2, 0, 0, 0);
        this.battleRadius = 1;
        this.supportRadius = 1;
        isZombie = false;
    }

    public void attack(Statistics enemyStats) {
        int attack = stats.getAttack();
        enemyStats.reduceHealth(attack);
    }

    public Statistics getStats() {
        return stats;
    }

    public int getHealth() {
        return stats.getHealth();
    }

    public void setIsZombie() {
        isZombie = true;
        stats.setAttack(5);
    }

    public boolean getIsZombie() {
        return isZombie;
    }
}
