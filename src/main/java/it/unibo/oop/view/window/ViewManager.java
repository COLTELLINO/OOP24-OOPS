package it.unibo.oop.view.window;

import java.awt.event.KeyListener;

import it.unibo.oop.utils.GameState;
/**
*
*/
public interface ViewManager {
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
    /**
     * Adds a key listener to the frame.
     * 
     * @param listener the key listener to add
     */
    void addKeyListener(KeyListener listener);
    /**
     * Sets whether the frame is focusable.
     * 
     * @param focusable true if the frame should be focusable
     */
    void setFocusable(boolean focusable);
}
