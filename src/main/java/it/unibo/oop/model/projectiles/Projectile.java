package it.unibo.oop.model.projectiles;

import java.awt.Rectangle;

import it.unibo.oop.model.managers.ProjectileManagerImpl.ProjectileManagerObserver;
import it.unibo.oop.utils.Direction;

/**
 * Represents a projectile shot by a weapon.
 * NOTE (hopefully temporary): every projectile image should be 32 x 32 and their hitbox consists in the tophalf of the image,
 * also it might not look like it's shot from the correct spot if the entity is not close to the 32 x 32 size. 
 */
public abstract class Projectile {
    private static final int BOUNDS = 6000;
    private final int damage;
    private final int speed;
    private int x;
    private int y;
    private final int size;
    private final Direction direction;
    private boolean showHitbox;

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
    public Projectile(final int x, final int y, final Direction direction,
            final int damage, final int speed, final int size, final int entitySize) {
        int adjustedX = x;
        int adjustedY = y;
        switch (direction) {
            case UP -> {
                adjustedY -= entitySize;
                adjustedX += (entitySize - size) / 2;
            }
            case DOWN -> {
                adjustedY += entitySize;
                adjustedX += (entitySize - size) / 2;
            }
            case LEFT -> {
                adjustedY += (entitySize - size) / 2;
                adjustedX -= entitySize;
            }
            case RIGHT -> {
                adjustedY += (entitySize - size) / 2;
                adjustedX += entitySize;
            }
            default -> throw new IllegalArgumentException("Invalid direction");
        }

        this.x = adjustedX;
        this.y = adjustedY;
        this.damage = damage;
        this.speed = speed;
        this.direction = direction;
        this.size = size;
    }

    /**
     * Updates the position of the projectile.
     */
    public void update() {
        switch (direction) {
            case UP -> y -= speed;
            case DOWN -> y += speed;
            case LEFT -> x -= speed;
            case RIGHT -> x += speed;
            default -> { }
        }
    }

    /**
     * Checks if the projectile is out of bounds.
     * 
     * @return true if the projectile is out of bounds, false otherwise
     */
    public boolean isOutOfBounds() {
        return x < -BOUNDS || x > BOUNDS || y < -BOUNDS || y > BOUNDS;
    }

    /**
     * @return the x-coordinate of the projectile
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y-coordinate of the projectile
     */
    public int getY() {
        return y;
    }

    /**
     * @return the size of the projectile
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the direction of the projectile.
     * 
     * @return the direction of the projectile
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @return the damage of the projectile.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Gets the hitbox of the projectile.
     * 
     * @return the hitbox as a Rectangle
     */
    public Rectangle getProjectileHitBox() {
        return new Rectangle(this.getX(), this.getY(), this.getSize(), this.getSize());
    }
    /**
     * @return if the hitboxes are showed.
     */
    public boolean isHitboxShowed() {
        return showHitbox;
    }
    /**
     * @param showHitbox
     */
    public void setShowHitbox(final boolean showHitbox) {
        this.showHitbox = showHitbox;
    }
    /**
     * @return the name of the projectile class
     */
    public abstract String getProjectileName();

    /**
     * sets the x coordinate of the projectile.
     * @param x the x coordinate
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * sets the y coordinate of the projectile.
     * @param y the y coordinate
     */
    public void setY(final int y) {
        this.y = y;
    }

    /**
     * Handles the collision of the projectile.
     */
    public abstract void handleCollision();

    /**
     * Sets the observer for this projectile.
     * 
     * @param observer the observer to set
     */
    public abstract void setManagerObserver(ProjectileManagerObserver observer);
}
