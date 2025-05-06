package it.unibo.oops.model;
import java.awt.Color;
import java.awt.Graphics;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * 
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "To position the weapon, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class Sword extends Weapon {
    private static final int WIDTH = 15;
    private static final int HEIGHT = 45;
    private static final double DURATION = 30;
    private static final double COOLDOWN = 60;
    private double duration;
    private double cooldown;
    private boolean active;
    private final Player player;
    private boolean lastSwingRight = true;
    /**
     * @param player
     */
    public Sword(final Player player) {
        this.active = false;
        this.player = player;
    }
    /**
     * Updates the sword.
     */
    @Override
    public void update() {
        if (active) {
            duration--;
            if (duration <= 0) {
                active = false;
                this.cooldown = COOLDOWN;
                this.duration = DURATION;
            }
        } else {
            if (cooldown <= 0) {
                this.active = true;
            } else {
                cooldown--;
            }
        }
    }    
    /**
     * Draws the sword.
     * @param g
     */
    @Override
    public void draw(final Graphics g) {
        if (active) {
            g.setColor(Color.BLUE);
            int drawX = player.getX(), drawY = player.getY();
            int swordWidth = WIDTH, swordHeight = HEIGHT; 

            Direction currentDirection = player.getDirection();

            if (currentDirection == Direction.NONE || currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
                if (lastSwingRight) {
                    currentDirection = Direction.RIGHT;
                } else {
                    currentDirection = Direction.LEFT;
                }
            } else if (currentDirection == Direction.UPRIGHT || currentDirection == Direction.DOWNRIGHT) {
                currentDirection = Direction.RIGHT;
            } else if (currentDirection == Direction.UPLEFT || currentDirection == Direction.DOWNLEFT) {
                currentDirection = Direction.LEFT;
            }

            switch (currentDirection) {
                case Direction.RIGHT:
                    drawX = player.getX() + player.getSize();
                    drawY = player.getY() + player.getSize() / 2 - WIDTH / 2;
                    swordWidth = HEIGHT; 
                    swordHeight = WIDTH;
                    lastSwingRight = true;
                    break;
                case Direction.LEFT:
                    drawX = player.getX() - HEIGHT;
                    drawY = player.getY() + player.getSize() / 2 - WIDTH / 2;
                    swordWidth = HEIGHT;
                    swordHeight = WIDTH;
                    lastSwingRight = false;
                    break;
                default:
                    break;
            }
            g.fillRect(drawX, drawY, swordWidth, swordHeight);
        }
    }
}
