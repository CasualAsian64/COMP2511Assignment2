package unsw.loopmania;

public class Vampire extends Enemy {
    public Vampire(PathPosition position) {
        super(position);
        stats = new Statistics(20, 5, 5, 20, 40);
        battleRadius = 3;
        supportRadius = 5;
    }

    /*
     * Movement for the Vampire
     */
    @Override
    public void move() {
        moveUpPath();
        moveUpPath();
    }

    @Override
    public Statistics getStats() {
        return stats;
    }
}
