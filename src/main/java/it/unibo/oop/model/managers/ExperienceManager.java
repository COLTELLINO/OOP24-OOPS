package it.unibo.oop.model.managers;

import java.util.List;

import it.unibo.oop.model.items.ExperienceOrb;

/**
 * Interface for managing experience orbs and player experience.
 */
public interface ExperienceManager {
    /** 
     * @return the current experience points of the player.
     */
    int getCurrentXP();
    /** 
     * @return the amount of experience points needed to reach the next level.
     */
    int getXPToNextLevel();

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
