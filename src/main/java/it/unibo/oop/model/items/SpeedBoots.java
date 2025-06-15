package it.unibo.oop.model.items;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;

/**
 * Represents Speed Boots accessory that increases player speed.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the accessory, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class SpeedBoots extends Accessory {

    private final Player player;
    private int lastLevel;
    private int level;
    private static final int BONUS = 1;

    /**
     * Constructs SpeedBoots accessory.
     *
     * @param player the player associated with this accessory
     */
    public SpeedBoots(final Player player) {
        super(player);
        this.player = player;
        this.level = 1;
    }

    /**
     * Updates the SpeedBoots' state, increasing the player's speed if the level has changed.
     */
    @Override
    public void update() {
        if (lastLevel < getLevel()) {
            lastLevel++;
            player.setSpeed(player.getSpeed() + BONUS);
        }
    }

    /**
     * Gets the level of the SpeedBoots.
     *
     * @return the level of the SpeedBoots
     */
    @Override
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the SpeedBoots.
     *
     * @param level the new level of the SpeedBoots
     */
    @Override
    public void setLevel(final int level) {
        this.level = level;
    }
}
