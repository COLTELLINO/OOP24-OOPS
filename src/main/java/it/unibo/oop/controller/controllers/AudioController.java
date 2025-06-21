package it.unibo.oop.controller.controllers;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.oop.model.managers.AudioManager;
import it.unibo.oop.utils.Percentage;
/** 
 * Controller for managing audio settings in the game.
 * It allows setting the volume of the audio manager.
 */
@SuppressFBWarnings(value = {"EI2", "EI"}, 
    justification = "AudioController needs to keep a reference to AudioManager for sound control.")
public class AudioController {

    private final AudioManager audioManager;
    /**
     * Constructor that initializes the AudioController with the given AudioManager.
     * @param audioManager
     */
    public AudioController(final AudioManager audioManager) {
        this.audioManager = audioManager;
    }
    /**
     * Sets the volume of the audio manager to the specified percentage.
     * @param volume
     */
    public void setVolume(final Percentage volume) {
        audioManager.setVolume(volume);
    }
    /**
     * Gets the current volume of the audio manager.
     * @return the current volume as a Percentage
     */
    public Percentage getVolume() {
        return audioManager.getVolume();
    }
}
