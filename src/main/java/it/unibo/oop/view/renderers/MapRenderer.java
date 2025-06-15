package it.unibo.oop.view.renderers;

import java.awt.Graphics2D;
/**
 * Interface for rendering the map in the game.
 */
public interface MapRenderer {
    /**
     * Draws the map on screen.
     * @param g2
     */
    void drawMap(Graphics2D g2);
    /**
     * Creates the map image by loading textures and drawing them.
     * @param g2
     */
    void createMapImage(Graphics2D g2);
}
