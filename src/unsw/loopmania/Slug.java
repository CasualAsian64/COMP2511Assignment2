package unsw.loopmania;

import java.util.Random;

public class Slug extends Enemy {
    private static final String type = "slug";

    public Slug(PathPosition position) {
        super(position);
        stats = new Statistics(10, 3, 3, 20, 40);
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

    public int getBattleRadius() {
        return battleRadius;
    }

}
