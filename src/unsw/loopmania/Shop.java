package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Shop {

    private int total = 0; 
    private IntegerProperty totalValue = new SimpleIntegerProperty(this,"totalValue"); 

    private LoopManiaWorld world; 

    public Shop(LoopManiaWorld world){
        this.world = world;
    }

    private int potions; 
    private int swords; 
    private int stakes; 
    private int staffs; 
    private int helmets; 
    private int armours; 
    private int shields; 
       

    public IntegerProperty totalValue() {
        return totalValue;
    }

    public int getTotalValue(){
        return totalValue.get();
    }
    public void setTotalValue(int totalValue) {
        this.totalValue.set(totalValue);
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        setTotalValue(total);
        this.total = total;
    }

    public Shop() {
        this.total = 0; 
        this.shopping = false;
        this.potions = 0;
        this.swords = 0;
        this.stakes = 0;
        this.staffs = 0;
        this.helmets = 0;
        this.armours = 0;
        this.shields = 0;
    }

    private boolean shopping; 

    

    public boolean isShopping() {
        return shopping;
    }
    public void setShopping(boolean shopping) {
        this.shopping = shopping;
    }


    

    // public void setTotalValue(IntegerProperty totalValue) {
    //     this.totalValue = totalValue;
    // }

    // public LoopManiaWorld getWorld() {
    //     return world;
    // }

    // public void setWorld(LoopManiaWorld world) {
    //     this.world = world;
    // }

    public int getPotions() {
        return potions;
    }

    public void setPotions(int potions) {
        this.potions = potions;
    }

    public int getSwords() {
        return swords;
    }

    public void setSwords(int swords) {
        this.swords = swords;
    }

    public int getStakes() {
        return stakes;
    }

    public void setStakes(int stakes) {
        this.stakes = stakes;
    }

    public int getStaffs() {
        return staffs;
    }

    public void setStaffs(int staffs) {
        this.staffs = staffs;
    }

    public int getHelmets() {
        return helmets;
    }

    public void setHelmets(int helmets) {
        this.helmets = helmets;
    }

    public int getArmours() {
        return armours;
    }

    public void setArmours(int armours) {
        this.armours = armours;
    }

    public int getShields() {
        return shields;
    }

    public void setShields(int shields) {
        this.shields = shields;
    }

    public void addPotion(){
        setTotal(getTotal() + 10);
        setPotions(getPotions() + 1);

    }
    public void subtractPotion(){
        if (getPotions() > 0) {
            setTotal(getTotal() - 10);
            setPotions(getPotions()-1);
        }
    }
    
    public void addSword(){
        setTotal(getTotal() + 50); 
        setSwords(getSwords()+1);

    }
    public void subtractSword(){

        if (getSwords() > 0) {
            setTotal(getTotal() - 50);
            setSwords(getSwords()-1);
        }
    }
    
    public void addStaff() {

        setTotal(getTotal() + 80);
        setStaffs(getStaffs()+1);
    }
    public void subtractStaff(){
        
        if (getStaffs() > 0) {
            setTotal(getTotal() - 80);
            setStaffs(getStaffs()-1);
        }
    }

    public void addStake(){

        setTotal(getTotal() + 30);
        setStakes(getStakes()+1);
    }
    public void subtractStake(){
        
        if (getStakes() > 0) {
            setTotal(getTotal() - 30);
            setStakes(getStakes()-1);

        }
    }

    public void addHelmet(){

        setTotal(getTotal() + 20);
        setHelmets(getHelmets()+1);

    }
    public void subtractHelmet(){
        
        if (getHelmets() > 0) {
            setTotal(getTotal() - 20);
            setHelmets(getHelmets()-1);
        }
    }
    
    public void addArmour(){

        setTotal(getTotal() + 70);

        setArmours(getArmours()+1);
    }
    public void subtractArmour(){

        if (getArmours() > 0) {

            setTotal(getTotal() - 70);
            setArmours(getArmours()-1);

        }
    }

    public void addShield(){

        setTotal(getTotal() + 40);
        setShields(getShields()+1);

    }
    public void subtractShield(){

        if (getShields()> 0) {
            setTotal(getTotal() - 70);
            setShields(getShields()-1);
        }
       
    }

    public boolean sufficientFunds(LoopManiaWorld world) {

         // Compare total with Character's gold
        if (getTotal() > world.getCharacter().getStats().getGold()){
            return false; 
        }

        return true;
    }

    public void finaliseTransaction(Statistics stats, LoopManiaWorld world) { 

    
        // TODO Add all the purchased to the unequipped inventory. 
        
        while (getSwords() > 0) {
            world.addUnequippedItem(0);
            setSwords(getSwords()-1);
        }
        
        while (getStakes() > 0) {
            world.addUnequippedItem(1);
            setStakes(getStakes()-1);
        }


        while (getArmours() > 0) {
            world.addUnequippedItem(2);
            setArmours(getArmours()-1);
        }

        while (getHelmets() > 0) {
            world.addUnequippedItem(3);
            setHelmets(getHelmets()-1);
        }

        while (getShields() > 0) {
            world.addUnequippedItem(4);
            setShields(getShields()-1);
        }
        
        while (getPotions() > 0) {
            world.addUnequippedItem(5);
            setPotions(getPotions()-1);
        }

        while (getStaffs() > 0) {
            world.addUnequippedItem(6);
            setStaffs(getStaffs()-1);
        }


        // Decrement the players gold. 
        stats.setGold(stats.getGold() - getTotal()); 

        // Set total back to zero after each transaction
        setTotal(0);

    }



}
