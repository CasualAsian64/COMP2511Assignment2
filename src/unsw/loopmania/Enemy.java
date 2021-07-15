package unsw.loopmania;

public abstract class Enemy extends MovingEntity {
    int battleRadius;
    int supportRadius;

    // TODO = modify this, and add additional forms of enemy
    public Enemy(PathPosition position, Statistics stats) {
        super(position, stats);
    }

    public void attack(Character character, Statistics charStats) {
        int attack = stats.getAttack();
        charStats.reduceHealth(attack);
    }

    public abstract void reverseDirection();

    public abstract void move();

    public abstract int getBattleRadius();

    public abstract String getType();
}