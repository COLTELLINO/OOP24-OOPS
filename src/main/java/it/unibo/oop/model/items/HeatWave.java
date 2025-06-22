package it.unibo.oop.model.items;

import java.awt.Rectangle;
import java.util.Collections;
import java.util.List;

import it.unibo.oop.model.entities.Player;

/**
* Represents a HeatWave weapon that creates a damaging wave around the player.
*/
public class HeatWave extends Weapon {

    private static final int BASE_DAMAGE = 5;
    private static final int DAMAGE_PER_LEVEL = 1;
    private static final int BASE_RADIUS = 100;
    private static final int RADIUS_PER_LEVEL = 15;
    private static final int BASE_COOLDOWN = 90;
    private static final int COOLDOWN_DECREASE_PER_LEVEL = 20;
    private static final int MIN_COOLDOWN = 30;
    private static final int DURATION = 15;

    private int duration;
    private int cooldown;
    private boolean isActive;

    /**
     * Creates a new HeatWave instance for the specified player.
     * @param player the player associated with this HeatWave
     */
    public HeatWave(final Player player) {
        super(player);
        this.duration = DURATION;
    }

    /**
     * Gets the base damage dealt by the HeatWave.
     * @return the base damage dealt by the HeatWave
     */
    @Override
    protected int getBaseDamage() {
        return BASE_DAMAGE + ((getLevel() - 1) * DAMAGE_PER_LEVEL);
    }

    /**
     * Gets the radius of the HeatWave.
     * @return the radius of the HeatWave based on its level
     */
    public int getRadius() {
        return BASE_RADIUS + (getLevel() - 1) * RADIUS_PER_LEVEL;
    }

    /**
     * Gets the cooldown of the HeatWave.
     * @return the cooldown in ticks, which decreases with each level
     */
    public int getCooldown() {
        final int cd = BASE_COOLDOWN - (getLevel() - 1) * COOLDOWN_DECREASE_PER_LEVEL;
        return Math.max(cd, MIN_COOLDOWN);
    }

    /**
     * Gets the hitbox of the HeatWave.
     * @return a list containing the bounding rectangle of the HeatWave if active, otherwise an empty list.
     */
    @Override
    public List<Rectangle> getHitBox() {
        if (isActive) {
            final int radius = getRadius();
            final int x = getPlayer().getX() + getPlayer().getSize() / 2 - radius;
            final int y = getPlayer().getY() + getPlayer().getSize() / 2 - radius;
            final Rectangle bounds = new Rectangle(x, y, radius * 2, radius * 2);
            return Collections.singletonList(bounds);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Updates the state of the HeatWave.
     */
    @Override
    public void update() {
        if (isActive) {
            duration--;
            if (duration <= 0) {
                isActive = false;
                this.cooldown = getCooldown();
                this.duration = DURATION;
            }
        } else {
            if (cooldown <= 0) {
                this.isActive = true;
            } else {
                cooldown--;
            }
        }
    }

    /**
     * If the HeatWave is active.
     * @return true if the HeatWave is currently active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }
}
