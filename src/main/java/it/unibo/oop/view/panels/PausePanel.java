package it.unibo.oop.view.panels;

import java.awt.Graphics;

/**
 * Temporary panel for the pause screen.
 */
public final class PausePanel extends MyPanel {
    private static final long serialVersionUID = 1L;
    private static final int TEXT_OFFSET = 20;

    /**
     * Paints the pause message.
     * @param g the graphics context
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawString("PAUSA", getWidth() / 2 - TEXT_OFFSET, getHeight() / 2);
    }
}
