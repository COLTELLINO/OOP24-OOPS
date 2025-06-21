package it.unibo.oop.model.factories;

import it.unibo.oop.model.entities.BossEnemy;
import it.unibo.oop.model.entities.Cultist;
import it.unibo.oop.model.entities.Enemy;
import it.unibo.oop.model.entities.Ghost;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.entities.Skull;
import it.unibo.oop.model.entities.Slime;
import it.unibo.oop.model.entities.Zombie;
import it.unibo.oop.model.entities.Bat;
/**
 * Class to create enemies.
 */
public class EnemyFactoryImpl implements EnemyFactory {
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param speed
     * @param attack
     * @param size
     * @param player
     * @return a new instance of a Slime enemy.
     */
    @Override
    public Enemy createSlime(final int x, final int y, final int maxHealth, final int health, final int attack, 
            final int speed, final int size, final Player player) {
        return new Slime(x, y, maxHealth, health, attack, speed, size, player);
    }
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param speed
     * @param attack
     * @param size
     * @param player
     * @return a new instance of a Zombie enemy.
     */
    @Override
    public Enemy createZombie(final int x, final int y, final int maxHealth, final int health, final int attack, 
            final int speed, final int size, final Player player) {
        return new Zombie(x, y, maxHealth, health, attack, speed, size, player);
    }
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param speed
     * @param attack
     * @param size
     * @param player
     * @return a new instance of a Ghost enemy.
     */
    @Override
    public Enemy createGhost(final int x, final int y, final int maxHealth, final int health, final int attack, 
            final int speed, final int size, final Player player) {
        return new Ghost(x, y, maxHealth, health, attack, speed, size, player);
    }
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param attack
     * @param speed
     * @param size
     * @param player
     * @return a new instance of a Bat enemy.
     */
    @Override
    public Enemy createBat(final int x, final int y, final int maxHealth, final int health, final int attack, 
            final int speed, final int size, final Player player) {
        return new Bat(x, y, maxHealth, health, attack, speed, size, player);
    }
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param speed
     * @param attack
     * @param size
     * @param player
     * @return a new instance of a Skull enemy.
     */
    @Override
    public Enemy createSkull(final int x, final int y, final int maxHealth, final int health, final int attack, 
            final int speed, final int size, final Player player) {
        return new Skull(x, y, maxHealth, health, attack, speed, size, player);
    }
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param speed
     * @param attack
     * @param size
     * @param player
     * @return a new instance of a Cultist enemy.
     */
    @Override
    public Enemy createCultist(final int x, final int y, final int maxHealth, final int health, final int attack,
            final int speed, final int size, final Player player) {
        return new Cultist(x, y, maxHealth, health, attack, speed, size, player);
    }
    /**
     * @param x
     * @param y
     * @param player
     * @return a new instance of a Slime enemy with base stats.
     */
    @Override
    public Enemy createBaseSlime(final int x, final int y, final Player player) {
        return Slime.createDefault(x, y, player);
    }
    /**
     * @param x
     * @param y
     * @param player
     * @return a new instance of a Zombie enemy with base stats.
     */
    @Override
    public Enemy createBaseZombie(final int x, final int y, final Player player) {
        return Zombie.createDefault(x, y, player);
    }
    /**
     * @param x
     * @param y
     * @param player
     * @return a new instance of a Ghost enemy with base stats.
     */
    @Override
    public Enemy createBaseGhost(final int x, final int y, final Player player) {
        return Ghost.createDefault(x, y, player);
    }
    /**
     * @param x
     * @param y
     * @param player
     * @return a new instance of a Bat enemy with base stats.
     */
    @Override
    public Enemy createBaseBat(final int x, final int y, final Player player) {
        return Bat.createDefault(x, y, player);
    }
    /**
     * @param x
     * @param y
     * @param player
     * @return a new instance of a Skull enemy with base stats.
     */
    @Override
    public Enemy createBaseSkull(final int x, final int y, final Player player) {
        return Skull.createDefault(x, y, player);
    }
    /**
     * @param x
     * @param y
     * @param player
     * @return a new instance of a Cultist enemy with base stats.
     */
    @Override
    public Enemy createBaseCultist(final int x, final int y, final Player player) {
        return Cultist.createDefault(x, y, player);
    }
    /**
     * @param enemy
     * @return a new instance of a Boss version of an enemy.
     */
    @Override
    public Enemy createBoss(final Enemy enemy) {
        return new BossEnemy(enemy);
    }
}
