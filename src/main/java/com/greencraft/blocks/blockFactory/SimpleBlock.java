package com.greencraft.blocks.blockFactory;
import com.greencraft.blocks.Block;
import com.greencraft.blocks.BlockType; 
import com.greencraft.blocks.visitor.BlockVisitor;
import javafx.scene.shape.Box;
public class SimpleBlock extends Block {
    public SimpleBlock(Box b, BlockType type) {
        super(); 
        this.node = b;
        this.type = type; 
        switch(type) {
            case GRASS:
                this.maxHP = 50;
            case WATER: 
                this.maxHP = 100; 
                break;
            case STONE:
                this.maxHP = 200; 
                break;
        }
        this.currentHP = this.maxHP;
    }
    public void accept(BlockVisitor visitor) { 
        visitor.visit(this); 
    }
}