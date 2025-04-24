package it.unibo.oop.controller;
/**
 * Interface that functions as the controller of the application.
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
