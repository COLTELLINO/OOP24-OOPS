package it.unibo.oop.model;

import java.awt.Rectangle;
import java.util.List;

/**
 * Interface for managing collisions between game objects.
 */
public interface CollisionManager {
    /**
     * Check if two objects are colliding.
     * @param h1 the first object
     * @param h2 the second object
     * @return true if the objects are colliding, false otherwise
     */
    boolean isColliding(Rectangle h1, Rectangle h2);

    /**
     * Handle collision between two objects.
     * @param h1 the first object
     * @param h2 the second object
     */
    void handleWeaponCollision(final List<Enemy> enemies, final Weapon weapon);

}
