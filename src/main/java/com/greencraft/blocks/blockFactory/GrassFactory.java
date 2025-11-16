package com.greencraft.blocks.blockFactory;
import com.greencraft.blocks.Block;
import com.greencraft.blocks.BlockType;
import com.greencraft.blocks.sound.GrassSoundStrategy;
import com.greencraft.utils.EventLogger;
import com.greencraft.utils.ResourceManager;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
public class GrassFactory implements BlockFactory {
    public Block createBlock(BlockType blockType) {
        Box box = new Box(1, 1, 1);
        box.setTranslateY(2);
        // Use Singleton ResourceManager to load texture
        PhongMaterial material = new PhongMaterial();
        Image texture = ResourceManager.getInstance().getTexture("grass.png");
        if(texture != null) {
            material.setDiffuseMap(texture);
        } 
        else {
            // Fallback to color if texture not found
            material.setDiffuseColor(Color.GREEN);
        }
        box.setMaterial(material);
        Block b = new SimpleBlock(box, BlockType.GRASS);
        // Assign sound strategy (Strategy Pattern)
        b.setSoundStrategy(new GrassSoundStrategy());
        EventLogger.log("Created GRASS Block");
        return b;
    }
}
