package it.unibo.oops.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
/**
 * 
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "To move enemies towards the player, its position is needed, " 
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public abstract class Enemy extends Entity {
    private boolean isSpawned;
    private final Player player;
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param attack
     * @param speed
     * @param size
     * @param player
     */
    public Enemy(final int x, final int y, final int maxHealth, final int health, final int attack, final int speed,
            final int size, final Player player) {
        super(x, y, maxHealth, health, attack, speed, size);
        this.player = player;
    }
    /**
     * @return if the enemy has been positioned.
     */
    public boolean isSpawned() {
        return isSpawned;
    }
    /**
     * @param isSpawned
     */
    public void setSpawned(final boolean isSpawned) {
        this.isSpawned = isSpawned;
    }
    /**
     * Draws current enemy.
     */
    @Override
    public void draw(final Graphics2D g2) {
        try {
            final BufferedImage image = 
            ImageIO.read(this.getClass().getResource("/Monster/" + this.getClass().getSimpleName() + ".png"));
            g2.drawImage(image, getX() - image.getWidth() / 2, getY() - image.getHeight() / 2, null);
            g2.setColor(Color.RED);
            g2.drawRect(getX() - image.getWidth() / 2, getY()  - image.getHeight() / 2,
            getSize(), getSize());
        } catch (IOException | IllegalArgumentException e) {
            Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, e.getClass().getSimpleName() + " occurred: ", e);
        }
    }
    /**
     * Updates current enemy.
     */
    @Override
    public void update() {
        final int xDistance = Integer.compare(player.getX() + player.getSize() / 2, this.getX());
        final int yDistance = Integer.compare(player.getY() + player.getSize() / 2, this.getY());
        this.setX(getX() + xDistance * getSpeed());
        this.setY(getY() + yDistance * getSpeed());
        if (this.getX() == player.getX() + player.getSize() / 2 && this.getY() == player.getY() + player.getSize() / 2) {
            //this.setAlive(false);
            player.setHealth(player.getHealth() - this.getAttack());
        }
    }
}
