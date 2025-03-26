package it.unibo.oops.model;
/**
 * 
 */
public interface Timer {

    /**
     * Updates the timer based on FPS.
     * @param updateLogic makes lambda function possible.
     */
    void update(Runnable updateLogic);
    /**
     *  @return frames per second
     */
    double getFps();
}
