package unsw.loopmania;

public abstract class Enemy extends MovingEntity {
    int battleRadius;
    int supportRadius;

    // TODO = modify this, and add additional forms of enemy
    public Enemy(PathPosition position) {
        super(position);
    }

    // If an enemy dies early, it's attack should be set to 0
    public void getAttacked(int attack, Character character) {
        Statistics stats = this.getStats();
        int health = stats.getHealth() - attack;
        if (health <= 0) {
            health = 0;
            stats.setAttack(0);
        }
        stats.setHealth(health);
    }

    public abstract void move();

    public abstract int getBattleRadius();

    public abstract String getType();
}