package it.unibo.oop.model;

/**
 * Represents a rectangular hitbox used for collision detection.
 */
public class Hitbox {
    private final int x, y, width, height;

    /**
     * Constructs a new Hitbox with the specified position and dimensions.
     *
     * @param x      the x-coordinate of the top-left corner of the hitbox
     * @param y      the y-coordinate of the top-left corner of the hitbox
     * @param width  the width of the hitbox
     * @param height the height of the hitbox
     */
    public Hitbox(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the x-coordinate of the top-left corner of the hitbox.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the top-left corner of the hitbox.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the width of the hitbox.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the hitbox.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }
}
