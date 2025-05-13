package it.unibo.oop.model.managers;

import java.awt.Rectangle;
import java.util.Set;

import it.unibo.oop.model.entities.Enemy;
import it.unibo.oop.model.items.Weapon;

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
     * Handle collision weapon/enemies.
     * @param enemies the enemy list
     * @param weapon the weapon
     */
    void handleWeaponCollision(Set<Enemy> enemies, Weapon weapon);

}
