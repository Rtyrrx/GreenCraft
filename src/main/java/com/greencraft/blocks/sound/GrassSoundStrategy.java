package com.greencraft.blocks.sound;

import com.greencraft.utils.ResourceManager;

public class GrassSoundStrategy implements BlockSoundStrategy {

    @Override
    public void playPlace() {
        ResourceManager.getInstance().playSound("place/grass1.wav");
    }

    @Override
    public void playDig() {
        // Randomly choose between grass dig sounds for variety
        String sound = Math.random() > 0.5 ? "dig/grass1.wav" : "dig/grass2.wav";
        ResourceManager.getInstance().playSound(sound);
    }

    @Override
    public void playBreak() {
        ResourceManager.getInstance().playSound("break/grass1.wav");
    }
}
