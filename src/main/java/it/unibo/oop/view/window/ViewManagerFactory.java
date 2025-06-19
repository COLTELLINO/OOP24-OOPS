package it.unibo.oop.view.window;

import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.managers.CollisionManager;
import it.unibo.oop.model.managers.EnemyManager;
import it.unibo.oop.model.managers.ExperienceManager;
import it.unibo.oop.model.managers.HealthManager;
import it.unibo.oop.model.managers.ProjectileManager;
import it.unibo.oop.model.managers.WeaponManager;
import it.unibo.oop.utils.Camera;
import it.unibo.oop.utils.GameState;
/**
 * Interface with the intent of creating windows.
 */
public interface ViewManagerFactory {

    /**
     * @param gameState
     * @param player
     * @param enemyManager
     * @param weaponManager
     * @param experienceManager
     * @param collisionManager
     * @param healthManager
     * @param projectileManager
     * @param camera
     * @return a DrawViewImpl.
     */
    ViewManager createViewManager(GameState gameState, Player player, EnemyManager enemyManager,
            WeaponManager weaponManager, ExperienceManager experienceManager, CollisionManager collisionManager,
            HealthManager healthManager, ProjectileManager projectileManager, Camera camera);
}
