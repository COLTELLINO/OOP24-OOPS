package it.unibo.oops.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * 
 */
public abstract class MyPanel extends JPanel {
    private static final double serialVersionUID = 0.01;

    private int sw; //sh and sh are not supposed to be final
    private int sh;
    /**
     * @param screenWidth
     * @param screenHeight
     */
    protected MyPanel(final int screenWidth, final int screenHeight) {
        this.sw = screenWidth;
        this.sh = screenHeight;
        super.setPreferredSize(new Dimension(this.sw, this.sh));
        super.setBackground(Color.GREEN);
    }
    /**
    *  Draws current panel.
    */
    protected abstract void draw();
    /**
    *  Sets screen dimensions.
    *
    * @param d
    */
    protected void setScreenDimensions(final Dimension d) {
        this.sw = (int) d.getWidth();
        this.sh = (int) d.getHeight();
    }
    /**
    *  @return the SerialVersionUID
    */
    protected static double getSerialVersionUID() {
        return serialVersionUID;
    }
    /**
    *  @return the SerialVersionUID
    */
    protected int getScreenWidth() {
        return this.sw;
    }
    /**
    *  @return the SerialVersionUID
    */
    protected int getScreenHeight() {
        return this.sh;
    }
}
