package it.unibo.oops.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * 
 */
public class TestPanel extends MyPanel { 
    @SuppressWarnings("unused") // TEMPORARY
    private static final double serialVersionUID = getSerialVersionUID();
    private static final int PROPORTION = 5;
    /**
     * @param screenWidth
     * @param screenHeight
     */
    protected TestPanel(final int screenWidth, final int screenHeight) {
        super(screenWidth, screenHeight);
        super.setBackground(Color.BLACK);
    }
    /**
    *  Draws updated panel.
    *
    * @param g
    */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN);
        g2.fillRect(0, 0, this.getScreenWidth() / PROPORTION, this.getScreenHeight() / PROPORTION);
    }
    /**
    *  Draws current panel.
    */
    @Override
    protected void draw() {
        super.repaint();
    }
}
