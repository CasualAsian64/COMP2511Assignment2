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

    private ArrayList<HealthPotion> potions; 
    private ArrayList<Sword> swords; 
    private ArrayList<Stake> stakes; 
    private ArrayList<Staff> staffs; 
    private ArrayList<Helmet> helmets; 
    private ArrayList<Armour> armours; 
    private ArrayList<Shield> shields; 
    
   
    
    
   

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
        this.potions = new ArrayList<HealthPotion>(); 
        this.swords = new ArrayList<Sword>(); 
        this.stakes = new ArrayList<Stake>(); 
        this.staffs = new ArrayList<Staff>(); 
        this.helmets = new ArrayList<Helmet>(); 
        this.armours = new ArrayList<Armour>(); 
        this.shields = new ArrayList<Shield>(); 
    }

    private boolean shopping; 

    

    public boolean isShopping() {
        return shopping;
    }
    public void setShopping(boolean shopping) {
        this.shopping = shopping;
    }

    public void addPotion(){
        setTotal(getTotal() + 10);
        // HealthPotion potion = new HealthPotion(0,0);
        // potions.add(potion);

        world.addUnequippedItem();
    }
    public void subtractPotion(){
        // total = total - 10; 
        setTotal(getTotal() - 10);
        // potions.remove(0);
    }
    
    public void addSword(){

        setTotal(getTotal() + 50);
        // Sword sword = new Sword(); 
        // swords.add(sword);
    }
    public void subtractSword(){

        setTotal(getTotal() - 50);

        // swords.remove(0);
    }
    
    public void addStaff() {

        setTotal(getTotal() + 80);

        // Staff staff = new Staff();
        // staffs.add(staff);
    }
    public void subtractStaff(){

        setTotal(getTotal() - 80);

        // staffs.remove(0);
    }

    public void addStake(){

        setTotal(getTotal() + 30);
        // Stake stake = new Stake();
        // stakes.add(stake);
    }
    public void subtractStake(){
        setTotal(getTotal() - 30);

        // stakes.remove(0);
    }

    public void addHelmet(){

        setTotal(getTotal() + 20);

        // Helmet helmet = new Helmet();
        // helmets.add(helmet);
    }
    public void subtractHelmet(){

        setTotal(getTotal() - 20);

        // helmets.remove(0);
    }
    
    public void addArmour(){

        setTotal(getTotal() + 70);

        // Armour armour = new Armour();
        // armours.add(armour);
    }
    public void subtractArmour(){

        setTotal(getTotal() - 70);

        // armours.remove(0);
    }

    public void addShield(){

        setTotal(getTotal() + 40);


    }
    public void subtractShield(){
        setTotal(getTotal() - 70);
        // shields.remove(0);
    }

    public boolean sufficientFunds(){

         // Compare total with Character's gold
        if (total > world.getCharacter().getStats().getGold()){
            // Not enough gold
            // System.out.println("Not enough gold!");
            return false; 
        }

        return true;
    }

    public void finaliseTransaction(Statistics stats, List<Item> unequippedInventory) { 

    
        // Add all the items to the unequipped inventory. 
        for (HealthPotion hp: potions) {
            unequippedInventory.add(hp);
        }

        for (Sword sword: swords) {
            unequippedInventory.add(sword);
        }

        for (Stake stake: stakes) {
            unequippedInventory.add(stake);
        }

        for (Staff staff: staffs) {
            unequippedInventory.add(staff);
        }

        for (Helmet helmet: helmets) {
            unequippedInventory.add(helmet);
        }

        for (Armour armour: armours) { 
            unequippedInventory.add(armour);
        }

        for (Shield shield: shields) { 
            unequippedInventory.add(shield);
        }

        // Decrement the players gold. 
        stats.setGold(stats.getGold() - total); 


    }



}
