package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import org.javatuples.Pair;
import org.json.JSONArray;

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
    private static final int SLUG = 0;
    private static final int ZOMBIE = 1;
    private static final int VAMPIRE = 2;
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

    // TODO = add more lists for other entities, for equipped inventory items,
    // etc...
    private Item equippedWeapon;

    // TODO = expand the range of enemies
    private List<Enemy> enemies;

    // TODO = expand the range of cards
    private List<Card> cardEntities;

    // TODO = expand the range of items
    private List<Entity> unequippedInventoryItems;
    // private List<Item> unequippedInventoryItems;

    // TODO = expand the range of buildings
    private List<Building> buildingEntities;

    private List<Gold> goldEntities;

    private List<String> allRareItems;

    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse
     * them
     */
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
        numLoops = 0;
        this.orderedPath = orderedPath;
        buildingEntities = new ArrayList<>();
        goldEntities = new ArrayList<>();
        allRareItems = new ArrayList<>();
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

    public void setWorldGoals(Goals goal) {
        worldGoals = goal;
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
        for (Enemy e : enemies) {
            if (e.getType().equals("Vampire")) {
                return true;
            }
        }
        return false;
    }

    public boolean checkZombieSpawned() {
        for (Enemy e : enemies) {
            if (e.getType().equals("Zombie")) {
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

    private void soldierEnemyBattle(AlliedSoldier soldier, Enemy e, ArrayList<AlliedSoldier> toRemoveAllies) {
        while (soldier.getHealth() > 0) {
            soldier.attack(e.getStats());
            if (e.getHealth() == 0) {
                // a dead enemy can no longer attack
                break;
            }
            e.attack(soldier.getStats());
            System.out.println("Soldier's health is: " + soldier.getHealth());

            if (e instanceof Zombie) {
                Zombie zombie = (Zombie) e;
                zombie.turnSoldier(soldier);
                toRemoveAllies.add(soldier);
                // break since the allied soldier can no longer fight the enemy
                break;
            } else if (soldier.getHealth() == 0) {
                toRemoveAllies.add(soldier);
            }
        }
    }

    private void alliedZombieCharacterBattle(AlliedSoldier soldier) {
        while (soldier.getHealth() > 0) {
            character.attack(soldier.getStats());
            System.out.println("Soldier's - zombie - health is: " + soldier.getHealth());
            // the allied-zombie has been killed, it can no longer attack
            // therefore, break the loop
            if (soldier.getHealth() == 0) {
                System.out.println("soldier is killed ");
                break;
            }
            soldier.attack(character.getStats());
            // if (character.getHealth() == 0) {
            // break;
            // // end the game
            // }
        }
    }

    private void enemyCharacterBattle(Enemy e) {
        // condition e.getHealth() > 0 so that if enemy has been killed by soldier, this
        // loop
        // will not run
        while (true) {
            character.attack(e.getStats());
            if (e.getHealth() == 0) {
                break;
            }
            e.attack(character.getStats());
            // if (character.getHealth() == 0) {
            // break;
            // // end the game
            // }
            System.out.println();
            System.out.println("Character's health is: " + character.getHealth());
        }
    }

    /**
     * run the expected battles in the world, based on current world state
     * 
     * @return list of enemies which have been killed
     */
    public List<Enemy> runBattles() {
        // TODO = modify this - currently the character automatically wins all battles
        // without any damage!

        // Before simulating combat, detect if the character is in radius of any
        // campfire
        // for now, the radius of the campfire is 4

        //
        boolean buffed = false;

        for (Building b : buildingEntities) {
            if (b.getType().equals("Campfire")) {

                if (Math.pow((character.getX() - b.getX()), 2) + Math.pow((character.getY() - b.getY()), 2) < 4) {
                    buffed = true;
                    character.getStats().setAttack(character.getAttack() * 2);
                    break;
                }
            }
        }

        List<Enemy> defeatedEnemies = new ArrayList<Enemy>();
        ArrayList<AlliedSoldier> toRemoveAllies = new ArrayList<AlliedSoldier>();
        for (Enemy e : enemies) {
            // Pythagoras: a^2+b^2 < radius^2 to see if within radius
            // TODO = you should implement different RHS on this inequality, based on
            // influence radii and battle radii

            if (Math.pow((character.getX() - e.getX()), 2) + Math.pow((character.getY() - e.getY()), 2) < e
                    .getBattleRadius()) {
                if (character.alliedSoldierExists()) {
                    AlliedSoldier soldier = character.getAnAlliedSoldier();
                    soldierEnemyBattle(soldier, e, toRemoveAllies);
                    if (soldier.getIsZombie()) {
                        alliedZombieCharacterBattle(soldier);
                    }
                    enemyCharacterBattle(e);
                }
                character.collectRewards(e);
                defeatedEnemies.add(e);
            }
        }

        character.removeSoldiers(toRemoveAllies);

        for (Enemy e : defeatedEnemies) {
            // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from
            // the enemies list
            // if we killEnemy in prior loop, we get
            // java.util.ConcurrentModificationException
            // due to mutating list we're iterating over
            killEnemy(e);
        }

        // Revert back to normal damage after battle has finished.
        if (buffed) {
            character.getStats().setAttack(character.getAttack() / 2);
        }

        return defeatedEnemies;
    }

    public Card loadCard() {
        if (cardEntities.size() >= getWidth()) {
            Card card = cardEntities.get(0);
            // card.removeCardAward(character);
            removeCard(0);
        }
        Random randomCard = new Random();
        int randCard = randomCard.nextInt(12);
        CardSelector cardSelector = new CardSelector();
        Card card = cardSelector.getCard(randCard, cardEntities.size());
        if (card != null) {
            cardEntities.add(card);
        }
        return card;
    }

    public Card getCard(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
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

    public Item addUnequippedItem() {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // Give some reward for removing the card
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        // System.out.println("First available slot at: " +
        // firstAvailableSlot.getValue0() + "," + firstAvailableSlot.getValue1());
        ItemSelector itemSelector = new ItemSelector();
        Random randomItem = new Random();
        int randItem = randomItem.nextInt(12);
        // Change true later
        Item item = itemSelector.getItem(randItem, allRareItems,
                new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        if (item != null) {
            unequippedInventoryItems.add(item);
        }
        return item;
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
        detectEnemyInRadius();
        // Everytime the character moves, check if the character has acheieved the world
        // goals
        worldGoals.checkGoalsMet(character.getStats(), numLoops);
    }

    // loop through all buildings and gold entities and see if Character is onTile
    public void detectCharacterisOnTile() {
        for (Building b : buildingEntities) {
            // if building X coordinate == character X coordinate &&
            // building Y coordinate == character Y coordinate
            if (b.getX() == (character.getX()) && b.getY() == (character.getY())) {
                b.performActionOnCharacter(this.character);

                if (b.getType().equals("HerosCastle")) {
                    System.out.println();
                    System.out.println("The character visited the Hero's Castle");
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
                destroyGold(g);
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

    public void destroyGold(Gold gold) {
        gold.destroy();
        goldEntities.remove(gold);
    }

    public void detectEnemyInRadius() {

        ArrayList<Enemy> enemiesInRange = new ArrayList<Enemy>();
        ArrayList<Enemy> killedEnemies = new ArrayList<Enemy>();
        ArrayList<Enemy> vampires = new ArrayList<Enemy>();

        ArrayList<Building> campfires = new ArrayList<Building>();

        for (Building b : buildingEntities) {

            if (b.getType().equals("Campfire")) {
                campfires.add(b);
            }

            for (Enemy e : enemies) {

                // TOWER
                // Pythagoras calculation to see if enemy in range.
                if (b.getType().equals("Tower") && Math.pow((b.getX() - e.getX()), 2)
                        + Math.pow((b.getY() - e.getY()), 2) < e.getBattleRadius()) {

                    enemiesInRange.add(e);

                    boolean enemyKilledByTower = b.performActionOnEnemy(e);

                    if (enemyKilledByTower) {
                        killedEnemies.add(e);
                    }
                }

                // Campfire
                if (b.getType().equals("Campfire") && e.getType().equals("Vampire")
                        && Math.pow((b.getX() - e.getX()), 2) + Math.pow((b.getY() - e.getY()), 2) < e
                                .getBattleRadius()) {
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
                if ((cell.getValue0() == building.getValue0() + 1 && cell.getValue1() == building.getValue1())
                        || (cell.getValue0() == building.getValue0() - 1 && cell.getValue1() == building.getValue1())
                        || (cell.getValue0() == building.getValue0() && cell.getValue1() == building.getValue1() + 1)
                        || (cell.getValue0() == building.getValue0() && cell.getValue1() == building.getValue1() - 1)) {
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
                if ((cell.getValue0() == building.getValue0() + 1 && cell.getValue1() == building.getValue1())
                        || (cell.getValue0() == building.getValue0() - 1 && cell.getValue1() == building.getValue1())
                        || (cell.getValue0() == building.getValue0() && cell.getValue1() == building.getValue1() + 1)
                        || (cell.getValue0() == building.getValue0() && cell.getValue1() == building.getValue1() - 1)) {
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
<<<<<<< HEAD

        Building b = null;

        if (card.getCardType().equals("VampireCastleCard")) {
            if (!checkBuildingOnPath(buildingNodeX, buildingNodeY)) {
                b = new VampireCastle(new SimpleIntegerProperty(buildingNodeX),
                        new SimpleIntegerProperty(buildingNodeY));

                buildingEntities.add(b);
                card.destroy();
                cardEntities.remove(card);
                shiftCardsDownFromXCoordinate(cardNodeX);
            } else {
                for (Card c : cardEntities) {
                    System.out.println(c.getCardType());
                }
            }

            // Return the building to be added
            return b;
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
=======
        Building building = null;
        BuildingSelector buildingSelector = new BuildingSelector();
        building = buildingSelector.getBuilding(card.getCardType(), new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY), 
                                                checkBuildingOnPath(buildingNodeX, buildingNodeY), checkBuildingNextToPath(buildingNodeX, buildingNodeY));
        if (building != null) {
            buildingEntities.add(building);
>>>>>>> origin/main
            card.destroy();
            cardEntities.remove(card);
            shiftCardsDownFromXCoordinate(cardNodeX);
        } 
        return building;

    }

    public boolean checkBuildingOnPath(int x, int y) {
        for (int i = 0; i < orderedPath.size(); i++) {
            Pair<Integer, Integer> cell = orderedPath.get(i);
            if (cell.getValue0() == x && cell.getValue1() == y) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBuildingNextToPath(int x, int y) {
        for (int i = 0; i < orderedPath.size(); i++) {
            Pair<Integer, Integer> cell = orderedPath.get(i);
            if ((cell.getValue0() == x + 1 && cell.getValue1() == y)
                    || (cell.getValue0() == x - 1 && cell.getValue1() == y)
                    || (cell.getValue0() == x && cell.getValue1() == y + 1)
                    || (cell.getValue0() == x && cell.getValue1() == y - 1)) {
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

    <<<<<<<HEAD

    public Item convertToItem(int nodeX, int nodeY) {
        for (Entity item : unequippedInventoryItems) {
            if (nodeX == item.getX() && nodeY == item.getY()) {
                Item matchingItem = item;
                return 
            }
        }
    }

    public boolean checkBuildingOnPath(int x, int y) {
        for (int i = 0; i < orderedPath.size(); i++) {
            Pair<Integer, Integer> cell = orderedPath.get(i);
            if (cell.getValue0() == x && cell.getValue1() == y) {
                return true;
            }
=======

    public Gold possiblySpawnGold() {
        Random randSpawn = new Random();
        int goldSpawn = randSpawn.nextInt(40);
        if (goldPosition != null && goldSpawn == 0) {
            int indexInPath = orderedPath.indexOf(goldPosition);
            PathPosition pathPosition = new PathPosition(indexInPath, orderedPath);
            Statistics stats = new Statistics(0, 0, 0, 0, 100);
            Gold gold = new Gold(pathPosition, stats);
            goldEntities.add(gold);
            return gold;
>>>>>>> origin/main
        }
        return null;
    }

}
