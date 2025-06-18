package it.unibo.oop.model.factories;

import it.unibo.oop.model.entities.Enemy;
import it.unibo.oop.model.entities.Player;

/**
 * Interface with the intent of creating enemy-type objects.
 */
public interface EnemyFactory {
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param attack
     * @param speed
     * @param size
     * @param player
     * @return a new instance of a Slime enemy.
     */
    Enemy createSlime(int x, int y, int maxHealth, int health, int attack, int speed, int size, Player player);
    /**
     * @param x
     * @param y
     * @param maxHealth
     * @param health
     * @param attack
     * @param speed
     * @param size
     * @param player
     * @return a new instance of a Zombie enemy.
     */
    Enemy createZombie(int x, int y, int maxHealth, int health, int attack, int speed, int size, Player player);
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
    Enemy createGhost(int x, int y, int maxHealth, int health, int attack, int speed, int size, Player player);
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
    Enemy createBat(int x, int y, int maxHealth, int health, int attack, int speed, int size, Player player);
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
    Enemy createSkull(int x, int y, int maxHealth, int health, int attack, int speed, int size, Player player);
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
    Enemy createCultist(int x, int y, int maxHealth, int health, int attack, int speed, int size, Player player);
    /**
     * @param x
     * @param y
     * @param player
     * @return a new instance of a Slime enemy with base stats.
     */
    Enemy createBaseSlime(int x, int y, Player player);
    /**
     * @param x
     * @param y
     * @param player
     * @return a new instance of a Zombie enemy with base stats.
     */
    Enemy createBaseZombie(int x, int y, Player player);
    /**
     * @param x
     * @param y
     * @param player
     * @return a new instance of a Ghost enemy with base stats.
     */
    Enemy createBaseGhost(int x, int y, Player player);
    /**
     * @param x
     * @param y
     * @param player
     * @return a new instance of a Bat enemy with base stats.
     */
    Enemy createBaseBat(int x, int y, Player player);
    /**
     * @param x
     * @param y
     * @param player
     * @return a new instance of a Skull enemy with base stats.
     */
    Enemy createBaseSkull(int x, int y, Player player);
    /**
     * @param x
     * @param y
     * @param player
     * @return a new instance of a Cultist enemy with base stats.
     */
    Enemy createBaseCultist(int x, int y, Player player);
    /**
     * @param enemy
     * @return a new instance of a Boss version of an enemy.
     */
    Enemy createBoss(Enemy enemy);
}
