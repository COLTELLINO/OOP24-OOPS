package it.unibo.oop.controller;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import it.unibo.oop.model.AudioHandler;
import it.unibo.oop.model.AudioHandlerImpl;
import it.unibo.oop.model.CollisionManager;
import it.unibo.oop.model.CollisionManagerImpl;
import it.unibo.oop.model.Enemy;
import it.unibo.oop.model.EnemyFactory;
import it.unibo.oop.model.EnemyFactoryImpl;
import it.unibo.oop.model.EnemyManager;
import it.unibo.oop.model.EnemyManagerImpl;
import it.unibo.oop.model.Entity;
import it.unibo.oop.model.ExperienceManager;
import it.unibo.oop.model.ExperienceManagerImpl;
import it.unibo.oop.model.InputHandler;
import it.unibo.oop.model.Percentage;
import it.unibo.oop.model.Player;
import it.unibo.oop.model.Timer;
import it.unibo.oop.model.TimerImpl;
import it.unibo.oop.model.Weapon;
import it.unibo.oop.model.WeaponManager;
import it.unibo.oop.model.WeaponManagerImpl;
import it.unibo.oop.view.DrawView;
import it.unibo.oop.view.DrawViewFactory;
import it.unibo.oop.view.DrawViewFactoryImpl;
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
    private final Timer spawnTestTimer = new TimerImpl(300);
    private final Player player = new Player(PLAYER_X, PLAYER_Y, PLAYER_MAX_HEALTH, PLAYER_HEALTH,
        PLAYER_ATTACK, PLAYER_SPEED, PLAYER_SIZE);
    private final InputHandler inputHandler = new InputHandler(player);
    private final EnemyManager enemyManager = new EnemyManagerImpl(player);
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private final WeaponManager weaponManager = new WeaponManagerImpl(player);
    private final ExperienceManager experienceManager = new ExperienceManagerImpl(player);
    private final AudioHandler audioHandler = new AudioHandlerImpl();
    private final DrawViewFactory drawViewFactory = new DrawViewFactoryImpl();
    private final CollisionManager collisionManager = new CollisionManagerImpl();
    private final DrawView window;
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
        this.window = drawViewFactory.createDrawView(GameState.TITLESTATE, player, enemyManager, 
            weaponManager, experienceManager);
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
            getAllEntities().forEach((e) -> e.setShowHitbox(inputHandler.isDebugMode()));
            this.spawnEnemies();
            weaponManager.update();
            experienceManager.update();
            player.update();
            enemyManager.update();
        }
        this.window.repaint();

        checkCollisions();
    }
    /**
     * Checks for collisions between the player and enemies.
     */
    private void checkCollisions() {
        for (final Weapon weapon : weaponManager.getWeapons().keySet()) {
            final List<Enemy> enemies = new ArrayList<>();
            for (final Enemy enemy : enemyManager.getSpawnedEnemies()) {
                for (final Rectangle rectangle : weapon.getHitBox()) {
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
        final Enemy slimeBoss = this.enemyFactory.createBoss(this.enemyFactory.createBaseSlime(ENEMY_X, ENEMY_Y, player));
        final Enemy baseSlime = this.enemyFactory.createBaseSlime(ENEMY_X, ENEMY_Y, player);
        slimeBoss.setDeathObserver(() -> {
            this.enemyManager.spawnEnemy(this.enemyFactory.
                createBaseSlime(slimeBoss.getX() + slimeBoss.getSize() / 2, slimeBoss.getY(), player));
            this.enemyManager.spawnEnemy(this.enemyFactory.
                createBaseSlime(slimeBoss.getX(), slimeBoss.getY(), player));
            this.experienceManager.spawnXP(slimeBoss.getX() + slimeBoss.getSize() / 2,
                slimeBoss.getY() + slimeBoss.getSize() / 2, 100);
        });
        baseSlime.setDeathObserver(() -> {
            this.experienceManager.spawnXP(baseSlime.getX() + baseSlime.getSize() / 2,
                baseSlime.getY() + baseSlime.getSize() / 2, 10);
        });
        this.enemyManager.addEnemy(baseSlime);
        this.spawnTestTimer.update(() -> {
            this.enemyManager.spawnEnemy(slimeBoss);
        });
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
