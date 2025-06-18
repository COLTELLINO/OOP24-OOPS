package it.unibo.oop.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.model.projectiles.Projectile;
import it.unibo.oop.model.projectiles.StaffProjectile;
import it.unibo.oop.model.managers.ProjectileManagerImpl;
import it.unibo.oop.utils.Direction;

class TestProjectileManager {
    private static final int PLAYER_PROJECTILE_X = 10;
    private static final int PLAYER_PROJECTILE_Y = 10;
    private static final int PLAYER_PROJECTILE_DAMAGE = 5;
    private static final int PLAYER_PROJECTILE_SPEED = 10;
    private static final int PLAYER_PROJECTILE_SIZE = 5;
    private static final int PLAYER_PROJECTILE_ENTITY_SIZE = 10;

    private static final int ENEMY_PROJECTILE_X = 20;
    private static final int ENEMY_PROJECTILE_Y = 20;
    private static final int ENEMY_PROJECTILE_DAMAGE = 5;
    private static final int ENEMY_PROJECTILE_SPEED = 10;
    private static final int ENEMY_PROJECTILE_SIZE = 5;

    private static final int EXPECTED_PLAYER_PROJECTILE_X_AFTER_UPDATE = 
            PLAYER_PROJECTILE_X + PLAYER_PROJECTILE_ENTITY_SIZE + PLAYER_PROJECTILE_SPEED;

    private ProjectileManagerImpl projectileManager;

    @BeforeEach
    void setUp() {
        projectileManager = new ProjectileManagerImpl();
    }

    @Test
    void testAddAndRetrievePlayerProjectiles() {

        final Projectile playerProjectile = new StaffProjectile(
            PLAYER_PROJECTILE_X, PLAYER_PROJECTILE_Y, Direction.RIGHT,
            PLAYER_PROJECTILE_DAMAGE, PLAYER_PROJECTILE_SPEED, PLAYER_PROJECTILE_SIZE, PLAYER_PROJECTILE_SIZE
        );

        projectileManager.addPlayerProjectile(playerProjectile);

        final List<Projectile> playerProjectiles = projectileManager.getPlayerProjectiles();
        assertEquals(1, playerProjectiles.size(), "There should be one player projectile.");
        assertEquals(playerProjectile, playerProjectiles.get(0), "The projectile should match the one added.");
    }

    @Test
    void testAddAndRetrieveEnemyProjectiles() {

        final Projectile enemyProjectile = new StaffProjectile(
            ENEMY_PROJECTILE_X, ENEMY_PROJECTILE_Y, Direction.LEFT,
            ENEMY_PROJECTILE_DAMAGE, ENEMY_PROJECTILE_SPEED, ENEMY_PROJECTILE_SIZE, ENEMY_PROJECTILE_SIZE
        );

        projectileManager.addEnemyProjectile(enemyProjectile);

        final List<Projectile> enemyProjectiles = projectileManager.getEnemyProjectiles();
        assertEquals(1, enemyProjectiles.size(), "There should be one enemy projectile.");
        assertEquals(enemyProjectile, enemyProjectiles.get(0), "The projectile should match the one added.");
    }

    @Test
    void testUpdateProjectiles() {

        final Projectile playerProjectile = new StaffProjectile(
            PLAYER_PROJECTILE_X, PLAYER_PROJECTILE_Y, Direction.RIGHT,
            PLAYER_PROJECTILE_DAMAGE, PLAYER_PROJECTILE_SPEED, PLAYER_PROJECTILE_SIZE, PLAYER_PROJECTILE_ENTITY_SIZE
        );

        projectileManager.addPlayerProjectile(playerProjectile);

        projectileManager.update();

        final List<Projectile> playerProjectiles = projectileManager.getPlayerProjectiles();
        assertEquals(1, playerProjectiles.size(), "There should still be one player projectile.");
        assertEquals(EXPECTED_PLAYER_PROJECTILE_X_AFTER_UPDATE, 
        playerProjectiles.get(0).getX(), "The projectile should have moved to the right.");
    }

    @Test
    void testRemovePlayerProjectile() {

        final Projectile playerProjectile = new StaffProjectile(
            PLAYER_PROJECTILE_X, PLAYER_PROJECTILE_Y, Direction.RIGHT,
            PLAYER_PROJECTILE_DAMAGE, PLAYER_PROJECTILE_SPEED, PLAYER_PROJECTILE_SIZE, PLAYER_PROJECTILE_SIZE
        );

        projectileManager.addPlayerProjectile(playerProjectile);
        projectileManager.removePlayerProjectile(playerProjectile);

        final List<Projectile> playerProjectiles = projectileManager.getPlayerProjectiles();
        assertTrue(playerProjectiles.isEmpty(), "The player projectile list should be empty.");
    }

    @Test
    void testRemoveEnemyProjectile() {

        final Projectile enemyProjectile = new StaffProjectile(
            ENEMY_PROJECTILE_X, ENEMY_PROJECTILE_Y, Direction.LEFT,
            ENEMY_PROJECTILE_DAMAGE, ENEMY_PROJECTILE_SPEED, ENEMY_PROJECTILE_SIZE, ENEMY_PROJECTILE_SIZE
        );

        projectileManager.addEnemyProjectile(enemyProjectile);
        projectileManager.removeEnemyProjectile(enemyProjectile);

        final List<Projectile> enemyProjectiles = projectileManager.getEnemyProjectiles();
        assertTrue(enemyProjectiles.isEmpty(), "The enemy projectile list should be empty.");
    }

    @Test
    void testGetAllProjectiles() {

        final Projectile playerProjectile = new StaffProjectile(
            PLAYER_PROJECTILE_X, PLAYER_PROJECTILE_Y, Direction.RIGHT,
            PLAYER_PROJECTILE_DAMAGE, PLAYER_PROJECTILE_SPEED, PLAYER_PROJECTILE_SIZE, PLAYER_PROJECTILE_SIZE
        );
        final Projectile enemyProjectile = new StaffProjectile(
            ENEMY_PROJECTILE_X, ENEMY_PROJECTILE_Y, Direction.LEFT,
            ENEMY_PROJECTILE_DAMAGE, ENEMY_PROJECTILE_SPEED, ENEMY_PROJECTILE_SIZE, PLAYER_PROJECTILE_SIZE
        );

        projectileManager.addPlayerProjectile(playerProjectile);
        projectileManager.addEnemyProjectile(enemyProjectile);

        final List<Projectile> allProjectiles = projectileManager.getAllProjectiles();
        assertEquals(2, allProjectiles.size(), "There should be two projectiles in total.");
        assertTrue(allProjectiles.contains(playerProjectile), "The list should contain the player projectile.");
        assertTrue(allProjectiles.contains(enemyProjectile), "The list should contain the enemy projectile.");
    }
}
