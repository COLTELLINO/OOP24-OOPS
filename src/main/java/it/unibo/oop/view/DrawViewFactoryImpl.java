package it.unibo.oop.view;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.controller.GameState;
import it.unibo.oop.model.EnemyManager;
import it.unibo.oop.model.ExperienceManager;
import it.unibo.oop.model.Player;
import it.unibo.oop.model.WeaponManager;

/**
 * Class to create windows.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
justification = "The purpose of this class is to give an usable window,"
              + "so it needs to be modifiable")
public class DrawViewFactoryImpl implements DrawViewFactory {
    private DrawView window;
    /**
     * @param gameState
     * @param player
     * @param enemyManager
     * @param weaponManager
     * @param experienceManager
     * @return a DrawView window.
     */
    @Override
    public DrawView createDrawView(final GameState gameState, final Player player, final EnemyManager enemyManager, 
        final WeaponManager weaponManager, final ExperienceManager experienceManager) {
            try {
                SwingUtilities.invokeAndWait(() -> {
                    this.window = new DrawViewImpl(GameState.TITLESTATE, player, 
                            enemyManager, weaponManager, experienceManager);
                    });
            } catch (InvocationTargetException | InterruptedException e) {
                Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, e.getClass().getSimpleName() + " occurred: ", e);
            }
            return this.window;
    }
}
