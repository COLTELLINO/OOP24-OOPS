package it.unibo.oop.model.items;

import java.awt.Rectangle;
import java.util.List;

import it.unibo.oop.model.entities.Player;

/**
 * Abstract base class for all weapons, providing common logic for level, hitbox visibility,
 * and critical hit calculation.
 */
public abstract class Weapon extends Upgrade {

    /**
     * The current level of the weapon.
     */
    private int level = 1;

    /**
     * Whether the weapon's hitbox should be shown.
     */
    private boolean showHitbox;

    /**
     * Constructor for the Weapon class.
     * @param player the player who owns the weapon.
     */
    public Weapon(final Player player) {
        super(player);
    }

    /**
     * Gets the current level of the weapon.
     * @return the weapon's level
     */
    @Override
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the weapon.
     * @param level the new level
     */
    @Override
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     * Sets whether the weapon's hitbox should be shown.
     * @param showHitbox true to show the hitbox, false otherwise
     */
    public void setShowHitbox(final boolean showHitbox) {
        this.showHitbox = showHitbox;
    }

    /**
     * Returns whether the weapon's hitbox is currently shown.
     * @return true if the hitbox is shown, false otherwise
     */
    public boolean isShowHitbox() {
        return showHitbox;
    }

    /**
     * Returns the weapon's damage, including critical hit calculation.
     * @return the weapon's damage value
     */
    public int getDamage() {
        final Player player = getPlayer();
        final int baseDamage = (getBaseDamage() * player.getAttack()) / 100;
        if (Math.random() * 100 < player.getCritRate()) {
            return (baseDamage * player.getCritDamage()) / 100;
        }
        return baseDamage;
    }

    /**
     * Returns the base damage of the weapon, without critical hit calculation.
     * Subclasses must implement this to provide their specific base damage logic.
     * @return the base damage value
     */
    protected abstract int getBaseDamage();

    /**
     * Returns the hitboxes of the weapon.
     * @return a list of rectangles representing the weapon's hitboxes
     */
    public abstract List<Rectangle> getHitBox();

    /**
     * Handles the weapon's collision logic.
     */
    public abstract void handleCollision();
}
