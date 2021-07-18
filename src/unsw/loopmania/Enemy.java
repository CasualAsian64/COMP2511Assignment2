package unsw.loopmania;

import java.util.List;

public abstract class Enemy extends MovingEntity {

    // TODO = modify this, and add additional forms of enemy
    public Enemy(PathPosition position, Statistics stats) {
        super(position, stats);
    }

    public void attack(Statistics opponentStats, List<Item> equippedItems) {
        int attack = super.getAttack();
        attack = updateAttack(attack, equippedItems.get(2));
        attack = updateAttack(attack, equippedItems.get(3));
        attack -= opponentStats.getDefense();
        opponentStats.reduceHealth(attack);
    }

    public int updateAttack(int attack, Item equipment) {
        if (equipment != null && equipment instanceof Armour) {
            attack = attack / 2;
        }
        return attack;
    }

    public abstract void reverseDirection();

    public abstract void move();

    public abstract int getBattleRadius();

    public abstract int getSupportRadius();

    public abstract String getType();
}