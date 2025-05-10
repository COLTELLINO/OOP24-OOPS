package it.unibo.oop.view.renderers;

import java.awt.Graphics2D;

import it.unibo.oop.model.entities.Player;

/**
 * Interface to render the player.
 */
public interface PlayerRenderer {
    /**
     * Draws the player on screen.
     * @param player the player instance
     * @param g2 the graphics context
     */
    void drawPlayer(Player player, Graphics2D g2);
}
