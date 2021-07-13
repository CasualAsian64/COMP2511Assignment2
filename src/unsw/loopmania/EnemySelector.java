package unsw.loopmania;

import java.util.List;

public class EnemySelector {
    
    public Enemy getEnemy(int enemySelection, PathPosition position, List<Enemy> enemies, LoopManiaWorld world) {
        Enemy enemy;
        boolean vampireSpawn = checkVampireSpawned(enemies);
        boolean zombieSpawn = checkZombieSpawned(enemies);
        if (world.getNumLoops() % 5 == 0 && !vampireSpawn && world.getNumLoops() == world.getVampireRespawnLoop()) {
            enemySelection = 2;
            world.setVampireRespawnLoop(world.getVampireRespawnLoop() + 5);
        } else if (!zombieSpawn && world.getNumLoops() == world.getZombieRespawnLoop()) {
            enemySelection = 1;
            world.setZombieRespawnLoop(world.getZombieRespawnLoop() + 1);
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
}
