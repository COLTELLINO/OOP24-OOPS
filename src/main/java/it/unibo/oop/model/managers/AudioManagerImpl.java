package it.unibo.oop.model.managers;

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

import it.unibo.oop.utils.Percentage;

/**
 * Handles audio playback.
 */
public class AudioManagerImpl implements AudioManager {
    private static final float FLOAT_DB = 20.0f;
    private Percentage volume = Percentage.TEN_PERCENT;
    private final List<URL> soundList = new ArrayList<>();
    private Clip musicClip;
    private Clip soundEffectClip;
    private boolean isMusicPlaying;

    /**
     * Initializes the AudioHandler and adds audio files to the sound list.
     */
    public AudioManagerImpl() {
        this.soundList.add(AudioManagerImpl.class.getResource("/Audio/SoundEffects/explosion.wav"));
        this.soundList.add(AudioManagerImpl.class.getResource("/Audio/SoundEffects/hit.wav"));
        this.soundList.add(AudioManagerImpl.class.getResource("/Audio/SoundEffects/shot.wav"));
        this.soundList.add(AudioManagerImpl.class.getResource("/Audio/SoundEffects/xp.wav"));
        this.soundList.add(AudioManagerImpl.class.getResource("/Audio/SoundEffects/select.wav"));
        this.soundList.add(AudioManagerImpl.class.getResource("/Audio/Music/OOP_Adventure.wav"));
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
     */
    @Override
    public void playMusic(final int i) {
        this.stopMusic();
        this.setMusicPlaying(true);
        this.musicClip = this.setClip(i);
        this.applyVolume(musicClip);
        this.play(musicClip);
        this.loop(musicClip);
    }

    /**
     * Stops the currently playing music.
     */
    @Override
    public void stopMusic() {
        this.setMusicPlaying(false);
        this.stop(musicClip);
    }
    /**
     * Gets the music playing state.
     * @return true if music is playing, false otherwise
     */
    @Override
    public boolean isMusicPlaying() {
        return isMusicPlaying;
    }
    /**
     * Sets the music playing state.
     * @param isMusicPlaying
     */
    @Override
    public void setMusicPlaying(final boolean isMusicPlaying) {
        this.isMusicPlaying = isMusicPlaying;
    }
    /**
     * Plays a sound effect once.
     * @param i
     */
    @Override
    public void playSoundEffect(final int i) {
        this.soundEffectClip = this.setClip(i);
        this.applyVolume(soundEffectClip);
        this.play(soundEffectClip);
    }
    /**
     * Sets the volume of the audio.
     * @param volume the volume to set, as a Percentage
     */
    @Override
    public void setVolume(final Percentage volume) {
        if (volume != null) {
            this.volume = volume;
            this.applyVolume(musicClip);
        }
    }
    /**
     * Gets the current volume.
     * @return the current volume as a Percentage
     */
    @Override
    public Percentage getVolume() {
        return this.volume;
    }
    /**
     * Applies the volume to the clip.
     */
    private void applyVolume(Clip clip) {
        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            final FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            final float dB = (float) (Math.log(this.volume.getPercentage()) / Math.log(10.0) * FLOAT_DB);
            volumeControl.setValue(dB);
        }
    }
    /**
     * Sets the audio file to be played.
     * @param i the index of the audio file in the sound list
     */
    private Clip setClip(final int i) {
        if (this.soundList.size() > i && this.soundList.get(i) != null) {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(this.soundList.get(i)));
                return clip;
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                Logger.getLogger(this.getClass().getName())
                        .log(Level.SEVERE, e.getClass().getSimpleName() + " occurred: ", e);
            }
        }
        return null;
    }
    /**
     * Plays the currently set audio file.
     */
    private void play(Clip clip) {
        if (clip != null) {
            clip.start();
        }
    }
    /**
     * Loops the currently set audio file.
     */
    private void loop(Clip clip) {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    /**
     * Stops the currently playing audio file.
     */
    private void stop(Clip clip) {
        if (clip != null) {
            clip.stop();
        }
    }
}
