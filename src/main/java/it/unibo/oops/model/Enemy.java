package it.unibo.oops.model;

import java.awt.Color;
import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;
// import java.io.IOException;
// import java.util.logging.Level;
// import java.util.logging.Logger;

// import javax.imageio.ImageIO;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
/**
 * 
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "To move enemies towards the player, its position is needed, " 
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public abstract class Enemy extends Entity {
    private boolean isPositioned;
    private final Player player;
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param speed
     * @param size
     * @param player
     */
    public Enemy(final int x, final int y, final int maxHealth, final int health, final int speed,
            final int size, final Player player) {
        super(x, y, maxHealth, health, speed, size);
        this.player = player;
    }
    /**
     * @return if the enemy has been positioned.
     */
    public boolean isPositioned() {
        return isPositioned;
    }
    /**
     * @param isPositioned
     */
    public void setPosition(final boolean isPositioned) {
        this.isPositioned = isPositioned;
    }
    /**
     * Draws current enemy.
     */
    @Override
    public void draw(final Graphics2D g2) {
        // try {
        //     final BufferedImage image = 
        //     ImageIO.read(this.getClass().getResource("./resources/Monster/" + this.getClass().getSimpleName() + ".png"));
        //     g2.drawImage(image, getX(), getY(), null);
        // } catch (IOException e) {
        //     Logger.getLogger(this.getClass().getName()).
        //     log(Level.SEVERE, "An IOException occurred: ", e);
        // } catch (IllegalArgumentException e) {
        //     Logger.getLogger(this.getClass().getName()).
        //     log(Level.SEVERE, "An IllegalArgumentException occurred: ", e);
        // }
        g2.setColor(Color.RED);
        g2.fillOval(getX(), getY(), getSize() / 2, getSize() / 2);
    }
    /**
     * Updates current enemy.
     */
    @Override
    public void update() {
        final int xDistance = Integer.compare(player.getX(), this.getX());
        final int yDistance = Integer.compare(player.getY(), this.getY());
        this.setX(getX() + xDistance * getSpeed());
        this.setY(getY() + yDistance * getSpeed());
    }
}
