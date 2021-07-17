package unsw.loopmania;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Statistics for each entity
 */
public class Statistics {
    private int health;
    private int attack;
    private int defense;
    private int exp;
    private int gold;
    private SimpleIntegerProperty healthValue = new SimpleIntegerProperty(this, "healthValue");


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
        this.healthValue.set(health);
    }

    public int getHealth() {
        getHealthProperty();
        return health;
    }

    
    public int getAttack() {
        return attack;
    }

    public int getGold() {
        return gold;
    }

    public int getExp() {
        return exp;
    }

    public void setHealth(int health) {
        this.health = health;
        setHealthProperty(health);
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getDefense() {
        return defense;
    }

    public void reduceHealth(int value) {
        health = health - value;
        if (health < 0) {
            health = 0;
        }

        // JAVAFX bindings
        setHealthProperty(getHealthProperty() - value);
        if (getHealthProperty() < 0){
            setHealthProperty(0);
        }
    }

    public IntegerProperty HealthValueProperty(){
        return healthValue;
    }

    public int getHealthProperty(){
        return healthValue.get();
    }

    public void setHealthProperty(int health){ 
        this.healthValue.set(health);
    }
}
