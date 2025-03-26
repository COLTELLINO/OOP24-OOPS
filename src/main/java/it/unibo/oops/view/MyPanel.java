package it.unibo.oops.view;

import javax.swing.JPanel;
/**
 * 
 */
public abstract class MyPanel extends JPanel {
    private static final double serialVersionUID = 0.01;
    /**
     * Font size value.
     */
    protected static final int FONT_SIZE = 24;
    /**
     * Horizontal border value.
     */
    protected static final int HORIZONTAL_BORDER = 50;
    /**
     * Vertical border value.
     */
    protected static final int VERTICAL_BORDER = 20;
    /**
     * Rows in gridLayout.
     */
    protected static final int ROWS = 5;
    /**
     * Columns in gridLayout.
     */
    protected static final int COLUMNS = 1;
    /**
     * Size of the horizontal and vertical gap in gridLayout.
     */
    protected static final int GAP = 10;
    /**
    *  @return the SerialVersionUID
    */
    public static double getSerialVersionUID() {
        return serialVersionUID;
    }
}
