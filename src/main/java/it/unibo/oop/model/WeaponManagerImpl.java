package it.unibo.oop.model;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Implementation of WeaponManager for managing weapons.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "Every weapon needs a player, so this class has to pass it on. " 
        + "and while it's not necessary for player to be externally mutable for this class, it has to be for others.")
public class WeaponManagerImpl implements WeaponManager {
    private final List<Weapon> weapons;
    private final Player player;

    /**
     * Constructs a WeaponManagerImpl.
     * 
     * @param player the player associated with the weapons
     */
    public WeaponManagerImpl(final Player player) {
        weapons = new ArrayList<>();
        this.player = player;
    }

    /**
     * Updates every weapon.
     */
    @Override
    public void update() {
        if (!weapons.stream().anyMatch(item -> item instanceof Sword)) {
            weapons.add(new Sword(player));
        }
        for (final Weapon weapon : weapons) {
            weapon.update();
        }
    }

    /**
     * Returns the list of weapons.
     * 
     * @return the list of weapons
     */
    @Override
    public List<Weapon> getWeapons() {
        return weapons;
    }
}
