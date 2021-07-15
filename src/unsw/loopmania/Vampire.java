package unsw.loopmania;

public class Vampire extends Enemy {
    private final String type = "Vampire";

    public Vampire(PathPosition position) {
        super(position, new Statistics(50, 7, 5, 25, 20));
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

    public String getType() {
        return type;
    }

    public int getBattleRadius() {
        return battleRadius;
    }

    @Override
    public void runAway() {
        moveDownPath();
        moveDownPath();
    }
}
