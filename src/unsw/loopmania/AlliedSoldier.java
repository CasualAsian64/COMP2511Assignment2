package unsw.loopmania;

import java.util.List;

/**
 * Represents a vampire castle card in the backend game world
 */
public class AlliedSoldier extends MovingEntity {
    // used to determine if the allied soldier is a zombie
    private boolean isZombie;

    /**
     * Constructor for Allied Soldier
     * 
     * @param position
     */
    public AlliedSoldier(PathPosition position) {
        super(position, new Statistics(20, 2, 0, 0, 0));
        isZombie = false;
    }

    /**
     * Turn the soldier into a zombie
     */
    public void setIsZombie() {
        isZombie = true;
    }

    /**
     * Return whether the soldier is a zombie
     * 
     * @return
     */
    public boolean getIsZombie() {
        return isZombie;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub

    }

    /**
     * Soldier's method of attack
     */
    public void attack(MovingEntity opponent, List<Item> equippedItems) {
        opponent.reduceHealth(super.getAttack() - opponent.getDefense());
    }
}
