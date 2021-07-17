package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Shop {

    private int total = 0; 

    private ArrayList<HealthPotion> potions; 
    private ArrayList<Sword> swords; 
    private ArrayList<Stake> stakes; 
    private ArrayList<Staff> staffs; 
    private ArrayList<Helmet> helmets; 
    private ArrayList<Armour> armours; 
    private ArrayList<Shield> shields; 

    


    public void addPotion(HealthPotion potion){
        total = total + 10; 
        potions.add(potion);
    }
    public void subtractPotion(){
        total = total - 10; 
        potions.remove(0);
    }
    
    public void addSword(Sword sword){
        total = total + 50; 
        swords.add(sword);
    }
    public void subtractSword(){
        total = total - 50; 
        swords.remove(0);
    }
    
    public void addStaff(Staff staff){
        total = total + 80; 
        staffs.add(staff);
    }
    public void subtractStaff(){
        total = total - 90; 
        staffs.remove(0);
    }

    public void addStake(Stake stake){
        total = total + 30; 
        stakes.add(stake);
    }
    public void subtractStake(){
        total = total - 30; 
        stakes.remove(0);
    }

    public void addHelmet(Helmet helmet){
        total = total + 20; 
        helmets.add(helmet);
    }
    public void subtractHelmet(){
        total = total - 20; 
        helmets.remove(0);
    }
    
    public void addArmour(Armour armour){
        total = total + 70; 
        armours.add(armour);
    }
    public void subtractArmour(){
        total = total - 70; 
        armours.remove(0);
    }

    public void addShield(Shield shield){
        total = total + 40; 
        shields.add(shield);
    }
    public void subtractShield(){
        total = total - 40; 
        shields.remove(0);
    }


    public boolean finaliseTransaction(Statistics stats, List<Item> unequippedInventory) { 

        // Compare total with Character's gold.
        if (total > stats.getGold()){
            // Not enough gold
            System.out.println("Not enough gold!");
            return false; 
        }


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
        System.out.println("Purchase successful!");


        return true; 



    }



}
