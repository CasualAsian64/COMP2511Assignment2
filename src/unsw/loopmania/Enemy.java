package unsw.loopmania;

public abstract class Enemy extends MovingEntity {
    int battleRadius;
    int supportRadius;

    // TODO = modify this, and add additional forms of enemy
    public Enemy(PathPosition position, Statistics stats) {
        super(position, stats);
    }

    public void attack(Statistics opponentStats) {
        int attack = stats.getAttack();
        opponentStats.reduceHealth(attack);
    }

    public abstract void move();

    public abstract int getBattleRadius();

    public abstract String getType();
}