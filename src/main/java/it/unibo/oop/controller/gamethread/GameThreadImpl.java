package it.unibo.oop.controller.gamethread;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.oop.model.entities.Enemy;
import it.unibo.oop.model.entities.Entity;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.factories.EnemyFactory;
import it.unibo.oop.model.factories.EnemyFactoryImpl;
import it.unibo.oop.model.handlers.AudioHandler;
import it.unibo.oop.model.handlers.AudioHandlerImpl;
import it.unibo.oop.model.handlers.InputHandler;
import it.unibo.oop.model.items.Weapon;
import it.unibo.oop.model.managers.CollisionManager;
import it.unibo.oop.model.managers.CollisionManagerImpl;
import it.unibo.oop.model.managers.EnemyManager;
import it.unibo.oop.model.managers.EnemyManagerImpl;
import it.unibo.oop.model.managers.ExperienceManager;
import it.unibo.oop.model.managers.ExperienceManagerImpl;
import it.unibo.oop.model.managers.HealthManager;
import it.unibo.oop.model.managers.HealthManagerImpl;
import it.unibo.oop.model.managers.ProjectileManager;
import it.unibo.oop.model.managers.ProjectileManagerImpl;
import it.unibo.oop.model.managers.WeaponManager;
import it.unibo.oop.model.managers.WeaponManagerImpl;
import it.unibo.oop.utils.Camera;
import it.unibo.oop.utils.GameState;
import it.unibo.oop.utils.Percentage;
import it.unibo.oop.utils.Timer;
import it.unibo.oop.utils.TimerImpl;
import it.unibo.oop.view.window.ViewManager;
import it.unibo.oop.view.window.ViewManagerFactory;
import it.unibo.oop.view.window.ViewManagerFactoryImpl;
/**
* Controller of the application.
*/
public class GameThreadImpl implements Runnable, GameThread {
    private static final int PLAYER_X = 200;
    private static final int PLAYER_Y = 200;
    private static final int PLAYER_MAX_HEALTH = 100;
    private static final int PLAYER_HEALTH = 100;
    private static final int PLAYER_ATTACK = 5;
    private static final int PLAYER_SPEED = 5;
    private static final int PLAYER_SIZE = 50;
    private static final int ENEMY_X = 500;
    private static final int ENEMY_Y = 500;

    private final Timer timer = new TimerImpl(1);
    private final Camera camera = new Camera(0, 0);
    private final Player player = new Player(PLAYER_X, PLAYER_Y, PLAYER_MAX_HEALTH, PLAYER_HEALTH,
        PLAYER_ATTACK, PLAYER_SPEED, PLAYER_SIZE);
    private final InputHandler inputHandler = new InputHandler(player);
    private final EnemyManager enemyManager = new EnemyManagerImpl(player);
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private final ProjectileManager projectileManager = new ProjectileManagerImpl();
    private final WeaponManager weaponManager = new WeaponManagerImpl(player, projectileManager);
    private final ExperienceManager experienceManager = new ExperienceManagerImpl(player);
    private final HealthManager healthManager = new HealthManagerImpl(player);
    private final CollisionManager collisionManager = new CollisionManagerImpl();
    private final AudioHandler audioHandler = new AudioHandlerImpl();
    private final ViewManagerFactory drawViewFactory = new ViewManagerFactoryImpl();
    private final ViewManager window;
    private Boolean running = true;
    /**
     * Functional interface to observe enemies and act when a condition is met.
     */
    @FunctionalInterface
    public interface EnemyObserver {
        /**
         * Executes an action in response to an event triggered by an enemy.
         */
        void enemyObserverAction();
    }
    /**
     *
     */
    public GameThreadImpl() {
        this.window = drawViewFactory.createViewManager(GameState.TITLESTATE, player, enemyManager, 
            weaponManager, experienceManager, healthManager, projectileManager, camera);
        this.window.addKeyListener(inputHandler);
        this.window.setFocusable(true);
        this.audioHandler.playSoundEffect(1, Percentage.TEN_PERCENT);
        this.startThread();
    }
    /**
     * Starts the gameThread.
     */
    private void startThread() {
        final Thread thread = new Thread(this);
        thread.start();
    }
    /**
     * Stops the gameThread.
     */
    @Override
    public void stopThread() {
        this.running = false;
    }
    /**
     *  Thread.
     */
    @Override
    public void run() {
        while (running) {
            this.timer.update(this::update);
        }
    }
    /**
     *  Updates players, items, enemies.
     */
    @Override
    public void update() {
        if (this.window.getCurrentGameState() == GameState.PLAYSTATE) {
            getAllEntities().forEach((e) -> e.showHitbox(inputHandler.isDebugMode()));
            projectileManager.getAllProjectiles().forEach((p) -> p.setShowHitbox(inputHandler.isDebugMode()));
            weaponManager.getWeapons().forEach((w) -> w.setShowHitbox(inputHandler.isDebugMode()));
            this.spawnEnemies();
            collisionManager.update();
            this.checkCollisions();
            weaponManager.update();
            experienceManager.update();
            player.update();
            enemyManager.update();
            healthManager.update();
            projectileManager.update();
            camera.update(player, window.getGameScreenWidth(), window.getGameScreenHeight());
        }
        this.window.repaint();
    }
    /**
     * Checks for collisions between the player and enemies.
     */
    private void checkCollisions() {
        collisionManager.handleEnemyProjectilenCollision(enemyManager.getSpawnedEnemies(),
            projectileManager.getPlayerProjectiles());
        collisionManager.handlePlayerProjectilenCollision(player, projectileManager.getEnemyProjectiles());
        collisionManager.handlePlayerEnemyCollisions(player, enemyManager.getSpawnedEnemies());
        for (final Weapon weapon : weaponManager.getWeapons()) {
            for (final Rectangle rectangle : weapon.getHitBox()) {
                final Set<Enemy> enemies = new HashSet<>();
                for (final Enemy enemy : enemyManager.getSpawnedEnemies()) {
                    if (collisionManager.isColliding(rectangle, enemy.getHitbox())) {
                        enemies.add(enemy);
                    }
                }
                collisionManager.handleWeaponCollision(enemies, weapon);
            }
        }
    }
    /**
     * Handles the spawning of enemies.
     */
    private void spawnEnemies() {
        final Enemy baseSkull = this.enemyFactory.createBaseSkull(ENEMY_X, ENEMY_Y, player);
        final Enemy baseZombie = this.enemyFactory.createBaseZombie(ENEMY_X, ENEMY_Y, player);
        baseSkull.setOnDeathObserver(() -> {
            this.experienceManager.spawnXP(baseSkull.getX() + baseSkull.getSize() / 2,
                baseSkull.getY() + baseSkull.getSize() / 2, 10);
        });
        baseSkull.setObserver(() -> {
           projectileManager.addEnemyProjectile(baseSkull.getProjectile());
        });
        this.enemyManager.addEnemy(baseSkull);
        this.enemyManager.addEnemy(baseZombie);
    }
    /**
     * @return all the entities.
     */
    private List<Entity> getAllEntities() {
        final List<Entity> entities = new ArrayList<>();
        entities.addAll(enemyManager.getSpawnedEnemies());
        entities.add(player);
        return entities;
    }
}
