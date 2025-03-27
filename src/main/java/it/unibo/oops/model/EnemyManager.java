package it.unibo.oops.model;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * 
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "To spawn enemies around the player, its position is needed, " 
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class EnemyManager {
    private static final int WAVE_SIZE = 30;
    private static final int MAX_ENEMIES = 30;
    private static final int SPAWN_DISTANCE = 200;

    private final List<Enemy> enemiesToSpawn = new ArrayList<>();
    private final List<Enemy> activeEnemies = new ArrayList<>();
    private final Timer waveTimer = new TimerImpl(120);
    private final Player player;
    /**
     * @param player
     */
    public EnemyManager(final Player player) {
        this.player = player;
    }
    /**
     * Updates enemy waves and all enemies.
     */
    public void update() {
        waveTimer.update(this::spawnWaveIfPossible);
        updateActiveEnemies();
    }
    /**
     * Spawns a wave of enemis if there aren't too many on screen.
     */
    private void spawnWaveIfPossible() {
        if (activeEnemies.size() + WAVE_SIZE > MAX_ENEMIES 
                || enemiesToSpawn.size() < WAVE_SIZE) {
            return;
        }
        for (int i = 0; i < WAVE_SIZE; i++) {
            final Enemy enemy = enemiesToSpawn.remove(0);
            spawnEnemy(enemy, i);
            activeEnemies.add(enemy);
        }
    }
    /**
     * Spawns each enemy in a position based on the size of the wave.
     * @param enemy
     * @param wavePosition
     */
    private void spawnEnemy(final Enemy enemy, final int wavePosition) {
        final double angle = 2 * Math.PI * wavePosition / WAVE_SIZE;
        final double x = player.getX() + player.getSize()
                + SPAWN_DISTANCE * Math.cos(angle) - enemy.getSize() / 2;
        final double y = player.getY() + player.getSize()
                + SPAWN_DISTANCE * Math.sin(angle) - enemy.getSize() / 2;
        enemy.setX((int) x);
        enemy.setY((int) y);
        enemy.setSpawned(true);
        enemy.setSpeed(0);
    }
    /**
     * Updates all active enemies. 
     */
    private void updateActiveEnemies() {
        final Iterator<Enemy> iterator = activeEnemies.iterator();
        while (iterator.hasNext()) {
            final Enemy enemy = iterator.next();
            if (enemy == null || !enemy.isAlive()) {
                iterator.remove();
            } else {
                enemy.update();
            }
        }
    }
    /**
     * Adds an enemy to the spawn list.
     * @param enemy
     */
    public void addEnemy(final Enemy enemy) {
        if (enemy != null && enemiesToSpawn.size() + activeEnemies.size() < MAX_ENEMIES) {
            enemiesToSpawn.add(enemy);
        }
    }
    /**
     * Draws all active enemies.
     * @param g2
     */
    public void draw(final Graphics2D g2) {
        for (final Enemy enemy : activeEnemies) {
            if (enemy != null && enemy.hasSpawned()) {
                enemy.draw(g2);
            }
        }
    }
}
