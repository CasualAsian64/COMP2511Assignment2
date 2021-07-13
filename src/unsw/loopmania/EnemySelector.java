package unsw.loopmania;

public class EnemySelector {
    
    public Enemy getEnemy(int enemySelection, PathPosition position) {
        Enemy enemy;
        switch (enemySelection) {
            case 0:
                enemy = new Slug(position);
               //System.out.println("Spawned a slug");
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
}
