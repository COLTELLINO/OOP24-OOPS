package it.unibo.oop.controller.controllers;

import java.util.List;

import it.unibo.oop.model.entities.Enemy;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.events.DamageEvent;
import it.unibo.oop.model.items.ExperienceOrb;
import it.unibo.oop.model.items.Weapon;
import it.unibo.oop.model.managers.*;
import it.unibo.oop.model.projectiles.Projectile;

public class GameController {

    private final Player player;
    private final EnemyManager enemyManager;
    private final ProjectileManager projectileManager;
    private final WeaponManager weaponManager;
    private final ExperienceManager experienceManager;
    private final CollisionManager collisionManager;
    /**
     * Constructor that initializes the GameController with the necessary managers and player.
     * @param player
     * @param enemyManager
     * @param projectileManager
     * @param weaponManager
     * @param experienceManager
     * @param collisionManager
     */
    public GameController(Player player, EnemyManager enemyManager, ProjectileManager projectileManager, 
            WeaponManager weaponManager, ExperienceManager experienceManager, CollisionManager collisionManager) {
        this.player = player;
        this.enemyManager = enemyManager;
        this.projectileManager = projectileManager;
        this.weaponManager = weaponManager;
        this.experienceManager = experienceManager;
        this.collisionManager = collisionManager;
    }
    /**
     * @return the player controlled by this controller
     */
    public Player getPlayer() {
        return player;
    }
    /**
     * @return the player controlled by this controller
     */
    public int getPlayerLevel() {
        return player.getLevel();
    }
    /**
     * @return the player's current experience points
     */
    public int getCurrentXP() {
        return experienceManager.getCurrentXP();
    }
    /**
     * @return the player's experience points needed to reach the next level
     */
    public int getXPToNextLevel() {
        return experienceManager.getXPToNextLevel();
    }
    /**
     * @return the player's current health
     */
    public int getPlayerHealth() {
        return player.getHealth();
    }
    /**
     * @return the player's maximum health
     */
    public int getPlayerMaxHealth() {
        return player.getMaxHealth();
    }
    /**
     * @return an iterable of all spawned enemies
     */
    public List<Enemy> getEnemies() {
        return enemyManager.getSpawnedEnemies();
    }
    /**
     * @return an iterable of all projectiles
     */
    public List<Projectile> getProjectiles() {
        return projectileManager.getAllProjectiles();
    }
    /**
     * @return an iterable of all weapons
     */
    public List<Weapon> getWeapons() {
        return weaponManager.getWeapons();
    }
    /**
     * @return an iterable of all damage events
     */
    public List<DamageEvent> getDamageEvents() {
        return collisionManager.getDamageEvents();
    }
    /**
     * @return an iterable of all experience orbs
     */
    public List<ExperienceOrb> getExperienceOrbs() {
        return experienceManager.getOrbs();
    }
}

