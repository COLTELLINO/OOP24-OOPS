package it.unibo.oop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Implementation of WeaponManager for managing weapons.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "Every weapon needs a player, so this class has to pass it on. " 
        + "and while it's not necessary for player to be externally mutable for this class, it has to be for others.")
public class WeaponManagerImpl implements WeaponManager {
    private final Map<Weapon, Integer> weapons;
    private final List<Weapon> weaponPool;
    private final Player player;
    private final Random random;
    private static final int MAX_LEVEL = 5;

    /**
     * Constructs a WeaponManagerImpl.
     * 
     * @param player the player associated with the weapons
     */
    public WeaponManagerImpl(final Player player) {
        this.weapons = new HashMap<>();
        this.weaponPool = new ArrayList<>();
        this.player = player;
        this.random = new Random();

        initializeWeaponPool();
    }

    /**
     * Initializes the weapon pool with all available weapons.
     */
    private void initializeWeaponPool() {
        weapons.put(new Sword(player), 1);
        weaponPool.add(new Sword(player));
        weaponPool.add(new Bow(player));
        weaponPool.add(new MagicStaff(player));
        // Add other weapon types here
    }

    /**
     * Updates every weapon and removes weapons at max level from the pool.
     */
    @Override
    public void update() {
        for (final Weapon weapon : weapons.keySet()) {
            weapon.update();
        }

        weapons.forEach((weapon, level) -> {
            if (level >= MAX_LEVEL) {
                weaponPool.remove(weapon);
            }
        });
    }

    /**
     * Returns the map of weapons and their levels.
     * 
     * @return the map of weapons and their levels
     */
    @Override
    public Map<Weapon, Integer> getWeapons() {
        return Collections.unmodifiableMap(weapons);
    }

    /**
     * Returns 3 random weapons from the weapon pool for the player to choose from.
     * 
     * @return a list of 3 random weapons
     */
    @Override
    public List<Weapon> getRandomWeaponsToChoose() {
        if (weaponPool.size() < 3) {
            throw new IllegalStateException("Not enough weapons in the pool to choose from.");
        }

        final List<Weapon> shuffledPool = new ArrayList<>(weaponPool);
        Collections.shuffle(shuffledPool, random);
        return shuffledPool.subList(0, 3);
    }

    /**
     * Adds the chosen weapon to the player's weapon map or increases its level if already owned.
     * 
     * @param chosenWeapon the weapon chosen by the player
     */
    @Override
    public void addChosenWeapon(final Weapon chosenWeapon) {
        if (!weaponPool.contains(chosenWeapon)) {
            throw new IllegalArgumentException("The chosen weapon is not in the weapon pool.");
        }
        if (weapons.containsKey(chosenWeapon)) {
            final int currentLevel = weapons.get(chosenWeapon);
            weapons.put(chosenWeapon, currentLevel + 1);
        } else {
            weapons.put(chosenWeapon, 1);
        }
    }
}
