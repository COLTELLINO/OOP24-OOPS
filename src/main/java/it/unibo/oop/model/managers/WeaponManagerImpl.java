package it.unibo.oop.model.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.items.Bow;
import it.unibo.oop.model.items.MagicStaff;
import it.unibo.oop.model.items.Shield;
import it.unibo.oop.model.items.Sword;
import it.unibo.oop.model.items.Upgrade;
import it.unibo.oop.model.items.Weapon;

/**
 * Implementation of WeaponManager for managing weapons.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "Every weapon needs a player, so this class has to pass it on. " 
        + "and while it's not necessary for player to be externally mutable for this class, it has to be for others.")
public class WeaponManagerImpl implements WeaponManager {
    private final Map<Upgrade, Integer> upgrades;
    private final List<Upgrade> upgradePool;
    private final Player player;
    private final Random random;
    private static final int MAX_LEVEL = 5;

    /**
     * Constructs a WeaponManagerImpl.
     * 
     * @param player the player associated with the weapons
     */
    public WeaponManagerImpl(final Player player) {
        this.upgrades = new HashMap<>();
        this.upgradePool = new ArrayList<>();
        this.player = player;
        this.random = new Random();

        initializeWeaponPool();
    }

    /**
     * Initializes the weapon pool with all available weapons.
     */
    private void initializeWeaponPool() {
        upgrades.put(new Bow(player), 1);
        upgradePool.add(new Sword(player));
        upgradePool.add(new Bow(player));
        upgradePool.add(new MagicStaff(player));
        upgradePool.add(new Shield(player));
        // Add other weapon types here
    }

    /**
     * Updates every weapon and removes weapons at max level from the pool.
     */
    @Override
    public void update() {
        for (final Upgrade upgrade : upgrades.keySet()) {
            upgrade.update();
        }

        upgrades.forEach((upgrade, level) -> {
            if (level >= MAX_LEVEL) {
                upgrades.remove(upgrade);
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
        final Map<Weapon, Integer> weapons = new HashMap<>();
        upgrades.forEach((upgrade, level) -> {
            if (upgrade instanceof Weapon) {
                weapons.put((Weapon) upgrade, level);
            }
        });
        return Collections.unmodifiableMap(weapons);
    }

    /**
     * Returns 3 random upgrades from the upgrade pool for the player to choose from.
     * 
     * @return a list of 3 random upgrades
     */
    @Override
    public List<Upgrade> getRandomUpgradesToChoose() {
        if (upgradePool.size() < 3) {
            throw new IllegalStateException("Not enough weapons in the pool to choose from.");
        }

        final List<Upgrade> shuffledPool = new ArrayList<>(upgradePool);
        Collections.shuffle(shuffledPool, random);
        return shuffledPool.subList(0, 3);
    }

    /**
     * Adds the chosen upgrade to the player's upgrade map or increases its level if already owned.
     * 
     * @param chosenUpgrade the upgrade chosen by the player
     */
    @Override
    public void addChosenUpgrade(final Upgrade chosenUpgrade) {
        if (!upgradePool.contains(chosenUpgrade)) {
            throw new IllegalArgumentException("The chosen weapon is not in the weapon pool.");
        }
        if (upgrades.containsKey(chosenUpgrade)) {
            final int currentLevel = upgrades.get(chosenUpgrade);
            upgrades.put(chosenUpgrade, currentLevel + 1);
        } else {
            upgrades.put(chosenUpgrade, 1);
        }
    }
}
