package it.unibo.oop.view.renderers;


import java.awt.Color;
import java.awt.Graphics2D;


/**
 * Draws the player's health bar on screen.
 */
public final class HealthRendererImpl implements HealthRenderer {


    private static final int BAR_WIDTH = 200;
    private static final int BAR_HEIGHT = 20;
    private static final int OFFSET_X = 20;
    private static final int OFFSET_Y = 20;
    private static final int TEXT_OFFSET_X = 5;
    private static final int TEXT_OFFSET_Y = 5;


    @Override
    public void drawHealthBar(final Graphics2D g2d, final int currentHealth, final int maxHealth) {
        final double ratio = (double) currentHealth / maxHealth;
        final int filledWidth = (int) (BAR_WIDTH * ratio);


        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(OFFSET_X, OFFSET_Y, BAR_WIDTH, BAR_HEIGHT);


        g2d.setColor(Color.RED);
        g2d.fillRect(OFFSET_X, OFFSET_Y, filledWidth, BAR_HEIGHT);


        g2d.setColor(Color.WHITE);
        g2d.drawRect(OFFSET_X, OFFSET_Y, BAR_WIDTH, BAR_HEIGHT);


        g2d.drawString("HP: " + currentHealth + " / " + maxHealth, OFFSET_X + TEXT_OFFSET_X, OFFSET_Y - TEXT_OFFSET_Y);
    }
}
