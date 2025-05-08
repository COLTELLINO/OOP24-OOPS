package it.unibo.oop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Implementation of ExperienceManager for managing experience orbs and player experience.
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "To give the player xp it needs to be externally mutable.")
public class ExperienceManagerImpl implements ExperienceManager {
    private final List<ExperienceOrb> orbs;
    private final Player player;

    /**
     * Constructs an ExperienceManagerImpl.
     * 
     * @param player the player associated with the experience manager
     */
    public ExperienceManagerImpl(final Player player) {
        this.orbs = new ArrayList<>();
        this.player = player;
    }
    /**
     * Updates the experience orbs.
     */
    @Override
    public void update() {
        for (final ExperienceOrb orb : new ArrayList<>(orbs)) {
            if (Math.abs(player.getX() - orb.getX()) <= 10 
                && Math.abs(player.getY() - orb.getY()) <= 10) {
                orbs.remove(orb);
                this.player.addXp(orb.getXP());
            }
        }
    }
    /**
     * Returns a copy of the orbs list.
     */
    @Override
    public List<ExperienceOrb> getOrbs() {
        return Collections.unmodifiableList(orbs);
    }
    /**
     * Spawns an experience orb.
     * @param x the x-coordinate of the orb
     * @param y the y-coordinate of the orb
     * @param amount the amount of experience the orb gives
     */
    @Override
    public void spawnXP(final int x, final int y, final int amount) {
        orbs.add(new ExperienceOrb(x, y, amount));
    }

    @Override
    public int getCurrentXP() {
        return player.getCurrentXP();
    }

    @Override
    public int getXPToNextLevel() {
        return player.getXPToNextLevel();
    }
}
