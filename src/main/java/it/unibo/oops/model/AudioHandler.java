package it.unibo.oops.model;
/**
 * 
 */
public interface AudioHandler {
    /**
     * Plays a music file in a loop.
     * @param i
     * @param percentage
     */
    void playMusic(int i, Percentage percentage);

    /**
     * Stops the currently playing music.
     */
    void stopMusic();

    /**
     * Plays a sound effect once.
     * @param i
     * @param percentage
     */
    void playSoundEffect(int i, Percentage percentage);
}
