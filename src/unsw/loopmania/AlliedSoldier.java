package unsw.loopmania;

/**
 * Represents a vampire castle card in the backend game world
 */
public class AlliedSoldier extends MovingEntity {
    private boolean isZombie;

    public AlliedSoldier(PathPosition position) {
        super(position, new Statistics(20, 2, 0, 0, 0));
        isZombie = false;
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

    @Override
    public void move() {
        // TODO Auto-generated method stub

    }
}
