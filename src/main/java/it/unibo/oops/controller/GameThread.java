package it.unibo.oops.controller;

import java.util.logging.Logger;

import it.unibo.oops.controller.gamestate.GameState;
import it.unibo.oops.view.DrawViewImpl;

import java.util.logging.Level;

/**
* 
*/
public class GameThread implements Runnable {

    private Boolean stop = true;
    private final DrawViewImpl dv;
    /**
     * @param gameState
     */
    protected GameThread(final GameState gameState) {
        this.dv = new DrawViewImpl(gameState);
        this.startThread();
    }
    /**
     * Starts the gameThread.
     */
    private void startThread() {
        final Thread thread = new Thread(this);
        thread.start();
    }
    /**
     * Stops the gameThread.
     */
    protected void stopThread() {
        this.stop = false;
    }
    /**
     *  Thread.
     */
    @Override
    public void run() {
        while (stop) {
            try {
                Thread.sleep(1000);
                this.dv.draw();
                this.dv.setScreenDimension();
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, "Sleep Thread Error", e);
            }
        }
    }
}
