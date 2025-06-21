package it.unibo.oop.view.window;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.utils.GameState;
import it.unibo.oop.controller.controllers.AudioController;
import it.unibo.oop.controller.controllers.GameController;
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
     * @param gameController
     * @param player
     * @param enemyManager
     * @param weaponManager
     * @param experienceManager
     * @param collisionManager
     * @param healthManager
     * @return a DrawView window.
     */
    @Override
    public ViewManager createViewManager(final GameState gameState, final GameController gameController, 
            final AudioController audioController, final Camera camera) {
        try {
            SwingUtilities.invokeAndWait(() -> {
                this.window = new ViewManagerImpl(GameState.TITLESTATE, gameController, audioController, camera);
                });
        } catch (InvocationTargetException | InterruptedException e) {
            Logger.getLogger(this.getClass().getName())
                .log(Level.SEVERE, e.getClass().getSimpleName() + " occurred: ", e);
        }
        return this.window;
    }
}
