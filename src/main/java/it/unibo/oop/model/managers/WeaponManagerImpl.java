package it.unibo.oop.model.managers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.lang.reflect.InvocationTargetException;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.items.Upgrade;
import it.unibo.oop.model.items.Weapon;
import it.unibo.oop.model.items.Bow;
import it.unibo.oop.model.items.CursorSaw;
import it.unibo.oop.model.items.HeatWave;
import it.unibo.oop.model.items.MagicStaff;
import it.unibo.oop.model.items.Magnet;
import it.unibo.oop.model.items.ShadowCloak;
import it.unibo.oop.model.items.Shield;
import it.unibo.oop.model.items.SpeedBoots;
import it.unibo.oop.model.items.Sword;
import it.unibo.oop.model.items.BlackBelt;
import it.unibo.oop.model.items.Wax;

/**
 * Implementation of WeaponManager for managing weapons.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "Every weapon needs a player, so this class has to pass it on. " 
        + "and while it's not necessary for player to be externally mutable for this class, it has to be for others.")
public class WeaponManagerImpl implements WeaponManager {
    private final List<Upgrade> upgrades;
    private final List<Class<? extends Upgrade>> upgradePool;
    private final Player player;
    private final Random random;
    private int playerLastLevel = 1;
    private final ProjectileManager projectileManager;

    /**
     * The max level of a weapon.
     */
    public static final int MAX_LEVEL = 5;
    private boolean first = true;
    /**
     * Functional interface to observe enemies and act when a condition is met.
     */
    @FunctionalInterface
    public interface WeaponObserver {
        /**
         * Executes an action in response to an event triggered by an enemy.
         */
        void weaponObserverAction();
    }

    /**
     * Constructs a WeaponManagerImpl.
     * 
     * @param player the player associated with the weapons
     * @param projectileManager
     */
    public WeaponManagerImpl(final Player player, final ProjectileManager projectileManager) {
        this.upgrades = new ArrayList<>();
        this.upgradePool = new ArrayList<>();
        this.player = player;
        this.random = new Random();
        this.projectileManager = projectileManager;
        initializeWeaponPool();
    }

    /** 
     * Initializes the weapon pool with all available weapons.
     */
    private void initializeWeaponPool() {
        upgrades.add(new MagicStaff(player));
        upgradePool.add(Sword.class);
        upgradePool.add(Bow.class);
        upgradePool.add(MagicStaff.class);
        upgradePool.add(Shield.class);
        upgradePool.add(HeatWave.class);
        upgradePool.add(CursorSaw.class);
        upgradePool.add(SpeedBoots.class);
        upgradePool.add(BlackBelt.class);
        upgradePool.add(Wax.class);
        upgradePool.add(ShadowCloak.class);
        upgradePool.add(Magnet.class);
        // Add other weapon types here
    }

    /**
     * Updates every weapon and removes weapons at max level from the pool.
     */
    @Override
    public void update() {
        if (first) {
            setAllObservers(getWeapons());
            first = false;
        }
        for (final Upgrade upgrade : upgrades) {
            upgrade.update();
        }
        if (player.getLevel() > playerLastLevel && !upgradePool.isEmpty()) {
            addChosenUpgrade(upgradePool.get(random.nextInt(upgradePool.size())));
            setAllObservers(getWeapons());
            playerLastLevel++;
        }
    }

    /**
     * Returns the map of weapons and their levels.
     * 
     * @return the map of weapons and their levels
     */
    @Override
    public List<Weapon> getWeapons() {
        final List<Weapon> weapons = new ArrayList<>();
        for (final Upgrade upgrade : upgrades) {
            if (upgrade instanceof Weapon) {
                weapons.add((Weapon) upgrade);
            }
        }
        return weapons;
    }

    /**
     * Returns the map of weapons and their levels.
     * 
     * @return the map of weapons and their levels
     */
    @Override
    public List<Upgrade> getUpgrades() {
        return upgrades;
    }

    /**
     * Returns the map of weapons and their levels.
     * 
     * @return the map of weapons and their levels
     */
    public List<Class<? extends Upgrade>> getUpgradePool() {
        return this.upgradePool;
    }

    /**
     * Sets a specific observer for each weapon that uses it.
     * @param weapons
     */
    private void setAllObservers(final List<Weapon> weapons) {
        for (final Weapon weapon : weapons) {
            if (weapon instanceof Bow) {
                ((Bow) weapon).setObserver(() -> {
                    ((Bow) weapon).getProjectiles().forEach(this.projectileManager::addPlayerProjectile);
                });
            } else if (weapon instanceof MagicStaff) {
                ((MagicStaff) weapon).setObserver(() -> {
                    ((MagicStaff) weapon).getProjectiles().forEach(this.projectileManager::addPlayerProjectile);
                });
            }
        }
    }

    /**
     * Sets the cursor position for the weapons that require it.
     * @param x the x-coordinate of the cursor
     * @param y the y-coordinate of the cursor
     */
    @Override
    public void setCursorPosition(final int x, final int y) {
        for (final Upgrade upgrade : upgrades) {
            if (upgrade instanceof CursorSaw) {
                ((CursorSaw) upgrade).setCursorPosition(x, y);
            }
        }
    }

    /**
     * Returns 3 random upgrade classes from the upgrade pool for the player to choose from.
     * 
     * @return a list of 3 random upgrade classes
     */
    @Override
    public List<Class<? extends Upgrade>> getRandomUpgradesToChoose() {
        if (upgradePool.size() < 3) {
            throw new IllegalStateException("Not enough weapons in the pool to choose from.");
        }
        final List<Class<? extends Upgrade>> shuffledPool = new ArrayList<>(upgradePool);
        Collections.shuffle(shuffledPool, random);
        return shuffledPool.subList(0, 3);
    }

    /**
     * Adds the chosen upgrade to the player's upgrade map or increases its level if already owned.
     * 
     * @param chosenUpgradeClass the class of the upgrade chosen by the player
     */
    @Override
    public void addChosenUpgrade(final Class<? extends Upgrade> chosenUpgradeClass) {
        final Upgrade existingUpgrade = upgrades.stream()
            .filter(upgrade -> upgrade.getClass().equals(chosenUpgradeClass))
            .findFirst()
            .orElse(null);
        if (existingUpgrade != null) {
            existingUpgrade.setLevel(existingUpgrade.getLevel() + 1);
            if (existingUpgrade.getLevel() >= MAX_LEVEL) {
                upgradePool.remove(chosenUpgradeClass);
            }
        } else {
            try {
                final Upgrade newUpgrade = chosenUpgradeClass.getDeclaredConstructor(Player.class).newInstance(player);
                upgrades.add(newUpgrade);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException("Failed to create an instance of " + chosenUpgradeClass.getName(), e);
            }
        }
    }
}
