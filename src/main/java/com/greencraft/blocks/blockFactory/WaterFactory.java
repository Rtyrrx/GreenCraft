package com.greencraft.blocks.blockFactory;
import com.greencraft.blocks.Block;
import com.greencraft.blocks.BlockType;
import com.greencraft.blocks.sound.WaterSoundStrategy;
import com.greencraft.utils.EventLogger;
import com.greencraft.utils.ResourceManager;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
public class WaterFactory implements BlockFactory {
    public Block createBlock(BlockType blockType) {
        Box box = new Box(1, 1, 1);
        box.setTranslateY(2);
        // Use Singleton ResourceManager to load texture
        PhongMaterial material = new PhongMaterial();
        Image texture = ResourceManager.getInstance().getTexture("water.png");
        if(texture != null) {
            material.setDiffuseMap(texture);
        } 
        else {
            // Fallback to color if texture not found
            material.setDiffuseColor(Color.DEEPSKYBLUE);
        }
        box.setMaterial(material);
        Block b = new SimpleBlock(box, BlockType.WATER);
        // Assign sound strategy (Strategy Pattern)
        b.setSoundStrategy(new WaterSoundStrategy());
        EventLogger.log("Created WATER block");
        return b;
    }
}
