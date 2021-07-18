package unsw.loopmania;

import java.util.List;

/**
 * Represents a vampire castle card in the backend game world
 */
public class AlliedSoldier extends MovingEntity {
    private boolean isZombie;

    public AlliedSoldier(PathPosition position) {
        super(position, new Statistics(20, 2, 0, 0, 0));
        isZombie = false;
    }

    public void setIsZombie() {
        isZombie = true;
    }

    public boolean getIsZombie() {
        return isZombie;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub

    }

    public void attack(Statistics opponentStats, List<Item> equippedItems) {
        opponentStats.reduceHealth(super.getAttack() - opponentStats.getDefense());
    }
}
