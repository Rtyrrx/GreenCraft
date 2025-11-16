package com.greencraft.utils;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private static ResourceManager instance;

    private final Map<String, Image> textureCache = new HashMap<>();

    private final Map<String, AudioClip> soundCache = new HashMap<>();

    private ResourceManager() {
        EventLogger.log("ResourceManager initialized (Singleton pattern)");
    }

    public static synchronized ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    public Image getTexture(String filename) {
        if (textureCache.containsKey(filename)) {
            return textureCache.get(filename);
        }

        try {
            InputStream stream = getClass().getResourceAsStream("/assets/textures/" + filename);
            if (stream == null) {
                EventLogger.log("Texture not found: " + filename + " (using fallback)");
                textureCache.put(filename, null); // Cache the miss to avoid repeated lookups
                return null;
            }

            Image image = new Image(stream);
            textureCache.put(filename, image);
            EventLogger.log("Texture loaded: " + filename);
            return image;

        } catch (Exception e) {
            EventLogger.log("Error loading texture: " + filename + " - " + e.getMessage());
            textureCache.put(filename, null);
            return null;
        }
    }

    public AudioClip getSound(String filename) {
        if (soundCache.containsKey(filename)) {
            return soundCache.get(filename);
        }

        try {
            String resourcePath = "/assets/sounds/" + filename;
            java.net.URL soundUrl = getClass().getResource(resourcePath);

            if (soundUrl == null) {
                EventLogger.log("Error loading sound: " + filename + " - File not found");
                soundCache.put(filename, null);
                return null;
            }

            String path = soundUrl.toExternalForm();
            AudioClip clip = new AudioClip(path);
            soundCache.put(filename, clip);
            EventLogger.log("Sound loaded: " + filename);
            return clip;

        } catch (Exception e) {
            EventLogger.log("Error loading sound: " + filename + " - " + e.getMessage());
            soundCache.put(filename, null);
            return null;
        }
    }

    public void playSound(String filename) {
        AudioClip clip = getSound(filename);
        if (clip != null) {
            clip.play();
        }
    }

    public void clearCaches() {
        textureCache.clear();
        soundCache.clear();
        EventLogger.log("Resource caches cleared");
    }

    public String getCacheStats() {
        return "Textures cached: " + textureCache.size() + ", Sounds cached: " + soundCache.size();
    }
}
