package it.unibo.oop.view.window;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.entities.Player;
import it.unibo.oop.model.managers.EnemyManager;
import it.unibo.oop.model.managers.ExperienceManager;
import it.unibo.oop.model.managers.HealthManager;
import it.unibo.oop.model.managers.ProjectileManager;
import it.unibo.oop.model.managers.WeaponManager;
import it.unibo.oop.utils.GameState;
import it.unibo.oop.utils.Camera;

/**
 * Class to create windows.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "The purpose of this class is to give an usable window,"
              + "so it needs to be modifiable")
public class ViewManagerFactoryImpl implements ViewManagerFactory {
    private ViewManager window;
    /**
     * @param gameState
     * @param player
     * @param enemyManager
     * @param weaponManager
     * @param experienceManager
     * @param healthManager
     * @return a DrawView window.
     */
    @Override
    public ViewManager createViewManager(final GameState gameState, final Player player, final EnemyManager enemyManager, 
        final WeaponManager weaponManager, final ExperienceManager experienceManager,
                final HealthManager healthManager, final ProjectileManager projectileManager, final Camera camera) {
            try {
                SwingUtilities.invokeAndWait(() -> {
                    this.window = new ViewManagerImpl(GameState.TITLESTATE, player, 
                            enemyManager, weaponManager, experienceManager, healthManager, projectileManager, camera);
                    });
            } catch (InvocationTargetException | InterruptedException e) {
                Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, e.getClass().getSimpleName() + " occurred: ", e);
            }
            return this.window;
    }
}
