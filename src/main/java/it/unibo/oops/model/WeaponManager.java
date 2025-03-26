package it.unibo.oops.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
/**
 * 
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "Every weapon needs a player, so this class has to pass it on. " 
        + "and while it's not necessary for player to be externally mutable for this class, it has to be for others.")
public class WeaponManager {
    private final List<Weapon> weapons;
    private final Player player;
    /**
     * @param player
     */
    public WeaponManager(final Player player) {
        weapons = new ArrayList<>();
        this.player = player;
    }
    /**
     * Updates every weapon.
     */
    public void update() {
        if (!weapons.stream().anyMatch(item -> item instanceof Sword)) {
            weapons.add(new Sword(player));
        }
        for (final Weapon weapon : weapons) {
            weapon.update();
        }
    }
    /**
    * Draws all the items in the list.
    * @param g
    */
    public void draw(final Graphics g) {
        for (final Weapon weapon : weapons) {
            weapon.draw(g);
        }
    }
}
