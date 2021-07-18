package unsw.loopmania;

public class Slug extends Enemy {
    private final String type = "Slug";
    int battleRadius;
    int supportRadius;

    public Slug(PathPosition position) {
        super(position, new Statistics(10, 3, 3, 20, 40));
        battleRadius = 2;
        supportRadius = 2;
    }

    /**
     * Movement for the slug
     */
    public void move() {
        moveUpPath();
    }

    @Override
    public Statistics getStats() {
        return stats;
    }

    public String getType() {
        return type;
    }

    @Override
    public void reverseDirection() {
        // TODO Auto-generated method stub

    }

    public int getSupportRadius() {
        return supportRadius;
    }

    public int getBattleRadius() {
        return battleRadius;
    }

}
