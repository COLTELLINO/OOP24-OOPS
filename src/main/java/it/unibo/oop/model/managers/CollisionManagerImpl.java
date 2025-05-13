package it.unibo.oop.model.managers;

import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.oop.model.entities.Enemy;
import it.unibo.oop.model.entities.Entity;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.items.Weapon;
import it.unibo.oop.model.projectiles.Projectile;

import java.awt.Rectangle;

/**
 * Class managing collisions between game objects.
 */
public class CollisionManagerImpl implements CollisionManager {
    private static final int DEFAULT_IFRAME_DURATION = 30;
    private final Map<Entity, Integer> entityCooldowns = new HashMap<>();

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
        entityCooldowns.replaceAll((enemy, cooldown) -> Math.max(0, cooldown - 1));
    }

    /**
     * Checks if an entity can take damage.
     * 
     * @param entity the entity to check
     * @return true if the entity can take damage, false otherwise
     */
    @Override
    public boolean canTakeDamage(final Entity entity) {
        return entityCooldowns.getOrDefault(entity, 0) == 0;
    }

    /**
     * Registers damage for an entity, starting its i-frame cooldown.
     * 
     * @param entity the entity that took damage
     */
    @Override
    public void registerDamage(final Entity entity) {
        entityCooldowns.put(entity, DEFAULT_IFRAME_DURATION);
    }

    /**
     * Handle collision weapon/enemies.
     * @param enemies the enemy list
     * @param weapon the weapon
     */
    @Override
    public void handleWeaponCollision(final Set<Enemy> enemies, final Weapon weapon) {
        weapon.handleCollision();
        for (final Enemy enemy : enemies) {
            if (canTakeDamage(enemy)) {
                enemy.setHealth(enemy.getHealth() - weapon.getDamage());
                registerDamage(enemy);
            }
        }
    }
    /**
     * Handle collision between enemies and projectiles.
     * @param enemies the enemy list
     * @param projectiles the projectile list
     */
    @Override
    public void handleEnemyProjectilenCollision(final List<Enemy> enemies, final List<Projectile> projectiles) {
        for (final Enemy enemy : enemies) {
            for (Projectile projectile : projectiles) {
                if (canTakeDamage(enemy) && isColliding(enemy.getHitbox(), projectile.getProjectileHitBox())) {
                    enemy.setHealth(enemy.getHealth() - projectile.getDamage());
                    registerDamage(enemy);
                }   
            }
        }
    }

    /**
     * Handle collision between the player and enemy projectiles.
     * @param player
     * @param projectiles the projectile list
     */
    @Override
    public void handlePlayerProjectilenCollision(final Player player, final List<Projectile> projectiles) {
            for (Projectile projectile : projectiles) {
                if (canTakeDamage(player) && isColliding(player.getHitbox(), projectile.getProjectileHitBox())) {
                    player.setHealth(player.getHealth() - projectile.getDamage());
                    registerDamage(player);
                }
            }   
        }
    }
