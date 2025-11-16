package com.greencraft.utils.audio;

import com.greencraft.utils.EventLogger;

public class MusicManager {
    private static MusicManager instance;
    private AudioPlayer backgroundMusic;

    private MusicManager() {
        EventLogger.log("MusicManager initialized (Singleton + Adapter pattern)");
    }

    public static synchronized MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }

    public void playBackgroundMusic(String filename) {
        stopBackgroundMusic();

        // ADAPTER PATTERN DEMONSTRATION:
        // We use MediaPlayerAdapter for long audio files (mp3, ogg)
        // Could also use AudioClipAdapter for short sounds (wav)
        // Both adapt DIFFERENT incompatible APIs to our AudioPlayer interface!

        if (filename.endsWith(".mp3") || filename.endsWith(".ogg")) {
            backgroundMusic = new MediaPlayerAdapter("/assets/sounds/" + filename);
            EventLogger.log("Using MediaPlayerAdapter for: " + filename);
        } else {
            backgroundMusic = new AudioClipAdapter("/assets/sounds/" + filename);
            EventLogger.log("Using AudioClipAdapter for: " + filename);
        }

        backgroundMusic.setVolume(0.3); // 30% volume for background
        backgroundMusic.loop();

        EventLogger.log("Background music started: " + filename);
    }

    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
            EventLogger.log("Background music stopped");
        }
    }

    public void setMusicVolume(double volume) {
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(volume);
        }
    }
}
