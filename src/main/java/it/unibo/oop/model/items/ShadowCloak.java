package it.unibo.oop.model.items;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;

/**
 * Represents a ShadowCloak accessory that increases the player's critical damage.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the accessory, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class ShadowCloak extends Accessory {

    private final Player player;
    private int level;
    private int lastLevel;
    private static final int BONUS = 20;

    /**
    * Constructs a ShadowCloak accessory.
    *
    * @param player the player associated with this accessory
    */
    public ShadowCloak(final Player player) {
        super(player);
        this.player = player;
        this.level = 1;
    }

    /**
     * Updates the ShadowCloak's state, increasing the player's critical damage if the level has changed.
     */
    @Override
    public void update() {
        if (lastLevel < getLevel()) {
            lastLevel++;
            player.setCritDamage(player.getCritDamage() + BONUS);
        }
    }

    /**
     * Gets the level of the ShadowCloak.
     *
     * @return the level of the ShadowCloak
     */
    @Override
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the ShadowCloak.
     *
     * @param level the new level of the ShadowCloak
     */
    @Override
    public void setLevel(final int level) {
        this.level = level;
    }
}
