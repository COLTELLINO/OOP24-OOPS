package it.unibo.oop.view.window;

import it.unibo.oop.controller.controllers.AudioController;
import it.unibo.oop.controller.controllers.GameController;
import it.unibo.oop.utils.Camera;
import it.unibo.oop.utils.GameState;
/**
 * Interface with the intent of creating windows.
 */
public interface ViewManagerFactory {

    /**
     * @param gameState
     * @param gameController
     * @param audioController
     * @param camera
     * @return a DrawViewImpl.
     */
    ViewManager createViewManager(GameState gameState, GameController gameController, 
        AudioController audioController, Camera camera);
}
