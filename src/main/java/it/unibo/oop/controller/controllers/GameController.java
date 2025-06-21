package it.unibo.oop.controller.controllers;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Enemy;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.events.DamageEvent;
import it.unibo.oop.model.items.ExperienceOrb;
import it.unibo.oop.model.items.Weapon;
import it.unibo.oop.model.managers.CollisionManager;
import it.unibo.oop.model.managers.EnemyManager;
import it.unibo.oop.model.managers.ExperienceManager;
import it.unibo.oop.model.managers.ProjectileManager;
import it.unibo.oop.model.managers.WeaponManager;
import it.unibo.oop.model.projectiles.Projectile;
import it.unibo.oop.utils.CountDownTimer;
/**
 * Controller that provides methods to access game data and manage the game state. 
 * It acts as a bridge between the model and the view.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
    justification = "Storing references to mutable managers to coordinate game logic is necessary "
      + "design requires these objects to be externally mutable.")
public class GameController {
    private static final int SECONDS_IN_MINUTE = 60;
    private final Player player;
    private final EnemyManager enemyManager;
    private final ProjectileManager projectileManager;
    private final WeaponManager weaponManager;
    private final ExperienceManager experienceManager;
    private final CollisionManager collisionManager;
    private final CountDownTimer countDownTimer;
    /**
     * Constructor that initializes the GameController with the necessary managers and player.
     * @param player
     * @param enemyManager
     * @param projectileManager
     * @param weaponManager
     * @param experienceManager
     * @param collisionManager
     * @param countDownTimer
     */
    public GameController(final Player player, final EnemyManager enemyManager, 
            final ProjectileManager projectileManager, final WeaponManager weaponManager,
            final ExperienceManager experienceManager, final CollisionManager collisionManager,
            final CountDownTimer countDownTimer) {
        this.player = player;
        this.enemyManager = enemyManager;
        this.projectileManager = projectileManager;
        this.weaponManager = weaponManager;
        this.experienceManager = experienceManager;
        this.collisionManager = collisionManager;
        this.countDownTimer = countDownTimer;
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
    /**
     * @return the timer value as a string
     */
    public String getTimerString() {
        final int totalSeconds = this.countDownTimer.getTotalSeconds();
        final int minutes = totalSeconds / SECONDS_IN_MINUTE;
        final int seconds = totalSeconds % SECONDS_IN_MINUTE;
        return String.format("%02d:%02d", minutes, seconds);
    }

}

