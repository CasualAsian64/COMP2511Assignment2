package unsw.loopmania;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A backend world.
 *
 * A world can contain many entities, each occupy a square. More than one entity
 * can occupy the same square.
 */
public class LoopManiaWorld {

    public static final int unequippedInventoryWidth = 4;
    public static final int unequippedInventoryHeight = 4;

    /**
     * width of the world in GridPane cells
     */
    private int width;

    /**
     * height of the world in GridPane cells
     */
    private int height;

    /**
     * number of loops the character has completed
     */
    static int numLoops;

    /**
     * Number of loops for when the vampire will respawn
     */
    private int vampireRespawnLoop = 5;

    /**
     * Number of loops for when the zombie will respawn
     */
    private int zombieRespawnLoop = 0;
    /**
     * number of loops the character has completed
     */
    private Goals worldGoals;

    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;

    private Character character;

    // TODO = add more lists for other entities, for equipped inventory items,
    // etc...

    // TODO = expand the range of enemies
    private List<Enemy> enemies;

    // TODO = expand the range of cards
    private List<Card> cardEntities;

    // TODO = expand the range of items
    private List<Entity> unequippedInventoryItems;
    // private List<Item> unequippedInventoryItems;

    // TODO = expand the range of buildings
    private List<Building> buildingEntities;

    //

    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse
     * them
     */
    private List<Pair<Integer, Integer>> orderedPath;

    /**
     * create the world (constructor)
     *  
     * @param width       width of world in number of cells
     * @param height      height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing
     *                    position of path cells in world
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath) {
        this.width = width;
        this.height = height;
        nonSpecifiedEntities = new ArrayList<>();
        character = null;
        enemies = new ArrayList<>();
        cardEntities = new ArrayList<>();
        unequippedInventoryItems = new ArrayList<>();
        numLoops = 0;
        this.orderedPath = orderedPath;
        buildingEntities = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity
     * out of the file
     * 
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    public void setWorldGoals(Goals goal) {
        worldGoals = goal;
    }

    public void setVampireRespawnLoop(int loop) {
        vampireRespawnLoop = loop;
    }

    public void setZombieRespawnLoop(int loop) {
        zombieRespawnLoop = loop;
    }

    public int getVampireRespawnLoop() {
        return vampireRespawnLoop;
    }

    public int getZombieRespawnLoop() {
        return zombieRespawnLoop;
    }

    public void incrementLoops() {
        numLoops++;
    }

    public List<Building> getBuildingEntities() {
        return buildingEntities;
    }

    public int getNumLoops() {
        return numLoops;
    }

    /**
     * add a generic entity (without it's own dedicated method for adding to the
     * world)
     * 
     * @param entity
     */
    public void addEntity(Entity entity) {
        // for adding non-specific entities (ones without another dedicated list)
        // TODO = if more specialised types being added from main menu, add more methods
        // like this with specific input types...
        nonSpecifiedEntities.add(entity);
    }

    /**
     * spawns enemies if the conditions warrant it, adds to world. It currently
     * spawns only slugs
     * 
     * @return list of the enemies to be displayed on screen
     */

    /**
     * Thoughts: 1. Create possiblyGetEnemySpawnPosition() for all types of enemies
     * e.g. possiblyGetSlugSpawnPosition() 2. Create
     * possiblySpawnVampire(),possiblySpawnSlug(), possiblySpawnZombie(). All 3
     * methods are called in the possibleSpawnEnemies. If condition moved into
     * possibleSpawn_____() methods created. Each method returns an enemy that is
     * added to the spawningEnemies list.
     * 
     * @return
     */
    public List<Enemy> possiblySpawnEnemies() {
        Pair<Integer, Integer> pos = possiblyGetEnemySpawnPosition();
        List<Enemy> spawningEnemies = new ArrayList<>();
        if (pos != null) {
            int indexInPath = orderedPath.indexOf(pos);

            PathPosition pathPosition = new PathPosition(indexInPath, orderedPath);
            EnemySelector enemySelector = new EnemySelector();
            Enemy enemy = enemySelector.getEnemy(0, pathPosition, enemies, numLoops, vampireRespawnLoop, zombieRespawnLoop);
            updateRespawnLoop(enemy);
            enemies.add(enemy);
            spawningEnemies.add(enemy);
        }
        return spawningEnemies;
    }

    private void updateRespawnLoop(Enemy enemy) {
        if (enemy.getType().equals("Vampire")) {
            vampireRespawnLoop += 5;
        }
        if (enemy.getType().equals("Zombie")) {
            zombieRespawnLoop += 1;
        }
    }

    /**
     * Kill an enemy
     * 
     * @param enemy enemy to be killed
     */
    private void killEnemy(Enemy enemy) {
        enemy.destroy();
        enemies.remove(enemy);
    }

    /**
     * run the expected battles in the world, based on current world state
     * 
     * @return list of enemies which have been killed
     */
    public List<Enemy> runBattles() {
        // TODO = modify this - currently the character automatically wins all battles
        // without any damage!
        List<Enemy> defeatedEnemies = new ArrayList<Enemy>();
        for (Enemy e : enemies) {
            // Pythagoras: a^2+b^2 < radius^2 to see if within radius
            // TODO = you should implement different RHS on this inequality, based on
            // influence radii and battle radii

            if (Math.pow((character.getX() - e.getX()), 2) + Math.pow((character.getY() - e.getY()), 2) < e
                    .getBattleRadius()) {
                // fight...
                while (e.getHealth() != 0) {
                    e.getAttacked(character.getAttack());
                    character.getAttacked(e.getAttack());
                    System.out.println();
                    System.out.println("Character's health is: " + character.getHealth());
                }
                character.collectRewards(e);
                defeatedEnemies.add(e);
            }
        }
        for (Enemy e : defeatedEnemies) {
            // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from
            // the enemies list
            // if we killEnemy in prior loop, we get
            // java.util.ConcurrentModificationException
            // due to mutating list we're iterating over
            killEnemy(e);
        }
        return defeatedEnemies;
    }

    /**
     * spawn a card in the world and return the card entity
     * 
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public VampireCastleCard loadVampireCard() {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()) {
            // TODO = give some cash/experience/item rewards for the discarding of the
            // oldest card
            removeCard(0);
        }
        VampireCastleCard vampireCastleCard = new VampireCastleCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(vampireCastleCard);
        return vampireCastleCard;
    }

    public ZombiePitCard loadZombieCard() {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()) {
            // TODO = give some cash/experience/item rewards for the discarding of the
            // oldest card
            removeCard(0);
        }
        ZombiePitCard zombiePitCard = new ZombiePitCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(zombiePitCard);
        return zombiePitCard;
    }

    public TowerCard loadTowerCard() {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()) {
            // TODO = give some cash/experience/item rewards for the discarding of the
            // oldest card
            removeCard(0);
        }
        TowerCard TowerCard = new TowerCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(TowerCard);
        return TowerCard;
    }

    public VillageCard loadVillageCard() {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()) {
            // TODO = give some cash/experience/item rewards for the discarding of the
            // oldest card
            removeCard(0);
        }
        VillageCard VillageCard = new VillageCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(VillageCard);
        return VillageCard;
    }

    public BarracksCard loadBarracksCard() {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()) {
            // TODO = give some cash/experience/item rewards for the discarding of the
            // oldest card
            removeCard(0);
        }
        BarracksCard BarracksCard = new BarracksCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(BarracksCard);
        return BarracksCard;
    }

    public TrapCard loadTrapCard() {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()) {
            // TODO = give some cash/experience/item rewards for the discarding of the
            // oldest card
            removeCard(0);
        }
        TrapCard TrapCard = new TrapCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(TrapCard);
        return TrapCard;
    }

    public CampfireCard loadCampfireCard() {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()) {
            // TODO = give some cash/experience/item rewards for the discarding of the
            // oldest card
            removeCard(0);
        }
        CampfireCard CampfireCard = new CampfireCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(CampfireCard);
        return CampfireCard;
    }

    /**
     * remove card at a particular index of cards (position in gridpane of unplayed
     * cards)
     * 
     * @param index the index of the card, from 0 to length-1
     */
    private void removeCard(int index) {
        Card c = cardEntities.get(index);
        int x = c.getX();
        c.destroy();
        cardEntities.remove(index);
        shiftCardsDownFromXCoordinate(x);
    }

    /**
     * spawn a sword in the world and return the sword entity
     * 
     * @return a sword to be spawned in the controller as a JavaFX node
     */
    public Sword addUnequippedSword() {
        // TODO = expand this - we would like to be able to add multiple types of items,
        // apart from swords
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        Sword sword = new Sword(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(sword);
        return sword;
    }

    public Stake addUnequippedStake() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        Stake stake = new Stake(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(stake);
        return stake;
    }

    public Staff addUnequippedStaff() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        Staff staff = new Staff(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(staff);
        return staff;
    }

    public Armour addUnequippedArmour() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        Armour armour = new Armour(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(armour);
        return armour;
    }

    public Helmet addUnequippedHelmet() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        Helmet helmet = new Helmet(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(helmet);
        return helmet;
    }

    public Shield addUnequippedShield() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        Shield shield = new Shield(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(shield);
        return shield;
    }

    public HealthPotion addUnequippedPotion() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        HealthPotion potion = new HealthPotion(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(potion);
        return potion;
    }

    public TheOneRing addUnequippedOneRing() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest
            // sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }

        // now we insert the new sword, as we know we have at least made a slot
        // available...
        TheOneRing oneRing = new TheOneRing(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(oneRing);
        return oneRing;
    }

    /**
     * remove an item by x,y coordinates
     * 
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y) {
        Entity item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        removeUnequippedInventoryItem(item);
    }

    /**
     * run moves which occur with every tick without needing to spawn anything
     * immediately
     */
    public void runTickMoves() {
        character.moveDownPath();
        moveEnemies();
        detectCharacterisOnTile();
        detectEnemyisOnTile();
        // Everytime the character moves, check if the character has acheieved the world
        // goals
        worldGoals.checkGoalsMet(character.getStats(), numLoops);
    }

    // loop through all buildings and see if Character is onTile
    public void detectCharacterisOnTile() {
        for (Building b : buildingEntities) {
            // if building X coordinate == character X coordinate &&
            // building Y coordinate == character Y coordinate
            if (b.getX() == (this.character.getX()) && b.getY() == (this.character.getY())) {
                b.performActionOnCharacter(this.character);

                if (b.getType().equals("HerosCastle")) {
                    System.out.println();
                    System.out.println("The character visited the Hero's Castle");
                    incrementLoops();
                    System.out.println("The number of loops completed is now " + getNumLoops());

                    System.out.println();
                    worldGoals.printAllGoals();
                    System.out.println();

                }
            }
        }
    }

    // loop through all buildings and see if Enemy is onTile
    public void detectEnemyisOnTile() {

        ArrayList<Building> destroyedBuildings = new ArrayList<Building>();
        List<Enemy> defeatedEnemies = new ArrayList<Enemy>();

        for (Building b : buildingEntities) {
            for (Enemy e : enemies) {
                if (b.getX() == (e.getX()) && b.getY() == (e.getY()) && b.getType().equals("Trap")) {
                    boolean enemyKilledByTrap = b.performActionOnEnemy(e);
                    destroyedBuildings.add(b);

                    if (enemyKilledByTrap) {
                        defeatedEnemies.add(e);
                    }
                }
            }
        }

        for (Building b : destroyedBuildings) {
            destroyBuilding(b);
        }
        for (Enemy e : defeatedEnemies) {
            killEnemy(e);
        }
    }

    private void destroyBuilding(Building building) {
        building.destroy();
        buildingEntities.remove(building);
    }

    /**
     * remove an item from the unequipped inventory
     * 
     * @param item item to be removed
     */
    private void removeUnequippedInventoryItem(Entity item) {
        item.destroy();
        unequippedInventoryItems.remove(item);
    }

    /**
     * return an unequipped inventory item by x and y coordinates assumes that no 2
     * unequipped inventory items share x and y coordinates
     * 
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    private Entity getUnequippedInventoryItemEntityByCoordinates(int x, int y) {
        for (Entity e : unequippedInventoryItems) {
            if ((e.getX() == x) && (e.getY() == y)) {
                return e;
            }
        }
        return null;
    }

    /**
     * remove item at a particular index in the unequipped inventory items list
     * (this is ordered based on age in the starter code)
     * 
     * @param index index from 0 to length-1
     */
    private void removeItemByPositionInUnequippedInventoryItems(int index) {
        Entity item = unequippedInventoryItems.get(index);
        item.destroy();
        unequippedInventoryItems.remove(index);
    }

    /**
     * get the first pair of x,y coordinates which don't have any items in it in the
     * unequipped inventory
     * 
     * @return x,y coordinate pair
     */
    private Pair<Integer, Integer> getFirstAvailableSlotForItem() {
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available
        // slot defined by looking row by row
        for (int y = 0; y < unequippedInventoryHeight; y++) {
            for (int x = 0; x < unequippedInventoryWidth; x++) {
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null) {
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    /**
     * shift card coordinates down starting from x coordinate
     * 
     * @param x x coordinate which can range from 0 to width-1
     */
    private void shiftCardsDownFromXCoordinate(int x) {
        for (Card c : cardEntities) {
            if (c.getX() >= x) {
                c.x().set(c.getX() - 1);
            }
        }
    }

    /**
     * move all enemies
     */
    private void moveEnemies() {
        for (Enemy e : enemies) {
            e.move();
        }
    }

    /**
     * get a randomly generated position which could be used to spawn an enemy
     * 
     * @return null if random choice is that wont be spawning an enemy or it isn't
     *         possible, or random coordinate pair if should go ahead
     */
    private Pair<Integer, Integer> possiblyGetEnemySpawnPosition() {
        // TODO = modify this

        // has a chance spawning an enemy on a tile the character isn't on or
        // immediately before or after (currently space required = 2)...
        Random rand = new Random();
        int choice = rand.nextInt(2); // TODO = change based on spec... currently low value for dev purposes...
        // TODO = change based on spec
        // spawn 4 enemies
        if ((choice == 0) && (enemies.size() < 4)) {
            List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
            int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
            // inclusive start and exclusive end of range of positions not allowed
            int startNotAllowed = (indexPosition - 2 + orderedPath.size()) % orderedPath.size();
            int endNotAllowed = (indexPosition + 3) % orderedPath.size();
            // note terminating condition has to be != rather than < since wrap around...
            for (int i = endNotAllowed; i != startNotAllowed; i = (i + 1) % orderedPath.size()) {
                orderedPathSpawnCandidates.add(orderedPath.get(i));
            }

            // choose random choice
            Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates
                    .get(rand.nextInt(orderedPathSpawnCandidates.size()));

            return spawnPosition;
        }
        return null;
    }

    /**
     * remove a card by its x, y coordinates
     * 
     * @param cardNodeX     x index from 0 to width-1 of card to be removed
     * @param cardNodeY     y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     */
    public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX,
            int buildingNodeY) {
        // start by getting card
        Card card = null;
        for (Card c : cardEntities) {
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)) {
                card = c;
                break;
            }
        }

        Building b = null;

        // Call card.getType() to determine which building to create and add

        ///////////////

        if (card.getCardType().equals("VampireCastleCard")) {
            VampireCastleBuilding newBuilding = new VampireCastleBuilding(new SimpleIntegerProperty(buildingNodeX),
                    new SimpleIntegerProperty(buildingNodeY));
            buildingEntities.add(newBuilding);

            // Remove the card
            card.destroy();
            cardEntities.remove(card);
            shiftCardsDownFromXCoordinate(cardNodeX);

            // Return the building to be added
            return newBuilding;
        }

        if (card.getCardType().equals("ZombiePitCard")) {
            ZombiePit newBuilding = new ZombiePit(new SimpleIntegerProperty(buildingNodeX),
                    new SimpleIntegerProperty(buildingNodeY));
            buildingEntities.add(newBuilding);

            // Remove the card
            card.destroy();
            cardEntities.remove(card);
            shiftCardsDownFromXCoordinate(cardNodeX);

            // Return the building to be added
            return newBuilding;
        }

        if (card.getCardType().equals("TowerCard")) {
            Tower newBuilding = new Tower(new SimpleIntegerProperty(buildingNodeX),
                    new SimpleIntegerProperty(buildingNodeY));
            buildingEntities.add(newBuilding);

            // Remove the card
            card.destroy();
            cardEntities.remove(card);
            shiftCardsDownFromXCoordinate(cardNodeX);

            // Return the building to be added
            return newBuilding;
        }

        // Village
        if (card.getCardType().equals("VillageCard")) {
            Village newBuilding = new Village(new SimpleIntegerProperty(buildingNodeX),
                    new SimpleIntegerProperty(buildingNodeY));
            buildingEntities.add(newBuilding);

            // Village newBuilding = createVillage(buildingNodeX, buildingNodeY);

            // Remove the card
            card.destroy();
            cardEntities.remove(card);
            shiftCardsDownFromXCoordinate(cardNodeX);

            // Return the building to be added
            return newBuilding;
        }

        if (card.getCardType().equals("BarracksCard")) {
            Barracks newBuilding = new Barracks(new SimpleIntegerProperty(buildingNodeX),
                    new SimpleIntegerProperty(buildingNodeY));
            buildingEntities.add(newBuilding);

            // Remove the card
            card.destroy();
            cardEntities.remove(card);
            shiftCardsDownFromXCoordinate(cardNodeX);

            // Return the building to be added
            return newBuilding;
        }

        if (card.getCardType().equals("TrapCard")) {
            Trap newBuilding = new Trap(new SimpleIntegerProperty(buildingNodeX),
                    new SimpleIntegerProperty(buildingNodeY));
            buildingEntities.add(newBuilding);

            // Remove the card
            card.destroy();
            cardEntities.remove(card);
            shiftCardsDownFromXCoordinate(cardNodeX);

            // Return the building to be added
            return newBuilding;
        }

        if (card.getCardType().equals("CampfireCard")) {
            Campfire newBuilding = new Campfire(new SimpleIntegerProperty(buildingNodeX),
                    new SimpleIntegerProperty(buildingNodeY));
            buildingEntities.add(newBuilding);

            // Remove the card
            card.destroy();
            cardEntities.remove(card);
            shiftCardsDownFromXCoordinate(cardNodeX);

            // Return the building to be added
            return newBuilding;
        }

        return b;

    }

}
