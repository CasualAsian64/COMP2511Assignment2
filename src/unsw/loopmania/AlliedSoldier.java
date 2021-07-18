package unsw.loopmania;

import java.util.List;

/**
 * Represents a vampire castle card in the backend game world
 */
public class AlliedSoldier extends MovingEntity {
    private Statistics stats;
    private boolean isZombie;

    public AlliedSoldier(PathPosition position) {
        super(position, new Statistics(20, 2, 0, 0, 0));
        isZombie = false;
    }

    public void setIsZombie() {
        isZombie = true;
        stats.setAttack(5);
    }

    public boolean getIsZombie() {
        return isZombie;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub

    }

    @Override
    public void attack(Statistics opponentStats, List<Item> equippedItems) {
        opponentStats.reduceHealth(stats.getAttack());
    }
}
