package unsw.loopmania;

public class Slug extends Enemy {
    private final String type = "Slug";

    public Slug(PathPosition position) {
        super(position, new Statistics(10, 3, 3, 20, 40), 2, 2);
    }

    /**
     * Movement for the slug
     */
    public void move() {
        moveUpPath();
    }

    public String getType() {
        return type;
    }

    @Override
    public void reverseDirection() {
        // TODO Auto-generated method stub

    }

    @Override
    public void reverseDirection() {
        // TODO Auto-generated method stub
        
    }

}
