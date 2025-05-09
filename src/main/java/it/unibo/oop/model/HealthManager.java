package it.unibo.oop.model;


/**
 * Interface for managing the health of an entity.
 */
public interface HealthManager {


    /**
     * Gets the current health of the entity.
     * @return the current health
     */
    int getHealth();


    /**
     * Gets the maximum health of the entity.
     * @return the maximum health
     */
    int getMaxHealth();


    /**
     * Checks if the entity is alive.
     * @return true if the entity is alive, false otherwise
     */
    boolean isAlive();


    /**
     * Updates the health status of the entity.
     */
    void update();
}
