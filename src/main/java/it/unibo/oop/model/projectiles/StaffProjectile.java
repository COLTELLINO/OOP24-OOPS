package it.unibo.oop.model.projectiles;

import it.unibo.oop.model.items.MagicStaff.ProjectileObserver;
import it.unibo.oop.model.managers.ProjectileManagerImpl.ProjectileManagerObserver;
import it.unibo.oop.utils.Direction;

/**
 * Represents a projectile shot by a magic staff.
 */
public class StaffProjectile extends Projectile {

    private ProjectileObserver observer;
    private ProjectileManagerObserver managerObserver = projectile -> {
        // Default no-op implementation
    };
    private boolean exploded;

    /**
     * Constructs a Projectile.
     * 
     * @param x the initial x-coordinate
     * @param y the initial y-coordinate
     * @param direction the direction of the projectile
     * @param damage the damage of the projectile
     * @param speed the speed of the projectile
     * @param size the size of the projectile
     * @param entitySize the size of the entity that fired the projectile
     */
    public StaffProjectile(final int x, final int y, final Direction direction, 
                           final int damage, final int speed, final int size, final int entitySize) {
        super(x, y, direction, damage, speed, size, entitySize);
    }

    /**
     * Sets the observer for this projectile.
     * 
     * @param observer the observer to set
     */
    public void setObserver(final ProjectileObserver observer) {
        this.observer = observer;
    }

    /**
     * Sets the observer for this projectile.
     * 
     * @param observer the observer to set
     */
    @Override
    public void setManagerObserver(final ProjectileManagerObserver observer) {
        this.managerObserver = observer;
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
        if (observer != null && managerObserver != null && !exploded) {
            observer.onProjectileExploded(this);
            managerObserver.onProjectileExploded(this);
            exploded = true;
        }
    }
}
