package com.greencraft.blocks.sound;

import com.greencraft.utils.ResourceManager;

public class WaterSoundStrategy implements BlockSoundStrategy {

    @Override
    public void playPlace() {
        ResourceManager.getInstance().playSound("place/stone1.wav");
    }

    @Override
    public void playDig() {
        ResourceManager.getInstance().playSound("dig/stone1.wav");
    }

    @Override
    public void playBreak() {
        ResourceManager.getInstance().playSound("break/stone1.wav");
    }
}
