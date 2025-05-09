package it.unibo.oop.model;

import java.util.List;
/**
 * Interface for managing projectiles.
 */
public interface ProjectileManager {
    /**
     * Updates all projectiles.
     */
    public void update();
    /**
     * Add a projectile to the enemy projectile list.
     * @param projectile
     */
    void addEnemyProjectile(Projectile projectile);
    /**
     * Add a projectile to the player projectile list.
     * @param projectile
     */
    void addPlayerProjectile(Projectile projectile);
    /**
     * Removes a projectile from the enemy projectile list.
     * @param projectile
     */
    void removeEnemyProjectile(Projectile projectile);
    /**
     * Removes a projectile from the player projectile list.
     * @param projectile
     */
    void removePlayerProjectile(Projectile projectile);
    /**
     * @return all projectiles that come from enemies.
     */
    List<Projectile> getEnemyProjectiles();
    /**
     * @return all projectiles that come from the player.
     */
    List<Projectile> getPlayerProjectiles();
}
