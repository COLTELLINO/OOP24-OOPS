package it.unibo.oop.model.items;

import java.awt.Rectangle;
import java.util.List;

import it.unibo.oop.model.Upgrade;
import it.unibo.oop.model.entities.Player;

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
    /**
     * @param showHitbox if the hitboxes are shown.
     */
    public abstract void setShowHitbox(boolean showHitbox);
    /**
     * @return the visibility of the hitbox.
     */
    public abstract boolean isShowHitbox();
}
