package it.unibo.oop.model.managers;

import java.util.List;

import it.unibo.oop.model.items.Upgrade;
import it.unibo.oop.model.items.Weapon;

/**
 * Interface for managing weapons.
 */
public interface WeaponManager {

    /**
     * MAX LEVEL of a weapon.
     */
    int MAX_LEVEL = 5;
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
    List<Class<? extends Upgrade>> getRandomUpgradesToChoose();

    /**
     * Returns the list of weapons.
     * 
     * @return the list of weapons
     */
    List<Weapon> getWeapons();

    void setCursorPosition(int x, int y);
    /**
     * Returns the map of weapons and their levels.
     * 
     * @return the map of weapons and their levels
     */
    List<Upgrade> getUpgrades();

}
