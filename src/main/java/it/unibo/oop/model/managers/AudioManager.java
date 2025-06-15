package it.unibo.oop.model.managers;

import java.net.URL;
import java.util.List;

import it.unibo.oop.utils.Percentage;

/**
 * 
 */
public interface AudioManager {
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
     */
    void playMusic(int i);
    /**
     * Stops the currently playing music.
     */
    void stopMusic();
    /**
     * Plays a sound effect once.
     * @param i
     */
    void playSoundEffect(int i);
    /**
     * Gets the music playing state.
     * @return true if music is playing, false otherwise
     */
    boolean isMusicPlaying();
    /**
     * Sets the music playing state.
     * @param isMusicPlaying
     */
    void setMusicPlaying(boolean isMusicPlaying);
    /**
     * Sets the volume of the audio.
     * @param volume the volume to set, as a Percentage
     */
    void setVolume(Percentage volume);
}
