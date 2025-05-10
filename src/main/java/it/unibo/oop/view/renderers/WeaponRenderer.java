package it.unibo.oop.view.renderers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Map;

import it.unibo.oop.model.items.Bow;
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
    void drawWeaponList(Graphics2D g, Map<Weapon, Integer> weapons);

    /**
     * Draws a bow and its projectiles.
     * 
     * @param g the graphics context
     * @param bow the bow to draw
     */
    void drawBow(Graphics g, Bow bow);

    /**
     * Draws a magic staff and its projectiles.
     * 
     * @param g the graphics context
     * @param staff the magic staff to draw
     */
    void drawMagicStaff(Graphics g, MagicStaff staff);
}
