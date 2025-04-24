package it.unibo.oop.model;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Handles audio playback.
 */
public class AudioHandlerImpl implements AudioHandler {
    private static final float FLOAT_DB = 20.0f;
    private final List<URL> soundList = new ArrayList<>();
    private Clip clip;

    /**
     * Initializes the AudioHandler and adds audio files to the sound list.
    */
    public AudioHandlerImpl() {
        this.soundList.add(AudioHandlerImpl.class.getResource("/Audio/Music/test.wav"));
        this.soundList.add(AudioHandlerImpl.class.getResource("/Audio/SoundEffects/test.wav"));
    }
    /**
     * Adds an url to the soundList.
     * @param url
     */
    @Override
    public void addSound(final URL url) {
        if (url != null) {
            this.soundList.add(url);
        }
    }
    /**
     * Adds a list of urls to the soundList.
     * @param urlList
     */
    @Override
    public void addSoundList(final List<URL> urlList) {
        for (final URL url : urlList) {
            if (url != null) {
                this.soundList.add(url);
            }
        }
    }
    /**
     * Plays a music file in a loop.
     * @param i
     * @param percentage
     */
    @Override
    public void playMusic(final int i, final Percentage percentage) {
        this.setFile(i);
        this.setVolume(percentage);
        this.play();
        this.loop();
    }

    /**
     * Stops the currently playing music.
     */
    @Override
    public void stopMusic() {
        this.stop();
    }

    /**
     * Plays a sound effect once.
     * @param i
     * @param percentage
     */
    @Override
    public void playSoundEffect(final int i, final Percentage percentage) {
        this.setFile(i);
        this.setVolume(percentage);
        this.play();
    }
    /**
     * Sets the volume of the audio clip.
     * @param volumeScale
     */
    private void setVolume(final Percentage volumeScale) {
        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            final FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            final float dB = (float) (Math.log(volumeScale.getPercentage()) / Math.log(10.0) * FLOAT_DB);
            volumeControl.setValue(dB);
        }
    }
    /**
     * Sets the audio file to be played.
     * @param i the index of the audio file in the sound list
     */
    private void setFile(final int i) {
        if (this.soundList.size() > i && this.soundList.get(i) != null) {
            try {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File(this.soundList.get(i).getPath())));
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, e.getClass().getSimpleName() + " occurred: ", e);
            }
        }
    }
    /**
     * Plays the currently set audio file.
     */
    private void play() {
        if (clip != null) {
            clip.start();
        }
    }
    /**
     * Loops the currently set audio file.
     */
    private void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    /**
     * Stops the currently playing audio file.
     */
    private void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
