package it.unibo.oops.model;
/**
 * 
 */
public interface EnemyFactory {
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param speed
     * @param size
     * @param player
     * @return a new instance of a Slime enemy.
     */
    Enemy createSlime(int x, int y, int maxHealth, int health, int speed, int size, Player player);
}
