package unsw.loopmania;

import java.util.List;

import javafx.util.Pair;

public class EnemySelector {
    
    public Enemy getEnemy(int enemySelection, PathPosition position, List<Enemy> enemies, int numLoops, int vampireRespawnLoop, int zombieRespawnLoop) {
        Enemy enemy;
        boolean vampireSpawn = checkVampireSpawned(enemies);
        boolean zombieSpawn = checkZombieSpawned(enemies);
        if (numLoops % 5 == 0 && !vampireSpawn && numLoops == vampireRespawnLoop) {
            enemySelection = 2;
        } else if (!zombieSpawn && numLoops == zombieRespawnLoop) {
            enemySelection = 1;
        }
        switch (enemySelection) {
            case 0:
                enemy = new Slug(position);
                System.out.println("Spawned a slug");
                return enemy;
            case 1:
                enemy = new Zombie(position);
                System.out.println("Spawned a Zombie");
                return enemy;
            case 2:
                enemy = new Vampire(position);
                System.out.println("Spawned a vampire");
                return enemy;

        }
        return null;
    }

    public boolean checkVampireSpawned(List<Enemy> enemies) {
        for (Enemy e: enemies) {
            if (e.getType().equals("Vampire")){
                return true;
            }
        }
        return false;
    }

    public boolean checkZombieSpawned(List<Enemy> enemies) {
        for (Enemy e: enemies) {
            if (e.getType().equals("Zombie")){
                return true;
            }
        }
        return false;
    }
    /*
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
    */
}
