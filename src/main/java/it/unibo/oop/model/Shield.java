package it.unibo.oop.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a Shield weapon in the game.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the weapon, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class Shield extends Weapon {

    private boolean isEquipped;
    private final Player player;

    /**
     * Creates a new Shield instance.
     * @param player the player associated with this shield
     */
    public Shield(final Player player) {
        this.player = player;
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
}
