package it.unibo.oops.controller;
/**
 * 
 */
public interface GameThread {
    /**
     * Stops the gameThread.
     */
    void stopThread();
    /**
     *  Thread.
     */
    void run();
    /**
     *  Updates players, items, enemies.
     */
    void update();
}
