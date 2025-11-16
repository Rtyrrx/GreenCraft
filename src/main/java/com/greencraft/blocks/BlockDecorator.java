package com.greencraft.blocks;

import com.greencraft.blocks.visitor.BlockVisitor;
import com.greencraft.blocks.sound.BlockSoundStrategy;
import javafx.scene.Node;

public abstract class BlockDecorator extends Block {
    protected Block inner;

    public BlockDecorator(Block inner) {
        this.inner = inner;
    }

    @Override
    public Node getNode() {
        return inner.getNode();
    }

    @Override
    public BlockType getType() {
        return inner.getType();
    }

    @Override
    public void accept(BlockVisitor visitor) {
        inner.accept(visitor);
    }

    @Override
    public int getCurrentHP() {
        return inner.getCurrentHP();
    }

    @Override
    public boolean isUnbreakable() {
        return inner.isUnbreakable();
    }

    @Override
    public boolean takeDamage(int damage) {
        return inner.takeDamage(damage);
    }

    @Override
    public BlockSoundStrategy getSoundStrategy() {
        return inner.getSoundStrategy();
    }

    @Override
    public void setSoundStrategy(BlockSoundStrategy soundStrategy) {
        inner.setSoundStrategy(soundStrategy);
    }

    public Block getInner() {
        return inner;
    }
}