package test;

import unsw.loopmania.Enemy;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Slug;
import unsw.loopmania.Vampire;
import unsw.loopmania.Zombie;

public class EnemySelector {

    public Enemy getEnemy(String type, PathPosition position) {
        if (type == null) {
            return null;
        }
        if (type.equals("Slug")) {
            Slug enemy = new Slug(position);
            return enemy;
        }
        if (type.equals("Zombie")) {
            Zombie enemy = new Zombie(position);
            return enemy;
        }
        if (type.equals("Slug")) {
            Vampire enemy = new Vampire(position);
            return enemy;
        }
        return null;
    }
}
