package unsw.loopmania;

public class Vampire extends Enemy {
    private final String type = "Vampire";
    private boolean direction = true;
    
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
        if (direction) {
            moveUpPath();
            moveUpPath();
        } else {
            moveDownPath();
            moveDownPath();
        }
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

    public void reverseDirection() {
        if (direction) {
            direction = false;
        } else {
            direction = true;
        }
    }
}
