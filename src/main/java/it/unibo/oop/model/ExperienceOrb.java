package it.unibo.oop.model;

/**
*
*/
public class ExperienceOrb {

    private final int x, y;
    private final int xp;
    /**
     * @param x
     * @param y
     * @param amount
     */
    public ExperienceOrb(final int x, final int y, final int amount) {
        this.x = x;
        this.y = y;
        this.xp = amount;
    }
    /**
     * @return the xp value of the orb. 
     */
    public int getXP() {
        return this.xp;
    }
    /**
     * @return the X coordinate of the orb.
     */
    public int getX() {
        return this.x;
    }
    /**
     * @return the Y coordinate of the orb.
     */
    public int getY() {
        return this.y;
    }
}
