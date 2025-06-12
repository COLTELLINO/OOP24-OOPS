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
    private static final int BASE_BONUS = 3;
    private static final int SCALER = 3;

    public BlackBelt(final Player player) {
        super(player);
        this.player = player;
        this.level = 1;
    }

    @Override
    public void update() {
        if (lastLevel < getLevel()) {
            lastLevel++;
            player.setAttack(player.getAttack() + BASE_BONUS + (getLevel() - 1) * SCALER);
        }
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(final int level) {
        this.level = level;
    }
}