package it.unibo.oop.model;

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
    boolean isColliding(Hitbox h1, Hitbox h2);

    /**
     * Handle collision between two objects.
     * @param h1 the first object
     * @param h2 the second object
     */
    void handleCollision(Hitbox h1, Hitbox h2);

}
