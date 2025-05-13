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
import it.unibo.oop.model.managers.ProjectileManager;
import it.unibo.oop.model.managers.ProjectileManagerImpl;
import it.unibo.oop.model.managers.WeaponManagerImpl;

class WeaponManagerTest {

    private WeaponManagerImpl weaponManager;
    private Player player;
    private static final int SPEED = 5;
    private static final int SIZE = 50;

    @BeforeEach
    void setUp() {
        player = new Player(0, 0, 100, 100, 10, SPEED, SIZE); // Simula un giocatore
        final ProjectileManager projectileManager = new ProjectileManagerImpl(); // Variabile locale
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
        // Simula un livello del giocatore che aumenta
        player.levelUp();
        player.levelUp();
        weaponManager.update();

        // Verifica che ci siano 3 opzioni di upgrade
        final List<Upgrade> upgradesToChoose = weaponManager.getRandomUpgradesToChoose();
        assertEquals(3, upgradesToChoose.size(), "There should be 3 upgrades to choose from.");

        // Aggiungi un upgrade scelto
        weaponManager.addChosenUpgrade(upgradesToChoose.get(0).getClass());
        final List<Weapon> weapons = weaponManager.getWeapons();
        assertEquals(2, weapons.size(), "Weapon pool should now contain two weapons.");
    }

    @Test
    void testMaxLevelUpgrade() {
        // Aggiungi un'arma e portala al livello massimo
        weaponManager.addChosenUpgrade(Bow.class); // Passa la classe corretta
        final Weapon bow = weaponManager.getWeapons().stream()
            .filter(weapon -> weapon instanceof Bow)
            .findFirst()
            .orElse(null);

        assertNotNull(bow, "Bow should be added to the weapon pool.");
        for (int i = 0; i < WeaponManagerImpl.MAX_LEVEL - 1; i++) {
            weaponManager.addChosenUpgrade(Bow.class); // Continua a passare la classe corretta
        }

        assertEquals(WeaponManagerImpl.MAX_LEVEL, bow.getLevel(), "Bow should reach max level.");
        assertFalse(weaponManager.getUpgradePool().contains(Bow.class),
            "Bow should be removed from the upgrade pool after reaching max level.");
    }

    @Test
    void testSetAllObservers() {
        weaponManager.update(); // Inizializza gli observer
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
