package it.unibo.oop.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Abstract class representing an upgrade (Weapon or Accessory).
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "Every weapon needs a player, so this class has to pass it on. " 
        + "and while it's not necessary for player to be externally mutable for this class, it has to be for others.")
public abstract class Upgrade {
    private int level;
    private final Player player;

    /**
     * Constructs an Upgrade.
     * 
     * @param player the player associated with this upgrade
     */
    public Upgrade(final Player player) {
        this.player = player;
        this.level = 1;
    }

    /**
     * Updates the upgrade. Specific behavior is implemented in subclasses.
     */
    public abstract void update();

    /**
     * Gets the current level of the upgrade.
     * 
     * @return the level of the upgrade
     */
    public int getLevel() {
        return level;
    }

    /**
     * Increases the level of the upgrade.
     */
    public void levelUp() {
        this.level++;
    }

    /**
     * Gets the player associated with this upgrade.
     * 
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
}
