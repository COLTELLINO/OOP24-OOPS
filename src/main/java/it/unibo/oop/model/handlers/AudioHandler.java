package it.unibo.oop.model.handlers;

import java.net.URL;
import java.util.List;

import it.unibo.oop.utils.Percentage;

/**
 * 
 */
public interface AudioHandler {
    /**
     * Adds an url to the soundList.
     * @param url
     */
    void addSound(URL url);
    /**
     * Adds a list of urls to the soundList.
     * @param urlList
     */
    void addSoundList(List<URL> urlList);
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
