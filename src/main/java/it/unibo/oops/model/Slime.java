package it.unibo.oops.model;

/**
 * 
 */

public class Slime extends Enemy {
    private static final int BASE_MAXHEALTH = 100;
    private static final int BASE_HEALTH = 100;
    private static final int BASE_ATTACK = 5;
    private static final int BASE_SPEED = 2;
    private static final int BASE_SIZE = 32;
    /**
     * @param x
     * @param y
     * @param player
     * @return a Slime enemy with base stats.
     */
    public static Enemy createDefault(final int x, final int y, final Player player) {
        return new Slime(x, y, BASE_MAXHEALTH, BASE_HEALTH, BASE_ATTACK, BASE_SPEED, BASE_SIZE, player);
    }
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param attack
     * @param speed
     * @param size
     * @param player
     */
    public Slime(final int x, final int y, final int maxHealth, final int health, final int attack, final int speed,
            final int size, final Player player) {
        super(x, y, maxHealth, health, attack, speed, size, player);
    }
}
