package it.unibo.oop.model;

import java.util.List;

/**
 * Interface for managing weapons.
 */
public interface WeaponManager {
    /**
     * Updates every weapon.
     */
    void update();

    /**
     * Returns the list of weapons.
     * 
     * @return the list of weapons
     */
    List<Weapon> getWeapons();
}
