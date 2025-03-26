package it.unibo.oops.model;
/**
 * 
 */
public class Slime extends Enemy {
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param speed
     * @param size
     * @param player
     */
    public Slime(final int x, final int y, final int maxHealth, final int health, final int speed,
            final int size, final Player player) {
        super(x, y, maxHealth, health, speed, size, player);
    }
}
