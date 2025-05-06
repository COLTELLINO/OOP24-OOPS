package it.unibo.oop.view;

import java.awt.Graphics;
import java.util.List;

import it.unibo.oop.model.Sword;
import it.unibo.oop.model.Weapon;

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
    void drawWeaponList(Graphics g, List<Weapon> weapons);
}
