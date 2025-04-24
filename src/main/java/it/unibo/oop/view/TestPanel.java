package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Dimension;
/**
 * 
 */
public class TestPanel extends MyPanel { 
    @SuppressWarnings("unused") // TEMPORARY
    private static final double serialVersionUID = getSerialVersionUID();
    /**
     * @param screenWidth
     * @param screenHeight
     */
    public TestPanel(final int screenWidth, final int screenHeight) {
        super.setPreferredSize(new Dimension(screenWidth, screenHeight));
        super.setBackground(Color.GRAY);
    }
}
