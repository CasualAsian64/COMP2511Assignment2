package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * The moving entity
 */
public abstract class MovingEntity extends Entity {
    /**
     * object holding the statistics of a moving entity
     */
    Statistics stats;
    /**
     * Determines if the current entity is in a trance
     */
    private boolean inTrance;
    /**
     * object holding position in the path
     */
    private PathPosition position;

    /**
     * Create a moving entity which moves up and down the path in position
     * 
     * @param position represents the current position in the path
     */
    public MovingEntity(PathPosition position, Statistics stats) {
        super();
        this.position = position;
        this.stats = stats;
        this.inTrance = false;
    }

    /**
     * Abstract method: All moving entities require the method move()
     */
    public abstract void move();

    /**
     * move clockwise through the path
     */
    public void moveDownPath() {
        position.moveDownPath();
    }

    /**
     * move anticlockwise through the path
     */
    public void moveUpPath() {
        position.moveUpPath();
    }

    public SimpleIntegerProperty x() {
        return position.getX();
    }

    public SimpleIntegerProperty y() {
        return position.getY();
    }

    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public Statistics getStats() {
        return stats;
    }

    public int getHealth() {
        Statistics stats = this.getStats();
        return stats.getHealth();
    }

    public int getAttack() {
        Statistics stats = this.getStats();
        return stats.getAttack();
    }

    public int getDefense() {
        Statistics stats = this.getStats();
        return stats.getDefense();
    }

    public void setInTrance(boolean inTrance) {
        this.inTrance = inTrance;
    }

    public boolean getInTrance() {
        return inTrance;
    }

    public abstract void attack(Statistics opponentStats, List<Item> equippedItems);

    public PathPosition getPathPosition() {
        return position;
    }
}
