package unsw.loopmania;

public class Zombie extends Enemy {
    private int stepCounter;

    public Zombie(PathPosition position) {
        super(position);
        stepCounter = 0;
        battleRadius = 3;
        supportRadius = 3;
    }

    @Override
    public void move() {
        // Zombie moves one step for every 3 steps the character takes
        if (stepCounter == 3) {
            moveUpPath();
            stepCounter = 0;
        } else {
            stepCounter++;
        }
    }

}
