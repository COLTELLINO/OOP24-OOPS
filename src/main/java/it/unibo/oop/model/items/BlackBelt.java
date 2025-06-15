package it.unibo.oop.model.items;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;

/**
 * Represents a Black Belt accessory that increases player attack.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "To position the accessory, the player size and position are needed, "
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class BlackBelt extends Accessory {

    private final Player player;
    private int level;
    private int lastLevel;
    private static final int BONUS = 10;

    /**
     * Constructs a BlackBelt accessory.
     * 
     * @param player the player associated with this accessory
     */
    public BlackBelt(final Player player) {
        super(player);
        this.player = player;
        this.level = 1;
    }

    /**
     * Updates the BlackBelt's state, increasing the player's attack if the level has changed.
     */
    @Override
    public void update() {
        if (lastLevel < getLevel()) {
            lastLevel++;
            player.setAttack(player.getAttack() + BONUS);
        }
    }

    /**
     * Gets the level of the BlackBelt.
     * 
     * @return the level of the BlackBelt
     */
    @Override
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the BlackBelt.
     * 
     * @param level the new level of the BlackBelt
     */
    @Override
    public void setLevel(final int level) {
        this.level = level;
    }
}
