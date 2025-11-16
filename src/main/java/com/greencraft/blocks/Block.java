package com.greencraft.blocks;
import com.greencraft.blocks.visitor.BlockVisitor;
import com.greencraft.blocks.sound.BlockSoundStrategy;
import com.greencraft.utils.EventLogger;
import javafx.scene.Node;
public abstract class Block {
    protected BlockType type;
    protected Node node;
    protected int maxHP = 100;
    protected int currentHP;
    protected BlockSoundStrategy soundStrategy; // Strategy Pattern for sounds
    public Block() {
        // Для каких нибудь других паттернов можно дополнить эту часть кодика, реально
        // не знаю что туда добавить
    }
    public Node getNode() {
        return node;
    }
    public BlockType getType() {
        return type;
    }
    public BlockSoundStrategy getSoundStrategy() {
        return soundStrategy;
    }
    public void setSoundStrategy(BlockSoundStrategy soundStrategy) {
        this.soundStrategy = soundStrategy;
    }
    public abstract void accept(BlockVisitor visitor);
    public int getCurrentHP() {
        return currentHP;
    }
    public boolean isUnbreakable() {
        return maxHP == -1; //тут смотрите получается, что у него бесконечно будет убывать хп. Все норма
    }
    public boolean takeDamage(int damage) {
        if(isUnbreakable() == true) {
            EventLogger.log("Block is unbreakeable");
            return false;
        }
        this.currentHP -= damage;
        EventLogger.log("The HP is left for breaking Block: " + currentHP + " out of " + maxHP);
        if(this.currentHP == 0) {
            EventLogger.log("Block after so many attempts is finally destroyed!!!!");
            return true;
        }
        return false;
    }
}
