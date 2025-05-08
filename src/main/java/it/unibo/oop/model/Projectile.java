package it.unibo.oop.model;

import java.awt.Rectangle;

/**
 * Represents a projectile shot by a weapon.
 */
public class Projectile {
    private static final int BOUNDS = 6000;
    private final int speed;
    private int x;
    private int y;
    private final int size;
    private final Direction direction;

    /**
     * Constructs a Projectile.
     * 
     * @param x the initial x-coordinate
     * @param y the initial y-coordinate
     * @param direction the direction of the projectile
     * @param speed the speed of the projectile
     * @param size the size of the projectile
     */
    public Projectile(final int x, final int y, final Direction direction, final int speed, final int size) {
        this.x = x;
        this.y = y;
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
        return x < 0 || x > BOUNDS || y < 0 || y > BOUNDS;
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
     * Gets the direction of the projectile.
     * 
     * @return the direction of the projectile
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Gets the hitbox of the projectile.
     * 
     * @return the hitbox as a Rectangle
     */
    public Rectangle getHitBox() {
        switch (direction) {
            case UP -> {
                return new Rectangle(x + size / 3, y - size - size / 2, size, size);
            }
            case DOWN -> {
                return new Rectangle(x + size / 3, y + size * 2, size, size);
            }
            case LEFT -> {
                return new Rectangle(x - size - size / 2, y + size / 3, size, size);
            }
            case RIGHT -> {
                return new Rectangle(x + size * 2, y + size / 3, size, size);
            }
            default -> {
                return null;
            }
        }
    }
}
