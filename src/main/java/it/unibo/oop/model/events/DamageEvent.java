package it.unibo.oop.model.events;
/**
 * Class representing a damage event in the game.
 * This event is used to display damage numbers when the player hits an enemy.
 */
public class DamageEvent {
    private static final int LIFETIME_VALUE = 60;
    private final int x;
    private int y;
    private final int value;
    private int lifetime = LIFETIME_VALUE;
    /**
     * Create a damage event at position (x,y) with damage value and lifetime in frames.
     * @param x the initial x coordinate
     * @param y the initial y coordinate
     * @param value the damage value to show
     */
    public DamageEvent(final int x, final int y, final int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    /**
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }
    /**
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }
    /**
     * @return the damage value
     */
    public int getValue() {
        return value;
    }
    /**
     * @return the lifetime of the event in frames
     */
    public int getLifetime() {
        return lifetime;
    }
    /**
     * Update the event.
     * Moves the damage number up by 1 pixel per frame and decreases lifetime.
     */
    public void update() {
        y -= 1;
        lifetime--;
    }
    /**
     * Check if the event is expired.
     * Intended to be used to remove the event from the list of active events.
     * @return true if lifetime <= 0
     */
    public boolean isOver() {
        return lifetime <= 0;
    }
}
