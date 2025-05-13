package it.unibo.oop.model.projectiles;
import java.awt.Rectangle;

import it.unibo.oop.utils.Direction;

public class StaffProjectile extends Projectile {

    private static final int EXPLOSION_SIZE = 200;
    //private static final int EXPLOSION_LIFETIME = 30;

    /**
     * Constructs a Projectile.
     * 
     * @param x the initial x-coordinate
     * @param y the initial y-coordinate
     * @param direction the direction of the projectile
     * @param speed the speed of the projectile
     * @param size the size of the projectile
     */
    public StaffProjectile(int x, int y, Direction direction, int damage, int speed, int size) {
        super(x, y, direction, damage, speed, size);
    }
    
    /**
     * creates the explosion.
     */
    public void explode() {
        final int explosionX;
        final int explosionY;

        switch (this.getDirection()) {
            case UP -> {
                explosionX = this.getX() - (EXPLOSION_SIZE - this.getSize()) / 2;
                explosionY = this.getY() - EXPLOSION_SIZE + this.getSize();
            }
            case DOWN -> {
                explosionX = this.getX() - (EXPLOSION_SIZE - this.getSize()) / 2;
                explosionY = this.getY();
            }
            case LEFT -> {
                explosionX = this.getX() - EXPLOSION_SIZE / 2 - this.getSize();
                explosionY = this.getY() - (EXPLOSION_SIZE - this.getSize()) / 2;
            }
            case RIGHT -> {
                explosionX = this.getX();
                explosionY = this.getY() - (EXPLOSION_SIZE - this.getSize()) / 2;
            }
            default -> {
                explosionX = this.getX() - (EXPLOSION_SIZE - this.getSize()) / 2;
                explosionY = this.getY() - (EXPLOSION_SIZE - this.getSize()) / 2;
            }
        }

        final Rectangle explosion = new Rectangle(
            explosionX,
            explosionY,
            EXPLOSION_SIZE,
            EXPLOSION_SIZE
        );

        //explosionHitboxes.put(explosion, EXPLOSION_LIFETIME);
    }
}
