package unsw.loopmania;

import java.util.List;
import java.util.Random;

public class Vampire extends Enemy {
    private final String type = "Vampire";
    private boolean direction;
    int battleRadius;
    int supportRadius;
    int remainingCriticalBites;
    int criticalBiteDamage;

    public Vampire(PathPosition position) {
        super(position, new Statistics(50, 7, 5, 25, 20));
        battleRadius = 3;
        supportRadius = 5;
        direction = true;
    }

    // Assumption: the vampire can deal a critical bite the first time it attacks
    // the allied
    // soldier or character
    @Override
    public void attack(Statistics opponentStats, List<Item> equippedItems) {
        int additionalCharacterAttack = checkStake(equippedItems.get(0));
        additionalCharacterAttack += checkStake(equippedItems.get(1));
        stats.setHealth(stats.getHealth() - additionalCharacterAttack);
        if (stats.getHealth() == 0) {
            return; // the vampire can no longer attack
        }
        if (remainingCriticalBites <= 0) {
            // reset the criticalBiteDamage
            criticalBiteDamage = 0;
            // Get the chance the vampire provides a critical bite
            Random random = new Random();
            int biteChance = random.nextInt(1000);
            int biteChanceRequired = 150; // current chance of a critical bite is 15%
            biteChanceRequired = updateBiteChanceRequired(equippedItems, biteChanceRequired);
            // 15% chance of inflicting biteChance
            if (biteChance < biteChanceRequired) {
                remainingCriticalBites = random.nextInt(5) + 1; // the number of critical bites
                criticalBiteDamage = random.nextInt(10) + 1; // the extra damage the critical bite inflicts
            }
        }
        remainingCriticalBites--; // critical bite will be dealt. Therefore, subtract 1.
        attack = super.updateAttack(attack, equippedItems.get(2));
        attack = super.updateAttack(attack, equippedItems.get(3));
        int attack = stats.getAttack() + criticalBiteDamage;
        opponentStats.reduceHealth(attack);
    }

    public int checkStake(Item weapon) {
        int additionalCharacterAttack = 0;
        if (weapon != null && weapon instanceof Stake) {
            additionalCharacterAttack += weapon.getIncrease();
        }
        return additionalCharacterAttack;
    }

    public int updateBiteChanceRequired(List<Item> equippedItems, int currentBiteChance) {
        currentBiteChance = checkArmour(equippedItems.get(2), currentBiteChance);
        currentBiteChance = checkArmour(equippedItems.get(3), currentBiteChance);
        return currentBiteChance;
    }

    public int checkArmour(Item equipment, int currentBiteChance) {
        if (equipment != null && equipment instanceof Armour) {
            currentBiteChance *= 0.6;
            System.out.println("currentBiteChance: " + currentBiteChance);
        }
        return currentBiteChance;
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

    @Override
    public int getBattleRadius() {
        return battleRadius;
    }

    @Override
    public int getSupportRadius() {
        return supportRadius;
    }

    public void reverseDirection() {
        if (direction) {
            direction = false;
        } else {
            direction = true;
        }
    }
}
