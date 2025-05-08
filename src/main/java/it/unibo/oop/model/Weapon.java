package it.unibo.oop.model;

import java.awt.Rectangle;
import java.util.List;

/**
* Abstract class representing a weapon in the game.
*/
public abstract class Weapon extends Upgrade {
    /**
     * Constructor for the Weapon class.
     * @param player the player who owns the weapon.
     */
    public Weapon(final Player player) {
        super(player);
    }
    /**
     * @return the weapon's damage.
     */
    public abstract int getDamage();
    /**
     * @return the weapon's hitbox.
     */
    public abstract List<Rectangle> getHitBox();
}
