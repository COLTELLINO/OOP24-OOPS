package it.unibo.oops.model;

import java.awt.Graphics2D;
import java.awt.Color;

/**
 * 
 */
public class Player extends Entity {
    private Direction direction;
    private int xp;
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param speed
     * @param size
     */
    public Player(final int x, final int y, final int maxHealth, final int health, final int speed, final int size) {
        super(x, y, maxHealth, health, speed, size);
        this.direction = Direction.NONE;
    }
    /**
     * @return the facing direction of the player.
     */
    public Direction getDirection() {
        return this.direction;
    }
    /**
     * @return the current experience value of the player.
     */
    public int getXP() {
        return this.xp;
    }
    /**
     * Sets the facing direction of the player.
     * @param direction
     */
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }
    /**
     * Adds to the player experience count.
     * @param xp
     */
    public void addXp(final int xp) {
        this.xp += xp;
    }
    /**
     * Updates the direction of the player.
     */
    @Override
    public void update() {
        switch (direction) {
            case UP:
                setY(getY() - getSpeed());
                break;
            case DOWN:
                setY(getY() + getSpeed());
                break;
            case LEFT:
                setX(getX() - getSpeed());
                break;
            case RIGHT:
                setX(getX() + getSpeed());
                break;
            case NONE:
                return;
            default:
                throw new IllegalArgumentException();
        }
    }
    /**
     * Draws the player.
     * @param g
     */
    @Override
    public void draw(final Graphics2D g) {
        g.setColor(Color.GREEN);    //colore rettangolo
        g.fillRect(getX(), getY(), getSize(), getSize());
    }
}
