package it.unibo.oop.model;

import java.util.List;
import java.util.Map;

/**
 * Interface for managing weapons.
 */
public interface WeaponManager {
    /**
     * Updates every weapon.
     */
    void update();

    /**
    * Adds the chosen weapon to the player's weapon map or increases its level if already owned.
    * 
    * @param chosenUpgrade the weapon chosen by the player
    */
   void addChosenUpgrade(Upgrade chosenUpgrade);
       /**
     * Returns 3 random upgrades from the upgrade pool for the player to choose from.
     * 
     * @return a list of 3 random upgrades
     */
    List<Upgrade> getRandomUpgradesToChoose();

    /**
     * Returns the list of weapons.
     * 
     * @return the list of weapons
     */
    Map<Weapon, Integer> getWeapons();
}
