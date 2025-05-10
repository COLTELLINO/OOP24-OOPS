package it.unibo.oop.model.managers;


import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;


/**
 * Implementation of HealthManager for handling player's health logic.
 */
@SuppressFBWarnings(value = {"EI2"}, justification = "Player must be mutable for health updates.")
public final class HealthManagerImpl implements HealthManager {


    private final Player player;


    /**
     * Constructs a HealthManagerImpl for the given player.
     * @param player
     */
    public HealthManagerImpl(final Player player) {
        this.player = player;
    }


    @Override
    public int getHealth() {
        return player.getHealth();
    }


    @Override
    public int getMaxHealth() {
        return player.getMaxHealth();
    }


    @Override
    public boolean isAlive() {
        return player.isAlive();
    }


    @Override
    public void update() {
        if (player.getHealth() == 0) {
            player.setAlive(false);
        }
    }
}
