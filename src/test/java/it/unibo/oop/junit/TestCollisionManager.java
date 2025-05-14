package it.unibo.oop.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.oop.model.entities.Enemy;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.entities.Slime;
import it.unibo.oop.model.items.Weapon;
import it.unibo.oop.model.managers.CollisionManagerImpl;
import it.unibo.oop.model.projectiles.Arrow;
import it.unibo.oop.model.projectiles.Projectile;
import it.unibo.oop.utils.Direction;

class TestCollisionManager {

    private static final int PLAYER_X = 50;
    private static final int PLAYER_Y = 50;
    private static final int PLAYER_HEALTH = 100;
    private static final int PLAYER_MAX_HEALTH = 100;
    private static final int PLAYER_ATTACK = 10;
    private static final int PLAYER_SPEED = 5;
    private static final int PLAYER_SIZE = 50;

    private static final int ENEMY_X = 70;
    private static final int ENEMY_Y = 50;
    private static final int ENEMY_HEALTH = 100;
    private static final int ENEMY_MAX_HEALTH = 100;
    private static final int ENEMY_ATTACK = 10;
    private static final int ENEMY_SPEED = 5;
    private static final int ENEMY_SIZE = 50;

    private static final int WEAPON_DAMAGE = 20;

    private static final int ARROW_X = 50;
    private static final int ARROW_Y = 50;
    private static final int ARROW_DAMAGE = 10;
    private static final int ARROW_SPEED = 5;
    private static final int ARROW_SIZE = 5;

    private static final int RECT1_X = 50;
    private static final int RECT1_Y = 50;
    private static final int RECT1_WIDTH = 10;
    private static final int RECT1_HEIGHT = 10;

    private static final int RECT2_X = 55;
    private static final int RECT2_Y = 55;
    private static final int RECT2_WIDTH = 10;
    private static final int RECT2_HEIGHT = 10;

    private static final int EXPECTED_HEALTH_AFTER_DAMAGE = 90;
    private static final int EXPECTED_HEALTH_AFTER_WEAPON_DAMAGE = 80;

    private CollisionManagerImpl collisionManager;
    private Player player;
    private Enemy enemy;
    private Weapon weapon;
    private Arrow arrow;

    @BeforeEach
    void setUp() {
        collisionManager = new CollisionManagerImpl();

        player = new Player(PLAYER_X, PLAYER_Y, PLAYER_MAX_HEALTH, PLAYER_HEALTH, PLAYER_ATTACK, PLAYER_SPEED, PLAYER_SIZE);

        enemy = new Slime(ENEMY_X, ENEMY_Y, ENEMY_MAX_HEALTH, ENEMY_HEALTH, ENEMY_ATTACK, ENEMY_SPEED, ENEMY_SIZE, player);

        weapon = new Weapon(player) {
            @Override
            public void handleCollision() {

            }

            @Override
            public int getDamage() {
                return WEAPON_DAMAGE;
            }

            @Override
            public List<Rectangle> getHitBox() {
                return List.of();
            }

            @Override
            public void setShowHitbox(final boolean showHitbox) {

            }

            @Override
            public boolean isShowHitbox() {
                return false;
            }

            @Override
            public void update() {

            }
        };

        arrow = new Arrow(ARROW_X, ARROW_Y, Direction.RIGHT, ARROW_DAMAGE, ARROW_SPEED, ARROW_SIZE) {
            @Override
            public void handleCollision() {

            }
        };
    }

    @Test
    void testIsColliding() {

        final Rectangle rect1 = new Rectangle(RECT1_X, RECT1_Y, RECT1_WIDTH, RECT1_HEIGHT);
        final Rectangle rect2 = new Rectangle(RECT2_X, RECT2_Y, RECT2_WIDTH, RECT2_HEIGHT);
        assertTrue(collisionManager.isColliding(rect1, rect2), "Rectangles should be colliding.");
    }

    @Test
    void testCanTakeDamage() {

        assertTrue(collisionManager.canTakeDamage(enemy), "Enemy should be able to take damage.");
    }

    @Test
    void testRegisterDamage() {

        collisionManager.registerDamage(enemy);
        assertTrue(!collisionManager.canTakeDamage(enemy), "Enemy should not be able to take damage immediately.");
    }

    @Test
    void testHandleWeaponCollision() {

        final Set<Enemy> enemies = new HashSet<>();
        enemies.add(enemy);

        collisionManager.handleWeaponCollision(enemies, weapon);

        assertEquals(EXPECTED_HEALTH_AFTER_WEAPON_DAMAGE, enemy.getHealth(), "Enemy health should be reduced by weapon damage.");
    }

    @Test
    void testHandleEnemyProjectileCollision() {

        arrow.setX(enemy.getX());
        arrow.setY(enemy.getY());

        final List<Projectile> projectiles = new ArrayList<>();
        projectiles.add(arrow);

        final  List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        collisionManager.handleEnemyProjectilenCollision(enemies, projectiles);

        assertEquals(EXPECTED_HEALTH_AFTER_DAMAGE, enemy.getHealth(), "Enemy health should be reduced by projectile damage.");
    }

    @Test
    void testHandlePlayerProjectileCollision() {

        arrow.setX(player.getX());
        arrow.setY(player.getY());

        final List<Projectile> projectiles = new ArrayList<>();
        projectiles.add(arrow);

        collisionManager.handlePlayerProjectilenCollision(player, projectiles);

        assertEquals(EXPECTED_HEALTH_AFTER_DAMAGE, player.getHealth(), "Player health should be reduced by projectile damage.");
    }
}
