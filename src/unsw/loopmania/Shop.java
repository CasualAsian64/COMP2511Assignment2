package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Shop {

    private int total = 0; 
    private IntegerProperty totalValue = new SimpleIntegerProperty(this,"totalValue"); 


    private ArrayList<HealthPotion> potions; 
    private ArrayList<Sword> swords; 
    private ArrayList<Stake> stakes; 
    private ArrayList<Staff> staffs; 
    private ArrayList<Helmet> helmets; 
    private ArrayList<Armour> armours; 
    private ArrayList<Shield> shields; 
    
   
    
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
        total = total + 10; 
        HealthPotion potion = new HealthPotion();
        potions.add(potion);
    }
    public void subtractPotion(){
        total = total - 10; 
        potions.remove(0);
    }
    
    public void addSword(){
        total = total + 50;
        Sword sword = new Swords(); 
        swords.add(sword);
    }
    public void subtractSword(){
        total = total - 50; 
        swords.remove(0);
    }
    
    public void addStaff() {
        total = total + 80; 
        Staff staff = new Staff();
        staffs.add(staff);
    }
    public void subtractStaff(){
        total = total - 90; 
        staffs.remove(0);
    }

    public void addStake(){
        total = total + 30; 
        Stake stake = new Stake();
        stakes.add(stake);
    }
    public void subtractStake(){
        total = total - 30; 
        stakes.remove(0);
    }

    public void addHelmet(){
        total = total + 20; 
        Helmet helmet = new Helmet();
        helmets.add(helmet);
    }
    public void subtractHelmet(){
        total = total - 20; 
        helmets.remove(0);
    }
    
    public void addArmour(){
        total = total + 70; 
        Armour armour = new Armour();
        armours.add(armour);
    }
    public void subtractArmour(){
        total = total - 70; 
        armours.remove(0);
    }

    public void addShield(){
        total = total + 40; 
        Shield shield = new Shield; 
        shields.add(shield);
    }
    public void subtractShield(){
        total = total - 40; 
        shields.remove(0);
    }

    public boolean sufficientFunds(){

         // Compare total with Character's gold
        if (total > stats.getGold()){
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
