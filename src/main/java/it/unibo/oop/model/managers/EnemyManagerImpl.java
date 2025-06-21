package it.unibo.oop.model.managers;

import java.util.List;
import java.util.stream.Stream;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Enemy;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.factories.EnemyFactory;
import it.unibo.oop.model.factories.EnemyFactoryImpl;
import it.unibo.oop.utils.CountDownTimer;
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
    private static final int WAVE_SIZE = 8;
    private static final int MAX_ENEMIES = 32;
    private static final int SPAWN_DISTANCE = 1000;
    private static final int FIRST_DIFFICULTY_WAVE = 20;
    private static final int SECOND_DIFFICULTY_WAVE = 40;
    private static final int THIRD_DIFFICULTY_WAVE = 60;
    private static final int BOSS_MINUTE_5 = 5;
    private static final int BOSS_MINUTE_6 = 6;
    private static final int BOSS_MINUTE_7 = 7;


    private final List<Enemy> activeEnemies = new ArrayList<>();
    private final List<Enemy> enemiesToSpawn = new ArrayList<>();
    private final List<Enemy> positionedEnemies = new ArrayList<>();
    private final EnemyFactory enemyFactory = new EnemyFactoryImpl();
    private final Timer waveTimer = new TimerImpl(120);
    private final Player player;
    private final CountDownTimer countDownTimer;
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
     * @param player
     * @param countDownTimer
     */
    public EnemyManagerImpl(final Player player, final CountDownTimer countDownTimer) {
        this.player = player;
        this.countDownTimer = countDownTimer;
    }
    /**
     * Updates enemy waves and all enemies.
     */
    @Override
    public void update() {
        waveTimer.update(this::spawnWaveIfPossible);
        positionedEnemies.removeAll(updateActiveEnemies(List.copyOf(positionedEnemies)));
        activeEnemies.removeAll(updateActiveEnemies(List.copyOf(activeEnemies)));
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
     * Handles which and when enemies spawn in the game.
     * @param projectileManager
     * @param experienceManager
     */
    @Override
    public void spawnEnemies(final ProjectileManager projectileManager, final ExperienceManager experienceManager) {
        final int seconds = this.countDownTimer.getSeconds();
        final int minutes = this.countDownTimer.getMinutes();
        final int buffMultiplier = this.countDownTimer.getMinutes() + 1;
        final Enemy baseSlime = this.enemyFactory.createBaseSlime(player.getX(), player.getY(), player);
        final Enemy baseGhost = this.enemyFactory.createBaseGhost(player.getX(), player.getY(), player);
        final Enemy baseZombie = this.enemyFactory.createBaseZombie(player.getX(), player.getY(), player);
        final Enemy baseBat = this.enemyFactory.createBaseBat(player.getX(), player.getY(), player);
        final Enemy baseSkull = this.enemyFactory.createBaseSkull(player.getX(), player.getY(), player);
        final Enemy baseCultist = this.enemyFactory.createBaseCultist(player.getX(), player.getY(), player);
        final Enemy slime = this.enemyFactory.createSlime(player.getX(), player.getY(),
            baseSlime.getMaxHealth() * buffMultiplier, baseSlime.getHealth() * buffMultiplier, 
            baseSlime.getAttack() + buffMultiplier, baseSlime.getSpeed(), baseSlime.getSize(), player);
        final Enemy ghost = this.enemyFactory.createGhost(player.getX(), player.getY(),
            baseGhost.getMaxHealth() * buffMultiplier, baseGhost.getHealth() * buffMultiplier, 
            baseGhost.getAttack() + buffMultiplier - 1, baseGhost.getSpeed(), baseGhost.getSize(), player);
        final Enemy zombie = this.enemyFactory.createZombie(player.getX(), player.getY(),
            baseZombie.getMaxHealth() * buffMultiplier, baseZombie.getHealth() * buffMultiplier, 
            baseZombie.getAttack() + buffMultiplier - 1, baseZombie.getSpeed(), baseZombie.getSize(), player);
        final Enemy bat = this.enemyFactory.createBat(player.getX(), player.getY(),
            baseBat.getMaxHealth() * buffMultiplier, baseBat.getHealth() * buffMultiplier, 
            baseBat.getAttack() + buffMultiplier - 1, baseBat.getSpeed(), baseBat.getSize(), player);
        final Enemy skull = this.enemyFactory.createSkull(player.getX(), player.getY(),
            baseSkull.getMaxHealth() * buffMultiplier, baseSkull.getHealth() * buffMultiplier, 
            baseSkull.getAttack() + buffMultiplier - 1, baseSkull.getSpeed(), baseSkull.getSize(), player);
        final Enemy cultist = this.enemyFactory.createCultist(player.getX(), player.getY(),
            baseCultist.getMaxHealth() * buffMultiplier, baseCultist.getHealth() * buffMultiplier, 
            baseCultist.getAttack() + buffMultiplier - 1, baseCultist.getSpeed(), baseCultist.getSize(), player);
        final Enemy slimeBoss = this.enemyFactory.createBoss(this.enemyFactory.createSlime(
            player.getX() + SPAWN_DISTANCE, player.getY() + SPAWN_DISTANCE, baseSlime.getMaxHealth() * buffMultiplier, 
            baseSlime.getHealth() * buffMultiplier, baseSlime.getAttack() * buffMultiplier,
            baseSlime.getSpeed(), baseSlime.getSize(), player));
        final Enemy ghostBoss = this.enemyFactory.createBoss(this.enemyFactory.createGhost(
            player.getX() + SPAWN_DISTANCE, player.getY() + SPAWN_DISTANCE, baseGhost.getMaxHealth() * buffMultiplier, 
            baseGhost.getHealth() * buffMultiplier, baseGhost.getAttack() * buffMultiplier,
            baseGhost.getSpeed(), baseGhost.getSize(), player));
        final Enemy zombieBoss = this.enemyFactory.createBoss(this.enemyFactory.createZombie(
            player.getX() + SPAWN_DISTANCE, player.getY() + SPAWN_DISTANCE, baseZombie.getMaxHealth() * buffMultiplier, 
            baseZombie.getHealth() * buffMultiplier, baseZombie.getAttack() * buffMultiplier,
            baseZombie.getSpeed(), baseZombie.getSize(), player));
        final Enemy batBoss = this.enemyFactory.createBoss(this.enemyFactory.createBat(
            player.getX() + SPAWN_DISTANCE, player.getY() + SPAWN_DISTANCE, baseBat.getMaxHealth() * buffMultiplier, 
            baseBat.getHealth() * buffMultiplier, baseBat.getAttack() * buffMultiplier,
            baseBat.getSpeed(), baseBat.getSize(), player));
        final Enemy skullBoss = this.enemyFactory.createBoss(this.enemyFactory.createSkull(
            player.getX() + SPAWN_DISTANCE, player.getY() + SPAWN_DISTANCE, baseSkull.getMaxHealth() * buffMultiplier, 
            baseSkull.getHealth() * buffMultiplier, baseSkull.getAttack() * buffMultiplier,
            baseSkull.getSpeed(), baseSkull.getSize(), player));
        final Enemy cultistBoss = this.enemyFactory.createBoss(this.enemyFactory.createCultist(
            player.getX() + SPAWN_DISTANCE, player.getY() + SPAWN_DISTANCE, baseCultist.getMaxHealth() * buffMultiplier, 
            baseCultist.getHealth() * buffMultiplier, baseCultist.getAttack() * buffMultiplier,
            baseCultist.getSpeed(), baseCultist.getSize(), player));
        Stream.of(slime, ghost, zombie, bat, skull, cultist)
            .forEach(e -> 
                e.setOnDeathObserver(() -> {
                    experienceManager.spawnXP(e.getX() + e.getSize() / 2,
                        e.getY() + e.getSize() / 2, this.countDownTimer.getTotalSeconds());
                }));
        skull.setObserver(() -> {
            projectileManager.addEnemyProjectile(skull.getProjectile());
        });
        cultist.setObserver(() -> {
            final Enemy skullSpawn = this.enemyFactory
                .createBaseSkull(cultist.getX(), cultist.getY(), player);
            skullSpawn.setOnDeathObserver(() -> {
                    experienceManager.spawnXP(skullSpawn.getX() + skullSpawn.getSize() / 2,
                        skull.getY() + skullSpawn.getSize() / 2, this.countDownTimer.getTotalSeconds());
                });
            skullSpawn.setObserver(() -> {
                    projectileManager.addEnemyProjectile(skullSpawn.getProjectile());
                });
            this.spawnEnemy(skullSpawn);
        });
        if (seconds > 0 && seconds <= FIRST_DIFFICULTY_WAVE) {
            this.addEnemy(slime);
            this.addEnemy(ghost);
        } else if (seconds > FIRST_DIFFICULTY_WAVE && seconds <= SECOND_DIFFICULTY_WAVE) {
            this.addEnemy(zombie);
            this.addEnemy(bat);
        } else if (seconds > SECOND_DIFFICULTY_WAVE && seconds < THIRD_DIFFICULTY_WAVE) {
            this.addEnemy(skull);
            this.addEnemy(cultist);
        } else if (seconds == 0 && !countDownTimer.isRunning()) {
            if (minutes == 1) {
                this.spawnEnemy(slimeBoss);
            } else if (minutes == 2) {
                this.spawnEnemy(ghostBoss);
            } else if (minutes == 3) {
                this.spawnEnemy(zombieBoss);
            } else if (minutes == 4) {
                this.spawnEnemy(batBoss);
            } else if (minutes == BOSS_MINUTE_5) {
                this.spawnEnemy(skullBoss);
            } else if (minutes == BOSS_MINUTE_6) {
                this.spawnEnemy(cultistBoss);
            } else if (minutes >= BOSS_MINUTE_7) {
                this.spawnEnemy(slimeBoss);
                this.spawnEnemy(ghostBoss);
                this.spawnEnemy(zombieBoss);
                this.spawnEnemy(batBoss);
                this.spawnEnemy(skullBoss);
                this.spawnEnemy(cultistBoss);
            }
        }
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
     * Spawns a wave of enemies if there aren't too many on screen.
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
     * @return the max amount of enemies.
     */
    protected int getMaxEnemies() {
        return MAX_ENEMIES;
    }
}
