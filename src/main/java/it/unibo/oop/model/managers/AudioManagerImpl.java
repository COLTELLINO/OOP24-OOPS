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
    private Percentage volume = Percentage.ZERO_PERCENT;
    private final List<URL> soundList = new ArrayList<>();
    private Clip musicClip;
    private boolean isMusicPlaying;

    /**
     * Initializes the AudioHandler and adds audio files to the sound list.
     */
    public AudioManagerImpl() {
        this.setVolume(Percentage.TEN_PERCENT);
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
        this.musicClip = this.clipSetup(i);
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
        final Clip soundEffectClip = this.clipSetup(i);
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
     * @param clip
     */
    private void applyVolume(final Clip clip) {
        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            final FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            final float dB = (float) (Math.log(this.volume.getPercentage()) / Math.log(10.0) * FLOAT_DB);
            volumeControl.setValue(dB);
        }
    }
    /**
     * Sets the audio file to be played.
     * @param i the index of the audio file in the sound list
     * @return the clip to play.
     */
    private Clip clipSetup(final int i) {
        if (this.soundList.size() > i && this.soundList.get(i) != null) {
            try {
                final Clip clip = AudioSystem.getClip();
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
     * @param clip
     */
    private void play(final Clip clip) {
        if (clip != null) {
            clip.start();
        }
    }
    /**
     * Loops the currently set audio file.
     * @param clip
     */
    private void loop(final Clip clip) {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    /**
     * Stops the currently playing audio file.
     * @param clip
     */
    private void stop(final Clip clip) {
        if (clip != null) {
            clip.stop();
        }
    }
}
