package it.unibo.oops.model;
/**
 * Implementation of EnemyFactory.
 */
public class EnemyFactoryImpl implements EnemyFactory {
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param speed
     * @param attack
     * @param size
     * @param player
     * @return a new instance of a Slime enemy.
     */
    @Override
    public Enemy createSlime(final int x, final int y, final int maxHealth, final int health, final int attack, 
            final int speed, final int size, final Player player) {
        return new Slime(x, y, maxHealth, health, attack, speed, size, player);
    }
}
