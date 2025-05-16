package it.unibo.oop.model.managers;

import java.awt.Rectangle;
import java.util.List;
import java.util.Set;

import it.unibo.oop.model.entities.Enemy;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.items.Weapon;
import it.unibo.oop.model.projectiles.Projectile;

/**
 * Interface for managing collisions between game objects.
 */
public interface CollisionManager {
    /**
     * Check if two objects are colliding.
     * @param h1 the first object
     * @param h2 the second object
     * @return true if the objects are colliding, false otherwise
     */
    boolean isColliding(Rectangle h1, Rectangle h2);

    /**
     * Handle collision weapon/enemies.
     * @param enemies the enemy list
     * @param weapon the weapon
     */
    void handleWeaponCollision(Set<Enemy> enemies, Weapon weapon);

    /**
     * Handle collision between enemies and the player's projectiles.
     * @param enemies the enemy list
     * @param projectiles the projectile list
     */
    void handleEnemyProjectilenCollision(List<Enemy> enemies, List<Projectile> projectiles);
    /**
     * Handle collision between enemies and the player.
     * @param player
     * @param enemies the enemy list
     */
    void handlePlayerEnemyCollisions(Player player, List<Enemy> enemies);
    /**
     * Handle collision between the player and enemy projectiles.
     * @param player
     * @param projectiles the projectile list
     */
    void handlePlayerProjectilenCollision(Player player, List<Projectile> projectiles);
    /**
     * Updates the cooldowns for all entities.
     */
    void update();

}
