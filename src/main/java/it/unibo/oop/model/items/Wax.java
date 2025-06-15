package it.unibo.oop.model.items;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;

/**
 * Represents a Wax accessory that increases the player's critical hit rate.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the accessory, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class Wax extends Accessory {

    private final Player player;
    private int level;
    private int lastLevel;
    private static final int BONUS = 20;

    /**
     * Constructs a Wax accessory.
     *
     * @param player the player associated with this accessory
     */
    public Wax(final Player player) {
        super(player);
        this.player = player;
        this.level = 1;
    }

    /**
     * Updates the Wax's state, increasing the player's critical hit rate if the level has changed.
     */
    @Override
    public void update() {
        if (lastLevel < getLevel()) {
            lastLevel++;
            player.setCritRate(player.getCritRate() + BONUS);
        }
    }

    /**
     * Gets the level of the Wax.
     *
     * @return the level of the Wax
     */
    @Override
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the Wax.
     *
     * @param level the new level of the Wax
     */
    @Override
    public void setLevel(final int level) {
        this.level = level;
    }
}
