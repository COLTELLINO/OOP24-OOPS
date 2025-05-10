package it.unibo.oop.model.managers;

import java.util.ArrayList;
import java.util.List;

import it.unibo.oop.model.projectiles.Projectile;

/**
 * Class that manages projectiles.
 */
public class ProjectileManagerImpl implements ProjectileManager {
    private final List<Projectile> enemyProjectileList = new ArrayList<>();
    private final List<Projectile> playerProjectileList = new ArrayList<>();
    /**
     * Updates all projectiles.
     */
    @Override
    public void update() {
        enemyProjectileList.forEach(Projectile::update);
        playerProjectileList.forEach(Projectile::update);
    }
    /**
     * Add a projectile to the enemy projectile list.
     * @param projectile
     */
    @Override
    public void addEnemyProjectile(final Projectile projectile) {
        enemyProjectileList.add(projectile);
    }
    /**
     * Add a projectile to the player projectile list.
     * @param projectile
     */
    @Override
    public void addPlayerProjectile(final Projectile projectile) {
        playerProjectileList.add(projectile);
    }
    /**
     * Removes a projectile from the enemy projectile list.
     * @param projectile
     */
    @Override
    public void removeEnemyProjectile(final Projectile projectile) {
        enemyProjectileList.remove(projectile);
    }
    /**
     * Removes a projectile from the player projectile list.
     * @param projectile
     */
    @Override
    public void removePlayerProjectile(final Projectile projectile) {
        playerProjectileList.remove(projectile);
    }
    /**
     * @return all projectiles that come from enemies.
     */
    @Override
    public List<Projectile> getEnemyProjectiles() {
        return new ArrayList<>(enemyProjectileList);
    }
    /**
     * @return all projectiles that come from the player.
     */
    @Override
    public List<Projectile> getPlayerProjectiles() {
        return new ArrayList<>(playerProjectileList);
    }
}
