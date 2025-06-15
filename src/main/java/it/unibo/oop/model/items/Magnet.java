package it.unibo.oop.model.items;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;

/**
 * Represents a Magnet accessory that increases the player's pickup range.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the accessory, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class Magnet extends Accessory {

    private final Player player;
    private int level;
    private int lastLevel;
    private static final int BONUS = 20;

    /**
     * Creates a new Magnet instance.
     * @param player the player associated with this magnet
     */
    public Magnet(final Player player) {
        super(player);
        this.player = player;
        this.level = 1;
    }

    /**
     * Updates the magnet's state.
     */
    @Override
    public void update() {
        if (lastLevel < getLevel()) {
            lastLevel++;
            player.setPickupRange(player.getPickupRange() + BONUS);
        }
    }

    /**
     * Gets the level of the magnet.
     * 
     * @return the level of the magnet
     */
    @Override
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the magnet.
     * 
     * @param level the new level of the magnet
     */
    @Override
    public void setLevel(final int level) {
        this.level = level;
    }
}
