package it.unibo.oops.view;

import java.awt.Color;
/**
 * 
 */
public class GamePanel extends MyPanel { 
    @SuppressWarnings("unused") // TEMPORARY
    private static final double serialVersionUID = getSerialVersionUID();
    /**
     * @param screenWidth
     * @param screenHeight
     */
    protected GamePanel(final int screenWidth, final int screenHeight) {
        super(screenWidth, screenHeight);
        super.setBackground(Color.BLACK);
    }
    /**
    *  Draws current panel.
    */
    @Override
    protected void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
}
