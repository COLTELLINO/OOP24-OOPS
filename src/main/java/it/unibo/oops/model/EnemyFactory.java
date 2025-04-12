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
     * @param attack
     * @param speed
     * @param size
     * @param player
     * @return a new instance of a Slime enemy.
     */
    Enemy createSlime(int x, int y, int maxHealth, int health, int attack, int speed, int size, Player player);
    /**
     * @param x
     * @param y
     * @param player
     * @return a new instance of a Slime enemy with base stats.
     */
    Enemy createBaseSlime(int x, int y, Player player);
}
