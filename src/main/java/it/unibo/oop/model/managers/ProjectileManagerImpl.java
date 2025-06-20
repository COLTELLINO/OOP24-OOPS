package it.unibo.oop.model.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.unibo.oop.model.projectiles.Projectile;
import it.unibo.oop.model.projectiles.StaffProjectile;

/**
 * Class that manages projectiles.
 */
public class ProjectileManagerImpl implements ProjectileManager {
    private final List<Projectile> enemyProjectileList = new ArrayList<>();
    private final List<Projectile> playerProjectileList = new ArrayList<>();

    /**
    * Functional interface for observing projectile events in the manager.
    */
    @FunctionalInterface
    public interface ProjectileManagerObserver {
        /**
        * Called when a projectile explodes.
        * 
        * @param projectile the projectile that exploded
        */
        void onProjectileExploded(Projectile projectile);
    }

    /**
     * Updates all projectiles.
     */
    @Override
    public void update() {
        final Iterator<Projectile> enemyProjectileIterator = enemyProjectileList.iterator();
        while (enemyProjectileIterator.hasNext()) {
            final Projectile p = enemyProjectileIterator.next();
            if (p.isOutOfBounds()) {
                enemyProjectileIterator.remove();
            } else {
                p.update();
            }
        }
        final Iterator<Projectile> playerProjectileIterator = playerProjectileList.iterator();
        while (playerProjectileIterator.hasNext()) {
            final Projectile p = playerProjectileIterator.next();
            if (p.isOutOfBounds()) {
                playerProjectileIterator.remove();
            } else {
                p.update();
            }
        }
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

        if (projectile instanceof StaffProjectile) {
            projectile.setManagerObserver(explodedProjectile -> {
                playerProjectileList.remove(explodedProjectile);
            });
        }
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
    /**
     * @return all projectiles.
     */
    @Override
    public List<Projectile> getAllProjectiles() {
        final List<Projectile> allProjectiles = new ArrayList<>(playerProjectileList);
        allProjectiles.addAll(enemyProjectileList);
        return new ArrayList<>(allProjectiles);
    }
}
