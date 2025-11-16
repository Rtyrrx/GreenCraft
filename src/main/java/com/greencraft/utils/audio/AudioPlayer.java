package com.greencraft.utils.audio;

public interface AudioPlayer {
    void play();

    void stop();

    void loop();

    void setVolume(double volume);
}
