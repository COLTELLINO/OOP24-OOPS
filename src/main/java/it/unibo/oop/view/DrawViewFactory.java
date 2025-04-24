package it.unibo.oop.view;

import it.unibo.oop.controller.GameState;
import it.unibo.oop.model.EnemyManager;
import it.unibo.oop.model.ExperienceManager;
import it.unibo.oop.model.Player;
import it.unibo.oop.model.WeaponManager;
/**
 * Interface with the intent of creating windows.
 */
public interface DrawViewFactory {

    /**
     * @param gameState
     * @param player
     * @param enemyManager
     * @param weaponManager
     * @param experienceManager
     * @return a DrawViewImpl.
     */
    DrawView createDrawView(GameState gameState, Player player, EnemyManager enemyManager,
            WeaponManager weaponManager, ExperienceManager experienceManager);

}
