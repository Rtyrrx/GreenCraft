package com.greencraft.blocks;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
public class GlowingBlockDecorator extends BlockDecorator {
    private AnimationTimer glowingTimer;
    private PhongMaterial material;
    public GlowingBlockDecorator(Block inner) {
        super(inner);
        // Get the material from the inner block
        if(inner.getNode() instanceof Box) {
            Box box = (Box) inner.getNode();
            material = (PhongMaterial) box.getMaterial();
        }
        startGlowing();
    }
    private void startGlowing() {
        glowingTimer = new AnimationTimer() {
            private long lastUpdate = 0;
            private double emissionLevel = 0.0;
            private boolean increasing = true;
            public void handle(long now) {
                if(now - lastUpdate < 50_000_000) {
                    return;
                }
                if(increasing) {
                    emissionLevel += 0.05;
                    if(emissionLevel > 1.0) {
                        increasing = false;
                    }
                } 
                else {
                    emissionLevel -= 0.05;
                    if(emissionLevel < 0.0) {
                        increasing = true;
                    }
                }
                // Set self-illumination color (makes it glow)
                if(material != null) {
                    Color glowColor = Color.rgb(
                    (int) (255 * emissionLevel),
                    (int) (255 * emissionLevel),
                    (int) (200 * emissionLevel));
                    material.setSelfIlluminationMap(null);
                    // Use specular color for glow effect
                    material.setSpecularColor(glowColor);
                    material.setSpecularPower(20);
                }
                lastUpdate = now;
            }
        };
        glowingTimer.start();
    }
    public void stopGlowing() {
        if(glowingTimer != null) {
            glowingTimer.stop();
        }
        inner.getNode().setEffect(null);
    }
}
