package it.unibo.oop.model;

import java.util.List;
/**
 * 
 */
public interface EnemyManager {
    /**
     * Updates enemy waves and all enemies.
     */
    void update();
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
