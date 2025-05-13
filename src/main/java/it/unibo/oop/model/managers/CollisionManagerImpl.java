package it.unibo.oop.model.managers;

import java.util.Set;
import java.util.HashMap;
import java.util.Map;

import it.unibo.oop.model.entities.Enemy;
import it.unibo.oop.model.items.Weapon;

import java.awt.Rectangle;

/**
 * Class managing collisions between game objects.
 */
public class CollisionManagerImpl implements CollisionManager {
    private static final int DEFAULT_IFRAME_DURATION = 30;
    private final Map<Enemy, Integer> enemyCooldowns = new HashMap<>();

    /**
     * Check if two objects are colliding.
     * @param h1 the first object
     * @param h2 the second object
     * @return true if the objects are colliding, false otherwise
     */
    @Override
    public boolean isColliding(final Rectangle h1, final Rectangle h2) {
        return h1.intersects(h2);
    }

    /**
     * Updates the cooldowns for all entities.
     */
    @Override
    public void update() {
        enemyCooldowns.replaceAll((enemy, cooldown) -> Math.max(0, cooldown - 1));
    }

    /**
     * Checks if an enemy can take damage.
     * 
     * @param enemy the enemy to check
     * @return true if the enemy can take damage, false otherwise
     */
    @Override
    public boolean canTakeDamage(final Enemy enemy) {
        return enemyCooldowns.getOrDefault(enemy, 0) == 0;
    }

    /**
     * Registers damage for an enemy, starting its i-frame cooldown.
     * 
     * @param enemy the enemy that took damage
     */
    @Override
    public void registerDamage(final Enemy enemy) {
        enemyCooldowns.put(enemy, DEFAULT_IFRAME_DURATION);
    }

    /**
     * Handle collision weapon/enemies.
     * @param enemies the enemy list
     * @param weapon the weapon
     */
    @Override
    public void handleWeaponCollision(final Set<Enemy> enemies, final Weapon weapon) {
        for (final Enemy enemy : enemies) {
            if (canTakeDamage(enemy)) {
                enemy.setHealth(enemy.getHealth() - weapon.getDamage());
                registerDamage(enemy);
            }
        }
    }
}
