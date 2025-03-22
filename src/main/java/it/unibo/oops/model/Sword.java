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

    private double timer;
    private double cooldown;
    private boolean active;
    private final Player player;
    /**
     * @param player
     */
    public Sword(final Player player) {
        this.active = false;
        this.timer = DURATION;
        this.cooldown = COOLDOWN;
        this.player = player;
    }
    /**
     * Updates the sword.
     */
    @Override
    public void update() {
        if (active) {
            timer--;
            if (timer <= 0) {
                active = false;
                this.cooldown = COOLDOWN;
                this.timer = DURATION;
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
            switch (player.getDirection()) {
                case Direction.UP:
                    drawX = player.getX() + player.getSize() / 2 - WIDTH / 2;
                    drawY = player.getY() - HEIGHT;
                    break;
                case Direction.RIGHT:
                    drawX = player.getX() + player.getSize();
                    drawY = player.getY() + player.getSize() / 2 - WIDTH / 2;
                    swordWidth = HEIGHT; 
                    swordHeight = WIDTH;
                    break;
                case Direction.DOWN:
                    drawX = player.getX() + player.getSize() / 2 - WIDTH / 2;
                    drawY = player.getY() + player.getSize();
                    break;
                case Direction.LEFT:
                    drawX = player.getX() - HEIGHT;
                    drawY = player.getY() + player.getSize() / 2 - WIDTH / 2;
                    swordWidth = HEIGHT;
                    swordHeight = WIDTH;
                    break;
                default:
                    break;
            }
            g.fillRect(drawX, drawY, swordWidth, swordHeight);
        }
    }
}
