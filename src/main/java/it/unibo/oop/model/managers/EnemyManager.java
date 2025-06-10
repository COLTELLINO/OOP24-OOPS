package it.unibo.oop.model.managers;

import java.util.List;

import it.unibo.oop.model.entities.Enemy;
/**
 * 
 */
public interface EnemyManager {
    /**
     * Updates enemy waves and all enemies.
     */
    void update();
    /**
     * Handles which and when enemies spawn in the game.
     * @param projectileManager
     * @param experienceManager
     */
    void spawnEnemies(ProjectileManager projectileManager, ExperienceManager experienceManager);
    /**
     * Adds an enemy to the spawn list.
     * @param enemy
     */
    void addEnemy(Enemy enemy);
    /**
     * Spawns an enemy.
     * @param enemy
     */
    void spawnEnemy(Enemy enemy);
    /**
     * Get a list of all spawned enemies.
     * @return all spawned enemies as a list.
     */
    List<Enemy> getSpawnedEnemies();
}
