package it.unibo.oop.model.managers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.lang.reflect.InvocationTargetException;

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
    private final List<Upgrade> upgrades;
    private final List<Class<? extends Upgrade>> upgradePool;
    private final Player player;
    private final Random random;
    private int playerLastLevel = 1;
    final ProjectileManager projectileManager;
    private static final int MAX_LEVEL = 5;
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
        setAllObservers(getWeapons());
        // Add other weapon types here
    }

    /**
     * Updates every weapon and removes weapons at max level from the pool.
     */
    @Override
    public void update() {
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

    private void setAllObservers(List<Weapon> weapons) {
        for (Weapon weapon : weapons) {
            if (weapon instanceof Bow) {
                ((Bow)weapon).setObserver(() -> {
                    ((Bow)weapon).getProjectiles().forEach(p -> this.projectileManager.addPlayerProjectile(p));
                });
            } else if (weapon instanceof MagicStaff) {
                ((MagicStaff)weapon).setObserver(() -> {
                    ((MagicStaff)weapon).getProjectiles().forEach(p -> this.projectileManager.addPlayerProjectile(p));
                });
            }
        }
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
        final List<Class<? extends Upgrade>> shuffledPool = new ArrayList<>(upgradePool);
        Collections.shuffle(shuffledPool, random);
        final List<Upgrade> upgradesToChoose = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            try {
                upgradesToChoose.add(shuffledPool.get(i)
                    .getDeclaredConstructor(Player.class).newInstance(player));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException("Failed to instantiate upgrade: " + shuffledPool.get(i).getName(), e);
            }
        }
        return upgradesToChoose;
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
