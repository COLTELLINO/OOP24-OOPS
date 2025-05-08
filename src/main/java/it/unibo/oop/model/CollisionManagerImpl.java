package it.unibo.oop.model;

import java.util.List;
import java.awt.Rectangle;

/**
 * Class managing collisions between game objects.
 */
public class CollisionManagerImpl implements CollisionManager {
    /**
     * Check if two objects are colliding.
     * @param h1 the first object
     * @param h2 the second object
     * @return true if the objects are colliding, false otherwise
     */
    @Override
    public boolean isColliding(final Rectangle h1, final Rectangle h2) {
        return h1.intersects(h2);
    }

    /**
     * Handle collision weapon/enemies.
     * @param enemies the enemy list
     * @param weapon the weapon
     */
    @Override
    public void handleWeaponCollision(final List<Enemy> enemies, final Weapon weapon) {
        for (final Enemy enemy : enemies) {
            enemy.setHealth(enemy.getHealth() - weapon.getDamage());
        }
    }
}
