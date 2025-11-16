package com.greencraft.utils.audio;

import javafx.scene.media.AudioClip;

public class AudioClipAdapter implements AudioPlayer {
    private AudioClip audioClip;
    private boolean isPlaying = false;

    public AudioClipAdapter(String resourcePath) {
        try {
            java.net.URL soundUrl = getClass().getResource(resourcePath);
            if (soundUrl != null) {
                String path = soundUrl.toExternalForm();
                this.audioClip = new AudioClip(path);
            }
        } catch (Exception e) {
            System.err.println("Error loading audio: " + resourcePath + " - " + e.getMessage());
        }
    }

    @Override
    public void play() {
        if (audioClip != null && !isPlaying) {
            // AudioClip.play() always plays from start
            audioClip.play();
            isPlaying = true;
        }
    }

    @Override
    public void stop() {
        if (audioClip != null) {
            audioClip.stop(); // This actually exists but works differently
            isPlaying = false;
        }
    }

    @Override
    public void loop() {
        if (audioClip != null) {
            audioClip.setCycleCount(AudioClip.INDEFINITE);
            audioClip.play();
            isPlaying = true;
        }
    }

    @Override
    public void setVolume(double volume) {
        if (audioClip != null) {
            audioClip.setVolume(Math.max(0.0, Math.min(1.0, volume)));
        }
    }
}