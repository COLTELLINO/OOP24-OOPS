package it.unibo.oops.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import it.unibo.oops.model.AudioHandler;
import it.unibo.oops.model.AudioHandlerImpl;
import it.unibo.oops.model.BossEnemy;
import it.unibo.oops.model.EnemyFactory;
import it.unibo.oops.model.EnemyFactoryImpl;
import it.unibo.oops.model.EnemyManager;
import it.unibo.oops.model.EnemyManagerImpl;
import it.unibo.oops.model.ExperienceManager;
import it.unibo.oops.model.Percentage;
import it.unibo.oops.model.Player;
import it.unibo.oops.model.Timer;
import it.unibo.oops.model.TimerImpl;
import it.unibo.oops.model.WeaponManager;
import it.unibo.oops.view.DrawViewImpl;
/**
* 
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
    private final EnemyManager enemyManager = new EnemyManagerImpl(player);
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private final WeaponManager weaponManager = new WeaponManager(player);
    private final ExperienceManager experienceManager = new ExperienceManager(player);
    private final AudioHandler audioHandler = new AudioHandlerImpl();

    private DrawViewImpl window;
    private Boolean running = true;
    /**
     *
     */
    public GameThreadImpl() {
        final InputHandler inputHandler = new InputHandler(player);
        try {
            SwingUtilities.invokeAndWait(() -> {
                window = new DrawViewImpl(GameState.TITLESTATE, player, enemyManager, weaponManager, experienceManager);
                window.addKeyListener(inputHandler);
                window.setFocusable(true);
            });
        } catch (InvocationTargetException | InterruptedException e) {
            Logger.getLogger(this.getClass().getName())
                .log(Level.SEVERE, e.getClass().getSimpleName() + " occurred: ", e);
        }
        audioHandler.playSoundEffect(1, Percentage.TEN_PERCENT);
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
            if (this.window.getCurrentGameState() == GameState.PLAYSTATE) {
                this.enemyManager.
                addEnemy(this.enemyFactory.createBaseSlime(ENEMY_X, ENEMY_Y, player));
                spawnTestTimer.update(() -> {
                    this.enemyManager.spawnEnemy(new BossEnemy(this.enemyFactory.createBaseSlime(ENEMY_X, ENEMY_Y, player)));
                });
            }
            timer.update(this::update);
        }
    }
    /**
     *  Updates players, items, enemies.
     */
    @Override
    public void update() {
        if (this.window.getCurrentGameState() == GameState.PLAYSTATE) {
            weaponManager.update();
            experienceManager.update();
            player.update();
            enemyManager.update();
        }
        SwingUtilities.invokeLater(() -> {
            this.window.repaint();
        });
    }
}
