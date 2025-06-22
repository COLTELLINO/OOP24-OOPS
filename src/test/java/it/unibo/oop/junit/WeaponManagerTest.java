package it.unibo.oop.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.items.Bow;
import it.unibo.oop.model.items.MagicStaff;
import it.unibo.oop.model.items.Sword;
import it.unibo.oop.model.items.Upgrade;
import it.unibo.oop.model.items.Weapon;
import it.unibo.oop.model.managers.AudioManager;
import it.unibo.oop.model.managers.ProjectileManager;
import it.unibo.oop.model.managers.AudioManagerImpl;
import it.unibo.oop.model.managers.ProjectileManagerImpl;
import it.unibo.oop.model.managers.WeaponManagerImpl;

class WeaponManagerTest {

    private static final AudioManager AUDIOMANAGER = new AudioManagerImpl();
    private WeaponManagerImpl weaponManager;
    private static final int SPEED = 5;
    private static final int SIZE = 50;

    @BeforeEach
    @SuppressWarnings("checkstyle:MagicNumber")
    void setUp() {
        final Player player = new Player(0, 0, 100, 100, 10, SPEED, SIZE);
        final ProjectileManager projectileManager = new ProjectileManagerImpl(AUDIOMANAGER);
        weaponManager = new WeaponManagerImpl(player, projectileManager);
    }

    @Test
    void testInitializeWeaponPool() {
        final List<Weapon> weapons = weaponManager.getWeapons();
        assertEquals(1, weapons.size(), "Weapon pool should initially contain one weapon (Sword).");
        assertTrue(weapons.get(0) instanceof Sword, "The initial weapon should be a Sword.");
    }

    @Test
    void testUpdateAndAddChosenUpgrade() {

        final List<Class<? extends Upgrade>> upgradesToChoose = weaponManager.getRandomUpgradesToChoose();
        assertEquals(3, upgradesToChoose.size(), "There should be 3 upgrades to choose from.");

        weaponManager.addChosenUpgrade(upgradesToChoose.get(0));
        final List<Upgrade> upgrades = weaponManager.getUpgrades();
        if (upgradesToChoose.get(0).equals(Sword.class)) {
            assertEquals(1, upgrades.size(), "Player weapons should now contain two weapons.");
        } else {
            assertEquals(2, upgrades.size(), "Player weapons should now contain two weapons.");
        }
    }

    @Test
    void testMaxLevelUpgrade() {

        weaponManager.addChosenUpgrade(Bow.class);
        final Weapon bow = weaponManager.getWeapons().stream()
            .filter(weapon -> weapon instanceof Bow)
            .findFirst()
            .orElse(null);

        assertNotNull(bow, "Bow should be added to the weapon pool.");
        for (int i = 0; i < WeaponManagerImpl.MAX_LEVEL - 1; i++) {
            weaponManager.addChosenUpgrade(Bow.class);
        }

        assertEquals(WeaponManagerImpl.MAX_LEVEL, bow.getLevel(), "Bow should reach max level.");
        assertFalse(weaponManager.getUpgradePool().contains(Bow.class),
            "Bow should be removed from the upgrade pool after reaching max level.");
    }

    @Test
    void testSetAllObservers() {
        weaponManager.update();
        final List<Weapon> weapons = weaponManager.getWeapons();

        for (final Weapon weapon : weapons) {
            if (weapon instanceof Bow) {
                final Bow bow = (Bow) weapon;
                assertNotNull(bow.getObserver(), "Bow should have an observer set.");
            } else if (weapon instanceof MagicStaff) {
                final MagicStaff staff = (MagicStaff) weapon;
                assertNotNull(staff.getObserver(), "MagicStaff should have an observer set.");
            }
        }
    }
}
