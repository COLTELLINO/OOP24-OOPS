package it.unibo.oop.model;

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
    public boolean isColliding(final Hitbox h1, final Hitbox h2) {
        return false;
    }

        /**
     * Handle collision between two objects.
     * @param h1 the first object
     * @param h2 the second object
     */
    @Override
    public void handleCollision(final Hitbox h1, final Hitbox h2) {

    }

}
