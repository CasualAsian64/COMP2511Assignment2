package unsw.loopmania;

import java.util.Random;

public class Vampire extends Enemy {
    private final String type = "Vampire";
    private boolean direction = true;
    private int remainingCriticalBites = 0;
    private int criticalBiteDamage = 0;

    public Vampire(PathPosition position) {
        super(position, new Statistics(50, 7, 5, 25, 20), 3, 5);
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

    // Assumption: the vampire can deal a critical bite the first time it attacks
    // the allied
    // soldier or character
    @Override
    public void attack(Statistics opponenStatistics) {
        if (remainingCriticalBites <= 0) {
            // reset the criticalBiteDamage
            criticalBiteDamage = 0;
            // Get the chance the vampire provides a critical bite
            Random random = new Random();
            int biteChance = random.nextInt(100);
            // 15% chance of inflicting biteChance
            if (biteChance < 15) {
                remainingCriticalBites = random.nextInt(5) + 1; // the number of critical bites
                criticalBiteDamage = random.nextInt(10) + 1; // the extra damage the critical bite inflicts
            }
        }
        remainingCriticalBites--; // critical bite will be dealt. Therefore, subtract 1.
        int attack = stats.getAttack() + criticalBiteDamage;
        opponenStatistics.reduceHealth(attack);
    }

    public String getType() {
        return type;
    }

    public void reverseDirection() {
        if (direction) {
            direction = false;
        } else {
            direction = true;
        }
    }
}
