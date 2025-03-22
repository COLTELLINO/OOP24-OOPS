package it.unibo.oops.view;

import it.unibo.oops.controller.GameState;
/**
*
*/
public interface DrawView {
    /**
     * Shows the window.
     */
    void start();
    /**
     * Changes the state of the game.
     * 
     * @param gameState 
     */
    void changeGameState(GameState gameState);
    /**
     *  @return the current gameState.
     */
    GameState getCurrentGameState();
    /**
     *  Repaints the current Panel.
     */
    void repaint();
}
