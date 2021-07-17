package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;
import org.json.JSONArray;

import javafx.beans.property.IntegerProperty;
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
    public static final int equippedInventoryLength = 4;
    private static final int SLUG = 0;
    private static final int ZOMBIE = 1;
    private static final int VAMPIRE = 2;

    private static final int GOLDRANDOMISER = 50;
    private static final int ITEMRANDOMISER = 30;
    private static final int CARDRANDOMISER = 20;
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
    private int zombieRespawnLoop = 1;
    /**
     * number of loops the character has completed
     */
    private Goals worldGoals;

    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;

    private Character character;

    private List<Enemy> enemies;

    private List<Card> cardEntities;

    private List<Item> unequippedInventoryItems;
    private List<Item> equippedInventoryItems;

    private List<Building> buildingEntities;

    private List<Gold> goldEntities;

    private List<String> allRareItems;

    private boolean gameOver = false; 
  
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private boolean gameWon = false; 

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }


    private SimpleIntegerProperty loopsValue = new SimpleIntegerProperty(this, "loopsValue");


    public IntegerProperty LoopsValueProperty(){
        return loopsValue;
    }

    public int getLoopsProperty(){
        return loopsValue.get();
    }

    public void incrementLoopsProperty(){ 
        this.loopsValue.set(getLoopsProperty()+1);
    }

    public List<Item> getUnequippedInventoryItems() {
        return unequippedInventoryItems;
    }

    public List<Card> getCardEntities() {
        return cardEntities;
    }

    //  * list of x,y coordinate pairs in the order by which moving entities traverse
    //  * them
    //  */
    private List<Pair<Integer, Integer>> orderedPath;

    private int shopCounter = 1;
    private int shopIncrement = 1;

    private int castleX;
    private int castleY;
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
        equippedInventoryItems = new ArrayList<>();
        for (int i = 0; i < equippedInventoryLength; i += 1) {
            equippedInventoryItems.add(null);
        }
        numLoops = 0;
        this.orderedPath = orderedPath;
        buildingEntities = new ArrayList<>();
        goldEntities = new ArrayList<>();
        allRareItems = new ArrayList<>();
    }

    public List<Pair<Integer, Integer>> getOrderedPath() {
        return orderedPath;
    }

    public void increaseShopLoops() {
        shopCounter += shopIncrement;
        shopIncrement += 1;
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

    public Character getCharacter() {
        return character;
    }

    public Goals getWorldGoals() {
        return worldGoals;
    }

    public void setWorldGoals(Goals goal) {
        worldGoals = goal;
    }

    public void incrementLoops() {
        numLoops++;
        incrementLoopsProperty();
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
    private boolean zombieSpawned = false;
    private boolean vampireSpawned = false;
    private boolean specialEnemySpawned = false;

    public List<Enemy> possiblySpawnEnemies() {
        int enemySelection = SLUG;
        if (numLoops % 5 == 0 && !checkVampireSpawned() && numLoops == vampireRespawnLoop && checkVampireBuilding()) {
            enemySelection = VAMPIRE;
            vampireRespawnLoop = numLoops + 5;
            vampireSpawned = true;
            specialEnemySpawned = true;
        } else if (!checkZombieSpawned() && numLoops == zombieRespawnLoop && checkZombiePit()) {
            enemySelection = ZOMBIE;
            zombieRespawnLoop = numLoops + 1;
            zombieSpawned = true;
            specialEnemySpawned = true;
        }
        List<Pair<Integer, Integer>> positions = possiblyGetEnemySpawnPosition(enemySelection);
        List<Enemy> spawningEnemies = new ArrayList<>();
        if (positions != null) {
            for (Pair<Integer, Integer> pos : positions) {
                int indexInPath = orderedPath.indexOf(pos);
                PathPosition pathPosition = new PathPosition(indexInPath, orderedPath);
                EnemySelector enemySelector = new EnemySelector();
                Enemy enemy = enemySelector.getEnemy(enemySelection, pathPosition);
                enemies.add(enemy);
                spawningEnemies.add(enemy);
            }
        }
        return spawningEnemies;
    }

    public boolean checkVampireBuilding() {
        for (Building b : buildingEntities) {
            if (b.getType().equals("VampireCastle")) {
                return true;
            }
        }
        return false;
    }

    public boolean checkZombiePit() {
        for (Building b : buildingEntities) {
            if (b.getType().equals("ZombiePit")) {
                return true;
            }
        }
        return false;
    }

    public boolean checkVampireSpawned() {
        for (Enemy e: enemies) {
            if (e.getType().equals("Vampire")){
                return true;
            }
        }
        return false;
    }

    public boolean checkZombieSpawned() {
        for (Enemy e: enemies) {
            if (e.getType().equals("Zombie")){
                return true;
            }
        }
        return false;
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
        boolean buffed = false;

        for (Building b: buildingEntities) {
            if (b.getType().equals("Campfire")) {

                if (Math.pow((character.getX() - b.getX()), 2) + Math.pow((character.getY() - b.getY()), 2) < 4) {
                    buffed = true; 
                    character.getStats().setAttack(character.getAttack()*2);
                    break;
                }
            }
        }

        List<Enemy> defeatedEnemies = new ArrayList<Enemy>();
        for (Enemy e : enemies) {
            // Pythagoras: a^2+b^2 < radius^2 to see if within radius
            // TODO = you should implement different RHS on this inequality, based on
            // influence radii and battle radii

            if (Math.pow((character.getX() - e.getX()), 2) + Math.pow((character.getY() - e.getY()), 2) < e
                    .getBattleRadius()) {
                // fight...
                // if enemy instanceof zombie && allied exists: attack allied
                while (true) {
                    character.attack(e, e.getStats());
                    if (e.getHealth() == 0) {
                        break;
                    }
                    e.attack(character, character.getStats());
                    if (character.getHealth() == 0) {
                        if(checkTheOneRingInUnequippedItems()) {
                            for (Item i : unequippedInventoryItems) {
                                if (i.getType().equals("OneRing")) {
                                    removeUnequippedInventoryItem(i);
                                    System.out.println("The One Ring was destroyed!");
                                    break;
                                }
                            }
                            Statistics stats = character.getStats();
                            stats.setHealth(100);
                        } else {
                            triggerGameOver();
                        }
                        break;
                        // end the game
                    }

                }
                character.collectRewards(e);
                defeatedEnemies.add(e);
            }
        }
        for (Enemy e : defeatedEnemies) {
            killEnemy(e);
        }

        // Revert back to normal damage after battle has finished. 
        if (buffed) {
            character.getStats().setAttack(character.getAttack()/2);
        }

        return defeatedEnemies;
    }

    private void triggerGameOver() {
        // signal to loop mania world controller to end the game
        setGameOver(true);
    }

    public void loadCard() {
        if (cardEntities.size() >= getWidth()) {
            Card card = cardEntities.get(0);
            card.removeCardAward(character);
            addUnequippedItem();
            removeCard(0);
        }
        Random randomCard = new Random();
        int randCard = randomCard.nextInt(CARDRANDOMISER);
        CardSelector cardSelector = new CardSelector();
        Card card = cardSelector.getCard(randCard, cardEntities.size());
        if(card != null) {
            cardEntities.add(card);
        }
    }

    public Card getCard(int cardNodeX, int cardNodeY) {
        // start by getting card
        Card card = null;
        for (Card c : cardEntities) {
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)) {
                card = c;
                break;
            }
        }
        return card;
    }
    public boolean checkTheOneRingInUnequippedItems() {
        boolean state = false;
        for (Item i : unequippedInventoryItems) {
            if (i != null && i.getType().equals("OneRing")) {
                return true;
            }
        }
        return state;
    }
    public void addUnequippedItem() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            //Give some reward for removing the card
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        ItemSelector itemSelector = new ItemSelector();
        Random randomItem = new Random();
        int randItem = randomItem.nextInt(ITEMRANDOMISER);
        Item item = itemSelector.getItem(randItem, allRareItems, new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()), checkTheOneRingInUnequippedItems());
        if (item != null) {
            unequippedInventoryItems.add(item);
        }
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
     * remove an item by x,y coordinates
     * 
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y) {
        Item item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        removeUnequippedInventoryItem(item);
    }

    public boolean checkItemInEquippedInventory(String itemType) {
        boolean state = false;
        for (Item i : equippedInventoryItems) {
            if (i != null && i.getType().equals(itemType)) {
                return true;
            }
        }
        return state;
    }

    public Item convertCardToItemByCoordinates(int nodeX, int nodeY, int x, int y) {
        Item item = null;
        for (Item i : unequippedInventoryItems) {
            if ((i.getX() == nodeX) && (i.getY() == nodeY)) {
                item = i;
                break;
            }
        }
        System.out.println(item.getItemType());
        if (item != null) {
            switch (x) {
                case 0:
                    if (item.getItemType().equals("Weapon") && !checkItemInEquippedInventory(item.getType())) {
                        equippedInventoryItems.set(x, item);
                        System.out.println("Weapon: " + item.getType() + " has been added");
                    } else {
                        item = null;
                    }
                    break;
                case 1:
                    if (item.getItemType().equals("Weapon") && !checkItemInEquippedInventory(item.getType())) {
                        equippedInventoryItems.set(x, item);
                        System.out.println("Weapon: " + item.getType() + " has been added");
                    } else {
                        item = null;
                    }
                    break;
                case 2:
                    if (item.getItemType().equals("Equipment") && !checkItemInEquippedInventory(item.getType())) {
                        equippedInventoryItems.set(x, item);
                        System.out.println("Equipment: " + item.getType() + " has been added");
                    } else {
                        item = null;
                    }
                    break;
                case 3:
                    if (item.getItemType().equals("Equipment") && !checkItemInEquippedInventory(item.getType())) {
                        equippedInventoryItems.set(x, item);
                        System.out.println("Equipment: " + item.getType() + " has been added");
                    } else {
                        item = null;
                    }
                    break;
            }
            
        }
        return item;
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
        detectEnemyInRadius();
        // Everytime the character moves, check if the character has acheieved the world
        // goals
        if (worldGoals.checkGoalsMet(character.getStats(), numLoops)) {
            setGameWon(true);
        }
    }

    // loop through all buildings and gold entities and see if Character is onTile
    public void detectCharacterisOnTile() {
        List<Gold> destroyedGoldEntities = new ArrayList<>();
        for (Building b : buildingEntities) {
            // if building X coordinate == character X coordinate &&
            // building Y coordinate == character Y coordinate
            if (b.getX() == (character.getX()) && b.getY() == (character.getY())) {
                b.performActionOnCharacter(this.character);

                if (b.getType().equals("HerosCastle")) {
                    if (!zombieSpawned && numLoops != 0) {
                        zombieRespawnLoop += 1;
                    }

                    if (!vampireSpawned && numLoops % 5 == 0 && numLoops != 0) {
                        vampireRespawnLoop += 5;
                    }
                    incrementLoops();
                    increaseShopLoops();
                    if (numLoops == shopCounter) {
                        System.out.println("Shop is opened");
                    }
                    System.out.println("The number of loops completed is now " + getNumLoops());
                    System.out.println();
                    worldGoals.printAllGoals();
                    System.out.println();
                    System.out.println("Zombie respawn loop: " + zombieRespawnLoop);
                    System.out.println("Vampire respawn loop: " + vampireRespawnLoop);
                    System.out.println();
                }
            }
        }
        for (Gold g : goldEntities) {
            if (g.getX() == (character.getX()) && g.getY() == (character.getY())) {
                Statistics stats = character.getStats();
                stats.setGold(stats.getGold() + g.getGold());
                System.out.println("Character's new gold: " + stats.getGold());
                destroyedGoldEntities.add(g);
            }
        }
        
        for (Gold g : destroyedGoldEntities) {
            destroyGold(g);
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

    public void destroyGold(Gold gold) {
        gold.destroy();
        goldEntities.remove(gold);
    }
	public void detectEnemyInRadius() {

        ArrayList<Enemy> enemiesInRange = new ArrayList<Enemy>();
        ArrayList<Enemy> killedEnemies = new ArrayList<Enemy>();

        ArrayList<Building> campfires = new ArrayList<Building>();

        for (Building b : buildingEntities) {

            if (b.getType().equals("Campfire")) {
                campfires.add(b);
            }

            for (Enemy e: enemies) { 

                // TOWER
                // Pythagoras calculation to see if enemy in range. 
                if (b.getType().equals("Tower") && Math.pow((b.getX() - e.getX()), 2) + Math.pow((b.getY() - e.getY()), 2) < e.getBattleRadius()) {
                    
                    enemiesInRange.add(e);

                    boolean enemyKilledByTower = b.performActionOnEnemy(e);

                    if (enemyKilledByTower) {
                        killedEnemies.add(e);
                    }
                }
                
                // Campfire
                if (b.getType().equals("Campfire") && e.getType().equals("Vampire") && Math.pow((b.getX() - e.getX()), 2) + Math.pow((b.getY() - e.getY()), 2) < e.getBattleRadius()) {
                    e.reverseDirection();
                }
            }
        }

        // Loop through enemies killed by tower and 
        for (Enemy e : killedEnemies) {
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
    private Item getUnequippedInventoryItemEntityByCoordinates(int x, int y) {
        for (Item i : unequippedInventoryItems) {
            if ((i.getX() == x) && (i.getY() == y)) {
                return i;
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
    Pair<Integer, Integer> goldPosition;

    private List<Pair<Integer, Integer>> possiblyGetEnemySpawnPosition(int enemySelection) {
        Random rand = new Random();
        int choice = rand.nextInt(2);
        // spawn 4 enemies
        if (specialEnemySpawned) {
            choice = 0;
        }
        List<Pair<Integer, Integer>> spawnPositions = new ArrayList<>();
        if ((choice == 0) && ((enemies.size() < 4) || specialEnemySpawned)) {
            if (specialEnemySpawned) {
                specialEnemySpawned = false;
            }
            Pair<Integer, Integer> spawnPosition = null;
            if (enemySelection == 0) {
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
                spawnPosition = orderedPathSpawnCandidates.get(rand.nextInt(orderedPathSpawnCandidates.size()));
                goldPosition = orderedPathSpawnCandidates.get(rand.nextInt(orderedPathSpawnCandidates.size()));
                spawnPositions.add(spawnPosition);
            } 
            if (enemySelection == 1) {
                spawnPositions = getZombieSpawn();
            }

            if (enemySelection == 2) {
                spawnPositions = getVampireSpawn();
            }
            return spawnPositions;
        }
        return null;
    }

    public List<Pair<Integer, Integer>> getZombieSpawn() {
        List<Pair<Integer, Integer>> zombieSpawns = new ArrayList<>();
        List<Pair<Integer, Integer>> allZombieBuildings = new ArrayList<>();
        for (Building b : buildingEntities) {
            if (b.getType().equals("ZombiePit")) {
                allZombieBuildings.add(new Pair<Integer, Integer>(b.getX(), b.getY()));
            }
        }
        for (Pair<Integer, Integer> building : allZombieBuildings) {
            for (int i = 0; i < orderedPath.size(); i++) {
                Pair<Integer, Integer> cell = orderedPath.get(i);
                if ((cell.getValue0() == building.getValue0() + 1 && cell.getValue1() == building.getValue1()) || (cell.getValue0() == building.getValue0() - 1 && cell.getValue1() == building.getValue1())  
                    || (cell.getValue0() == building.getValue0() && cell.getValue1() == building.getValue1() + 1)  || (cell.getValue0() == building.getValue0() && cell.getValue1() == building.getValue1() - 1)){
                    zombieSpawns.add(cell);
                    break;
                }
            }
        }
        return zombieSpawns;
    }

    public List<Pair<Integer, Integer>> getVampireSpawn() {
        List<Pair<Integer, Integer>> vampireSpawns = new ArrayList<>();
        List<Pair<Integer, Integer>> allVampireBuildings = new ArrayList<>();
        for (Building b : buildingEntities) {
            if (b.getType().equals("VampireCastle")) {
                allVampireBuildings.add(new Pair<Integer, Integer>(b.getX(), b.getY()));
                System.out.println("Vampire pit building at (" + b.getX() + "," + b.getY() + ")");
            }
        }
        for (Pair<Integer, Integer> building : allVampireBuildings) {
            for (int i = 0; i < orderedPath.size(); i++) {
                Pair<Integer, Integer> cell = orderedPath.get(i);
                if ((cell.getValue0() == building.getValue0() + 1 && cell.getValue1() == building.getValue1()) || (cell.getValue0() == building.getValue0() - 1 && cell.getValue1() == building.getValue1())  
                    || (cell.getValue0() == building.getValue0() && cell.getValue1() == building.getValue1() + 1)  || (cell.getValue0() == building.getValue0() && cell.getValue1() == building.getValue1() - 1)){
                    System.out.println("Vampire spawned at (" + cell.getValue0() + "," + cell.getValue1() + ")");
                    vampireSpawns.add(cell);
                    break;
                }
            }
        }
        return vampireSpawns;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////
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
        Building building = null;
        BuildingSelector buildingSelector = new BuildingSelector();
        building = buildingSelector.getBuilding(card.getCardType(), new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY), 
                                                checkBuildingOnPath(buildingNodeX, buildingNodeY), checkBuildingNextToPath(buildingNodeX, buildingNodeY));
        if (building != null) {
            buildingEntities.add(building);
            card.destroy();
            cardEntities.remove(card);
            shiftCardsDownFromXCoordinate(cardNodeX);
        } 
        return building;

    }

    public boolean checkBuildingOnPath(int x, int y) {
        for (int i = 0; i < orderedPath.size(); i++) {
            Pair<Integer,Integer> cell = orderedPath.get(i);
            if (cell.getValue0() == x && cell.getValue1() == y) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBuildingNextToPath(int x, int y) {
        for (int i = 0; i < orderedPath.size(); i++) {
            Pair<Integer,Integer> cell = orderedPath.get(i);
            if ((cell.getValue0() == x + 1 && cell.getValue1() == y) || (cell.getValue0() == x - 1 && cell.getValue1() == y) ||
                (cell.getValue0() == x && cell.getValue1() == y + 1) || (cell.getValue0() == x && cell.getValue1() == y - 1)) {
                return true;
            }
        }
        return false;
    }

    public void setRareItems(JSONArray jsonRareItems) {
        for (int i = 0; i < jsonRareItems.length(); i++) {
            allRareItems.add(jsonRareItems.getString(i));
        }
    }

    public void setCastleCoordinates(int x, int y) {
        castleX = x;
        castleY = y;
    }

    public int getCastleX() {
        return castleX;
    }

    public int getCastleY() {
        return castleY;
    }

    public Gold possiblySpawnGold() {
        Random randSpawn = new Random();
        int goldSpawn = randSpawn.nextInt(GOLDRANDOMISER);
        if (goldPosition != null && goldSpawn == 0) {
            int indexInPath = orderedPath.indexOf(goldPosition);
            PathPosition pathPosition = new PathPosition(indexInPath, orderedPath);
            Statistics stats = new Statistics(0, 0, 0, 0, 100);
            Gold gold = new Gold(pathPosition, stats);
            goldEntities.add(gold);
            return gold;
        }
        return null;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addBuilding(Building building) {
        buildingEntities.add(building);
    }

    public Item getItem(int x, int y) {
        Item item = null;
        for (Item i : unequippedInventoryItems) {
            if ((i.getX() == x) && (i.getY() == y)) {
                item = i;
                break;
            }
        }
        return item;
    }

} 
