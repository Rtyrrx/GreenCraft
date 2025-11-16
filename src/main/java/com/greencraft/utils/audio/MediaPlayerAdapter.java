package com.greencraft.utils.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MediaPlayerAdapter implements AudioPlayer {
    private MediaPlayer mediaPlayer;
    private String soundPath;

    public MediaPlayerAdapter(String resourcePath) {
        try {
            java.net.URL soundUrl = getClass().getResource(resourcePath);
            if (soundUrl != null) {
                Media media = new Media(soundUrl.toExternalForm());
                this.mediaPlayer = new MediaPlayer(media);
                this.soundPath = resourcePath;
            }
        } catch (Exception e) {
            System.err.println("Error loading audio: " + resourcePath + " - " + e.getMessage());
        }
    }

    @Override
    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        }
    }

    @Override
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @Override
    public void loop() {
        if (mediaPlayer != null) {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }
    }

    @Override
    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(Math.max(0.0, Math.min(1.0, volume)));
        }
    }
}
