package it.unibo.oops.model;
import java.awt.Graphics;
/**
* 
*/
public abstract class Weapon {
    /**
     * Updates the Item.
     */
    public abstract void update();
    /**
     * Draws the Item.
     * @param g
     */
    public abstract void draw(Graphics g); 
}
