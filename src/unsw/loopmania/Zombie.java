package unsw.loopmania;

import java.util.Random;

public class Zombie extends Enemy {
    private final String type = "Zombie";
    private int stepCounter;

    public Zombie(PathPosition position) {
        super(position, new Statistics(20, 5, 2, 15, 10));
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

    public void turnSoldier(AlliedSoldier soldier) {
        Random randomItem = new Random();
        int randItem = randomItem.nextInt(5);
        if (randItem == 0) {
            soldier.setIsZombie();
            System.out.println("allied has turned into a zombie");
        }
    }

}
