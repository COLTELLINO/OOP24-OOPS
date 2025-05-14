package it.unibo.oop.model.projectiles;

import it.unibo.oop.model.managers.ProjectileManagerImpl.ProjectileManagerObserver;
import it.unibo.oop.utils.Direction;

/**
 * Represents an arrow projectile in the game.
 */
public class Arrow extends Projectile {

    /**
     * Constructs an Arrow object.
     * 
     * @param x         the x-coordinate of the arrow
     * @param y         the y-coordinate of the arrow
     * @param direction  the direction of the arrow
     * @param damage    the damage dealt by the arrow
     * @param speed     the speed of the arrow
     * @param size      the size of the arrow
     */
    public Arrow(final int x, final int y, final Direction direction, 
                 final int damage, final int speed, final int size) {
        super(x, y, direction, damage, speed, size);
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
