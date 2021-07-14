package unsw.loopmania;

import java.util.ArrayList;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private ArrayList<Weapon> weapons;
    private Weapon equippedWeapon;
    private Equipment equipment;
    private ArrayList<Building> buildingBuffs;
    private ArrayList<AlliedSoldier> allies;

    // TODO = potentially implement relationships between this class and other classes
    
    public Character(PathPosition position) {
        super(position);
        stats = new Statistics(100, 5, 2, 0, 0);
        weapons = new ArrayList<Weapon>();
        equippedWeapon = null;
        equipment = null;
        allies = new ArrayList<AlliedSoldier>();

    }

    public void move() {

    }

    public void collectRewards(Enemy e){
        Statistics eStats = e.getStats();
        stats.setGold(eStats.getGold() + stats.getGold());
        stats.setExp(eStats.getExp() + stats.getExp());
        //System.out.println("The characters gold: " + stats.getGold());
        //System.out.println("The characters exp: " + stats.getExp());
    }

    @Override
    public Statistics getStats() {
        return stats;
    }

    @Override
    public void getAttacked(int attack) {
        Statistics stats = this.getStats();
        // Check for weapons
        // Check for equipment
        int totalDefense = 0;
        // Increase defense for character if equipped with an equipment item
        if (equipment != null) {
            totalDefense += equipment.getDefense();
            //attack = equipment.reduceAttack(attack);
        }
        
        int totalAttack = attack - totalDefense;
        if (totalAttack < 0) {
            totalAttack = 0;
        }
        int health = stats.getHealth() - totalAttack;
        if (health < 0) {
            health = 0;
        }
        stats.setHealth(health);
    }
  
    public ArrayList<AlliedSoldier> getAllies(){
        return allies;
    }

}
