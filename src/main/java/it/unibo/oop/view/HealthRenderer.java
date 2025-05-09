package it.unibo.oop.view;


import java.awt.Graphics2D;


/**
 * Interface for rendering the health bar of the player.
 */
public interface HealthRenderer {


    /**
     * Draws the health bar of the player.
     *
     * @param g2d
     * @param currentHealth
     * @param maxHealth
     */
    void drawHealthBar(Graphics2D g2d, int currentHealth, int maxHealth);
}
