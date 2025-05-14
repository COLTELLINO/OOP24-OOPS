package it.unibo.oop.model.managers;

import java.util.Set;
import java.util.Collections;
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
    private static final int WEAPON_IFRAME = 30;
    private static final int PROJECTILE_IFRAME = 60;
    private final Map<Entity, Integer> entityCooldowns = new HashMap<>();
    private final Map<Entity, Map<Projectile, Integer>> projectileCooldowns = new HashMap<>();

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
        removeDeadEntities();
        entityCooldowns.replaceAll((enemy, cooldown) -> Math.max(0, cooldown - 1));
        for (Map<Projectile, Integer> cooldownMap : projectileCooldowns.values()) {
            cooldownMap.replaceAll((proj, cooldown) -> Math.max(0, cooldown - 1));
        }
    }
    private void removeDeadEntities() {
        entityCooldowns.entrySet().removeIf(e -> !e.getKey().isAlive());
        projectileCooldowns.entrySet().removeIf(entry -> !entry.getKey().isAlive());
    }
    /**
     * Checks if an entity can take damage.
     * 
     * @param entity the entity to check
     * @return true if the entity can take damage, false otherwise
     */
    private boolean canTakeWeaponDamage(final Entity entity) {
        return entityCooldowns.getOrDefault(entity, 0) == 0;
    }
    /**
     * Checks if an entity can take damage from a specific projectile.
     * @param entity the entity to check
     * @param projectile the projectile to check
     * @return true if the entity can take damage, false otherwise
     */
    private boolean canTakeProjectileDamage(final Entity entity, final Projectile projectile) {
        return projectileCooldowns
            .getOrDefault(entity, Collections.emptyMap())
            .getOrDefault(projectile, 0) == 0;
    }
    /**
     * Registers damage for an entity, starting its i-frame cooldown.
     * 
     * @param entity the entity that took damage
     */
    private void registerWeaponDamage(final Entity entity) {
        entityCooldowns.put(entity, WEAPON_IFRAME);
    }
    /**
     * Registers damage for an entity, starting its i-frame cooldown.
     * @param entity the entity that took damage
     * @param projectile the projectile that hit
     */
    private void registerProjectileDamage(final Entity entity, final Projectile projectile) {
        projectileCooldowns
            .computeIfAbsent(entity, k -> new HashMap<>())
            .put(projectile, PROJECTILE_IFRAME);
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
            if (canTakeWeaponDamage(enemy)) {
                enemy.setHealth(enemy.getHealth() - weapon.getDamage());
                registerWeaponDamage(enemy);
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
            for (final Projectile projectile : projectiles) {
                if (isColliding(enemy.getHitbox(), projectile.getProjectileHitBox())) {
                    projectile.handleCollision();
                    if (canTakeProjectileDamage(enemy, projectile) && isColliding(enemy.getHitbox(), projectile.getProjectileHitBox()) 
                    && projectile.getDamage() > 0) {
                        enemy.setHealth(enemy.getHealth() - projectile.getDamage());
                        registerProjectileDamage(enemy, projectile);
                    }
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
        for (final Projectile projectile : projectiles) {
            if (canTakeProjectileDamage(player, projectile) && isColliding(player.getHitbox(), projectile.getProjectileHitBox())) {
                player.setHealth(player.getHealth() - projectile.getDamage());
                registerProjectileDamage(player, projectile);
            }
        }
    }
}
