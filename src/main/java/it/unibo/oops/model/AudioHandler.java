package it.unibo.oops.model;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Duration;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * 
 */
public class AudioHandler {

    private final List<URL> soundList = new ArrayList<>(); //considera mappa
    private MediaPlayer mediaPlayer;
    private double volume = 0.5;
    /**
     * 
     */
    public AudioHandler() {
        this.soundList.add(AudioHandler.class.getResource("./resources/Audio/Music/test.mp3"));
        this.soundList.add(AudioHandler.class.getResource("./resources/Audio/SoundEffects/test.mp3"));
    }
    /**
     * @param i
     */
    public void playMusic(final int i) {
        this.setFile(i);
        this.play();
        this.loop();
    }
    /**
     *
     */
    public void stopMusic() {
        this.stop();
    }
    /**
     * @param i
     */
    public void playSoundEffect(final int i) {
        this.setFile(i);
        this.play();
    }
    /**
     * @param volumeScale
     */
    public void setVolume(final Percentage volumeScale) {
        volume = volumeScale.getPercentage();
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }
    /**
     * @param i
     */
    private void setFile(final int i) {
        Platform.runLater(() -> {
            if (this.soundList.get(i) != null) {
                final Media media = new Media(new File(soundList.get(i).getPath()).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(volume);
            }
        });
    }
    /**
     * 
     */
    private void play() {
        Platform.runLater(() -> {
            if (mediaPlayer != null) {
                mediaPlayer.play();
            }
        });
    }
    /**
     * 
     */
    private void loop() {
        Platform.runLater(() -> {
            if (mediaPlayer != null) {
                mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
                mediaPlayer.play();
            }
        });
    }
    /**
     * 
     */
    private void stop() {
        Platform.runLater(() -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
        });
    }
}
