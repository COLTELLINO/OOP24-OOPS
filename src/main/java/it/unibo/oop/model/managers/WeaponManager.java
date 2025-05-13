package it.unibo.oop.model.managers;

import java.util.List;

import it.unibo.oop.model.items.Upgrade;
import it.unibo.oop.model.items.Weapon;

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
    * @param chosenUpgradeClass the class of the weapon chosen by the player
    */
    void addChosenUpgrade(Class<? extends Upgrade> chosenUpgradeClass);
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
    List<Weapon> getWeapons();
}
