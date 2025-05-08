package it.unibo.oop.model;

import java.awt.Rectangle;
import java.util.List;

/**
* Abstract class representing a weapon in the game.
*/
public abstract class Weapon {
    /**
     * Updates the Item.
     */
    public abstract void update();
    /**
     * @return the weapon's player.
     */
    public abstract Player getPlayer();
    /**
     * @return the weapon's level.
     */
    public abstract int getLevel();
    /**
     * @return the weapon's damage.
     */
    public abstract int getDamage();
    /**
     * @return the weapon's hitbox.
     */
    public abstract List<Rectangle> getHitBox();
}   
