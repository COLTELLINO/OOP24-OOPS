package it.unibo.oop.model.items;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;

/**
 * Represents a Shield weapon in the game.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the weapon, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class Shield extends Accessory {

    private boolean isEquipped;
    private final Player player;
    private int level;

    /**
     * Creates a new Shield instance.
     * @param player the player associated with this shield
     */
    public Shield(final Player player) {
        super(player);
        this.player = player;
        this.level = 1;
    }

    /**
     * Updates the shield's state.
     */
    @Override
    public void update() {
        if (!isEquipped) {
            player.setHealth(player.getHealth() + 100);
            this.isEquipped = true;
        }
    }

    /**
     * Gets the level of the shield.
     * 
     * @return the level of the shield
     */
    @Override
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the shield.
     * 
     * @param level the new level of the shield
     */
    @Override
    public void setLevel(final int level) {
        this.level = level;
    }
}
