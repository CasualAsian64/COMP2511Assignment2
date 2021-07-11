package unsw.loopmania;

import java.util.ArrayList;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private ArrayList<Weapon> weapons;
    private Weapon equippedWeapon;
    private ArrayList<Building> buildingBuffs;
    private ArrayList<AlliedSoldier> allies;

    // TODO = potentially implement relationships between this class and other classes
    
    public Character(PathPosition position) {
        super(position);
        stats = new Statistics(100, 5, 0, 0, 0);
        weapons = new ArrayList<Weapon>();
        equippedWeapon = null;
        allies = new ArrayList<AlliedSoldier>();
    }

    public void move() {

    }

    public void collectRewards(Enemy e){
        Statistics eStats = e.getStats();
        stats.setGold(eStats.getGold() + stats.getGold());
        stats.setExp(eStats.getExp() + stats.getExp());
        System.out.println("The characters gold: " + stats.getGold());
        System.out.println("The characters exp: " + stats.getExp());
    }

    @Override
    public Statistics getStats() {
        return stats;
    }

    public ArrayList<AlliedSoldier> getAllies(){
        return allies;
    }

}
