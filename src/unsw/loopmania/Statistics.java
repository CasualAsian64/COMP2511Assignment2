package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Statistics for each entity
 */
public class Statistics{
    private int health;
    private int attack;
    private int defense;
    private int exp;
    private int gold;

    // Default constructor
    public Statistics() {
        this.health = 0;
        this.defense = 0;
        this.exp = 0;
        this.gold = 0;
    }

    public Statistics(int health, int attack, int defense, int exp, int gold) {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.exp = exp;
        this.gold = gold;
    }

    public int getHealth(){
        return health;
    }

    public int getAttack(){
        return attack;
    }

    public int getGold(){
        return gold;
    }

    public int getExp(){
        return exp;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }

    public void setGold(int gold){
        this.gold = gold;
    }

    public void setExp(int exp){
        this.exp = exp;
    }

    public int getDefense() {
        return defense;
    }

}
