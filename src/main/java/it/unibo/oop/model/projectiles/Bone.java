package it.unibo.oop.model.projectiles;

import it.unibo.oop.model.managers.ProjectileManagerImpl.ProjectileManagerObserver;
import it.unibo.oop.utils.Direction;

/**
 * Represents an arrow projectile in the game.
 */
public class Bone extends Projectile {

    /**
     * Constructs an Bone object.
     * 
     * @param x          the x-coordinate of the bone
     * @param y          the y-coordinate of the bone
     * @param direction  the direction of the bone
     * @param damage     the damage dealt by the bone
     * @param speed      the speed of the bone
     * @param size       the size of the bone
     * @param entitySize the size of the entity that fired the bone
     */
    public Bone(final int x, final int y, final Direction direction,
            final int damage, final int speed, final int size, final int entitySize) {
        super(x, y, direction, damage, speed, size, entitySize);
    }

    /**
    * @return the name of the projectile class
    */
    @Override
    public String getProjectileName() {
        return this.getClass().getSimpleName();
    }

    /**
     * handles the collision of the projectile with the enemy.
     */
    @Override
    public void handleCollision() {
        //unused
    }

    @Override
    public void setManagerObserver(final ProjectileManagerObserver observer) {
        //unused
    }
}
