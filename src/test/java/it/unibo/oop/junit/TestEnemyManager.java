package it.unibo.oop.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.model.entities.Enemy;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.entities.Slime;
/**
 * Tests the enemy manager class.
 */
class TestEnemyManager {
    private TestableEnemyManager enemyManager;
    private Player fakePlayer;

    /**
     * Preparation for tests.
     */
    @BeforeEach
    void setUp() {
        fakePlayer = new Player(0, 0, 0, 0, 0, 0, 0);
        enemyManager = new TestableEnemyManager(fakePlayer);
    }
    /**
     * Test if adding an enemy changes the list of spawned enemies.
     */
    @Test
    void testAddEnemy() {
        final Enemy enemy = new Slime(0, 0, 0, 0, 0, 0, 0, fakePlayer);
        enemyManager.addEnemy(enemy);
        assertTrue(enemyManager.getSpawnedEnemies().isEmpty());
    }
    /**
     * Test if spawning enemies correctly puts them in the list of spawned enemies.
     */
    @Test
    void testSpawnEnemy() {
        final Enemy enemy = new Slime(0, 0, 0, 0, 0, 0, 0, fakePlayer);
        assertEquals(0, enemyManager.getSpawnedEnemies().size());
        enemyManager.spawnEnemy(enemy);
        assertEquals(1, enemyManager.getSpawnedEnemies().size());
    }
    @Test
    void testWaveSpawningLimits() {
        final int enemiesAdded = 100;
        for (int i = 0; i < enemiesAdded; i++) {
            enemyManager.addEnemy(new Slime(0, 0, 0, 1, 0, 0, 0, fakePlayer));
            enemyManager.spawnWaveIfPossible();
        }
        assertEquals(enemyManager.getMaxEnemies(), enemyManager.getSpawnedEnemies().size(), "Should not exceed max enemies");
    }
}
