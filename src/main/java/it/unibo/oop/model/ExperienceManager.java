package it.unibo.oop.model;

import java.util.List;

/**
 * Interface for managing experience orbs and player experience.
 */
public interface ExperienceManager {
    /**
     * Updates the state of experience orbs.
     */
    void update();

    /**
     * Returns an unmodifiable view of the experience orbs.
     * 
     * @return the list of experience orbs
     */
    List<ExperienceOrb> getOrbs();

    /**
     * Spawns a new experience orb at the specified location.
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param amount the amount of experience
     */
    void spawnXP(int x, int y, int amount);
}
