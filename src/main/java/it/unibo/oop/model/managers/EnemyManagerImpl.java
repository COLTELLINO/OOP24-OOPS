package it.unibo.oop.model.managers;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Enemy;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.utils.Timer;
import it.unibo.oop.utils.TimerImpl;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * 
 */
@SuppressFBWarnings(value = {"EI2"}, 
justification = "To spawn enemies around the player, its position is needed, " 
        + "and while it's not necessary for the player to be externally mutable for this class, it has to be for others.")
public class EnemyManagerImpl implements EnemyManager {
    private static final int WAVE_SIZE = 6;
    private static final int MAX_ENEMIES = 12;
    private static final int SPAWN_DISTANCE = 200;

    private final List<Enemy> activeEnemies = new ArrayList<>();
    private final List<Enemy> enemiesToSpawn = new ArrayList<>();
    private final List<Enemy> positionedEnemies = new ArrayList<>();
    private final Timer waveTimer = new TimerImpl(120);
    private final Player player;
    /**
     * @param player
     */
    public EnemyManagerImpl(final Player player) {
        this.player = player;
    }
    /**
     * Updates enemy waves and all enemies.
     */
    @Override
    public void update() {
        waveTimer.update(this::spawnWaveIfPossible);
        activeEnemies.removeAll(updateActiveEnemies(List.copyOf(activeEnemies)));
        positionedEnemies.removeAll(updateActiveEnemies(List.copyOf(positionedEnemies)));
    }

    /**
     * Get a list of all spawned enemies.
     * @returns all spawned enemies as a list.
     */
    @Override
    public List<Enemy> getSpawnedEnemies() {
        final List<Enemy> enemies = new ArrayList<>();
        enemies.addAll(activeEnemies);
        enemies.addAll(positionedEnemies);
        return enemies;
    }
    /**
     * Spawns a wave of enemies if there aren't too many on screen.
     * (This method is protected to allow for easy junit testing.)
     */
    protected void spawnWaveIfPossible() {
        if (activeEnemies.size() + WAVE_SIZE > MAX_ENEMIES 
                || enemiesToSpawn.size() < WAVE_SIZE) {
            return;
        }
        for (int i = 0; i < WAVE_SIZE; i++) {
            final Enemy enemy = enemiesToSpawn.remove(0);
            spawnEnemyInWave(enemy, i);
            activeEnemies.add(enemy);
        }
    }
    /**
     * Spawns each enemy in a position based on the size of the wave.
     * @param enemy
     * @param wavePosition
     */
    private void spawnEnemyInWave(final Enemy enemy, final int wavePosition) {
        final double angle = 2 * Math.PI * wavePosition / WAVE_SIZE;
        final double x = player.getX() + player.getSize() / 2
                + SPAWN_DISTANCE * Math.cos(angle) - (double) enemy.getSize() / 2;
        final double y = player.getY() + player.getSize() / 2
                + SPAWN_DISTANCE * Math.sin(angle) - (double) enemy.getSize() / 2;
        enemy.setX((int) x);
        enemy.setY((int) y);
    }
    /**
     * Updates all enemies in a list. 
     * @param enemies 
     * @return the list of enemies to remove.
     */
    private List<Enemy> updateActiveEnemies(final List<Enemy> enemies) {
        final Iterator<Enemy> iterator = enemies.iterator();
        final List<Enemy> toRemove = new ArrayList<>(); 
        while (iterator.hasNext()) {
            final Enemy enemy = iterator.next();
            if (enemy == null || !enemy.isAlive()) {
                toRemove.add(enemy);
            } else {
                enemy.update();
            }
        }
        return toRemove;
    }
    /**
     * Adds an enemy to the spawn list.
     * @param enemy
     */
    @Override
    public void addEnemy(final Enemy enemy) {
        if (enemy != null && enemiesToSpawn.size() + activeEnemies.size() < MAX_ENEMIES) {
            enemiesToSpawn.add(enemy);
        }
    }
    /**
     * Forces an enemy to spawn.
     * Warning: to ensure that the enemy is able to spawn and does not suffer from starvation,
     * the maximum amount of enemies is increased for this method. 
     * @param enemy
     */
    @Override
    public void spawnEnemy(final Enemy enemy) {
        if (enemy != null && positionedEnemies.size() < MAX_ENEMIES * 2) {
            positionedEnemies.add(enemy);
        }
    }
    /**
     * @return the max amount of enemies.
     */
    protected int getMaxEnemies() {
        return MAX_ENEMIES;
    }
}
