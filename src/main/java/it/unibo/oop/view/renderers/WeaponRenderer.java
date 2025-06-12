package it.unibo.oop.view.renderers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import it.unibo.oop.model.items.CursorSaw;
import it.unibo.oop.model.items.HeatWave;
import it.unibo.oop.model.items.MagicStaff;
import it.unibo.oop.model.items.Sword;
import it.unibo.oop.model.items.Weapon;

/**
 * Interface for rendering weapons.
 */
public interface WeaponRenderer {
    /**
     * Draws a sword.
     * 
     * @param g the graphics context
     * @param sword the sword to draw
     */
    void drawSword(Graphics g, Sword sword);

    /**
     * Draws a list of weapons.
     * 
     * @param g the graphics context
     * @param weapons the list of weapons to draw
     */
    void drawWeaponList(Graphics2D g, List<Weapon> weapons);

    /**
     * Draws a magic staff and its projectiles.
     * 
     * @param g the graphics context
     * @param staff the magic staff to draw
     */
    void drawMagicStaff(Graphics g, MagicStaff staff);

    void drawCursorSaw(Graphics g, CursorSaw cursorSaw);

        /**
     * Draws a HeatWave.
     * 
     * @param g the graphics context
     * @param staff the HeatWave to draw
     */
    void drawHeatWave(Graphics g, HeatWave heatwave);
}
