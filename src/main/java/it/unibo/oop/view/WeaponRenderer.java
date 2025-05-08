package it.unibo.oop.view;

import java.awt.Graphics;
import java.util.Map;

import it.unibo.oop.model.Sword;
import it.unibo.oop.model.Weapon;
import it.unibo.oop.model.Bow;
import it.unibo.oop.model.MagicStaff;

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
    void drawWeaponList(Graphics g, Map<Weapon, Integer> weapons);

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
    /**
     * Draws weapon hitboxes.
     */
    void drawWeaponHitboxes(Graphics g, Map<Weapon, Integer> weapons);
}
