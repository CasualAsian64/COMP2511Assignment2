package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * The moving entity
 */
public abstract class MovingEntity extends Entity {
    int attack;
    Statistics stats;
    /**
     * object holding position in the path
     */
    private PathPosition position;

    /**
     * Create a moving entity which moves up and down the path in position
     * 
     * @param position represents the current position in the path
     */
    public MovingEntity(PathPosition position) {
        super();
        this.position = position;
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
        return this.getStats();
    }
    
    public int getHealth() {
        Statistics stats = this.getStats();
        return stats.getHealth();
    }

    public int getAttack(){
        Statistics stats = this.getStats();
        return stats.getAttack();
    }
    
    public int getDefense(){
        Statistics stats = this.getStats();
        return stats.getDefense();
    }    

    public void getAttacked(int attack) {
        Statistics stats = this.getStats();
        int health = stats.getHealth() - attack;
        if (health < 0) {
            health = 0;
        }
        stats.setHealth(health);
    }
}
