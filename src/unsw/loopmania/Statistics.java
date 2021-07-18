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
    private SimpleIntegerProperty goldValue = new SimpleIntegerProperty(this, "goldValue");
    private SimpleIntegerProperty expValue = new SimpleIntegerProperty(this, "expValue");
    private SimpleIntegerProperty attackValue = new SimpleIntegerProperty(this, "attackValue");
    private SimpleIntegerProperty defenseValue = new SimpleIntegerProperty(this, "defenseValue");

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
        this.goldValue.set(gold);
        this.expValue.set(exp);
        this.attackValue.set(attack);
        this.defenseValue.set(defense);
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

    public void setHealth(int healthPoints) {
        this.health = healthPoints;
        if (this.health > 100) {
            this.health = 100;
        }
        setHealthProperty(health);
    }

    public void setAttack(int attack) {
        setAttackProperty(attack);
        this.attack = attack;
    }

    public void setDefense(int defense) {
        setDefenseProperty(defense);
        this.defense = defense;
    }

    public void setGold(int gold) {
        setGoldProperty(gold);
        this.gold = gold;
    }

    public void setExp(int exp) {
        setExpProperty(exp);
        this.exp = exp;
    }

    public int getDefense() {
        return defense;
    }

    public void reduceHealth(int value) {
        if (value < 0) {
            value = 0;
        }
        health = health - value;
        if (health < 0) {
            health = 0;
        }

        // JAVAFX bindings
        setHealthProperty(getHealthProperty() - value);
        if (getHealthProperty() < 0) {
            setHealthProperty(0);
        }
    }

    public IntegerProperty HealthValueProperty() {
        return healthValue;
    }

    public int getHealthProperty() {
        return healthValue.get();
    }

    public void setHealthProperty(int health) {
        this.healthValue.set(health);
    }

    public IntegerProperty goldValueProperty() {
        return goldValue;
    }

    public int getGoldProperty() {
        return goldValue.get();
    }

    public void setGoldProperty(int gold) {
        this.goldValue.set(gold);
    }

    public IntegerProperty expValueProperty() {
        return expValue;
    }

    public int getExpProperty() {
        return expValue.get();
    }

    public void setExpProperty(int exp) {
        this.expValue.set(exp);
    }

    public IntegerProperty attackValueProperty() {
        return attackValue;
    }

    public int getattackProperty() {
        return attackValue.get();
    }

    public void setAttackProperty(int attack) {
        this.attackValue.set(attack);
    }

    public IntegerProperty defenseValueProperty() {
        return defenseValue;
    }

    public int getDefenseProperty() {
        return defenseValue.get();
    }

    public void setDefenseProperty(int defense) {
        this.defenseValue.set(defense);
    }

}
