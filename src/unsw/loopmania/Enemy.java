package unsw.loopmania;

public abstract class Enemy extends MovingEntity {
    int battleRadius;
    int supportRadius;

    // TODO = modify this, and add additional forms of enemy
    public Enemy(PathPosition position, Statistics stats, int battleRadius, int supportRadius) {
        super(position, stats);
        this.battleRadius = battleRadius;
        this.supportRadius = supportRadius;
    }

    public abstract void reverseDirection();

    public abstract void move();

    public int getBattleRadius() {
        return battleRadius;
    }

    public int getSupportRadius() {
        return supportRadius;
    }

    public abstract String getType();

}