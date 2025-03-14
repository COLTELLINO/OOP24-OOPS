package it.unibo.oops.model;
import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 */
public class Sword extends Weapon {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 30;
    private static final double DURATION = 0.5;
    private static final double COOLDOWN = 1.5;

    private double timer;
    private double cooldown;
    private boolean active;
    private final Player player;
    private final double fps;
    /**
     * @param fps
     * @param player
     */
    public Sword(final double fps, final Player player) {
        this.active = false;
        this.timer = fps * DURATION;
        this.cooldown = fps * COOLDOWN;
        this.fps = fps;
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
                this.cooldown = fps * COOLDOWN;
                this.timer = fps * DURATION;
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
            switch (player.getDirection()) {
                case "UP":
                    drawX = player.getX() + WIDTH * 2;
                    drawY = player.getY() - HEIGHT;
                    break;
                case "RIGHT": 
                    drawX = WIDTH;
                    drawY = HEIGHT / 2;
                    break;
                case "DOWN":
                    drawX = WIDTH / 2;
                    drawY = HEIGHT;
                    break;
                case "LEFT":
                    drawX = WIDTH;
                    drawY = HEIGHT / 2;
                    break;
                default:
                    break;
            }
            g.fillRect(drawX, drawY, WIDTH, HEIGHT);
        }
    }
}
