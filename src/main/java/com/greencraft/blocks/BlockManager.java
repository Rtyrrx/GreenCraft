package com.greencraft.blocks;
import com.greencraft.entities.Player;
import com.greencraft.utils.EventLogger;
import javafx.scene.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; //здесья добавил эту библиотеку для того, чтобы  по тутору лишний раз не выводить null каждый раз при проверке
public class BlockManager {
    private final Group root;
    private final List<Block> blocks = new ArrayList<>();
    public BlockManager(Group root) { 
        this.root = root; 
    }
    public void addBlockNearPlayer(Player p, Block b) {
        double x = Math.round(p.getX());
        double z = Math.round(p.getZ()) + 2;
        b.getNode().setTranslateX(x);
        b.getNode().setTranslateZ(z);
        root.getChildren().add(b.getNode());
        blocks.add(b);
        EventLogger.log("Block added at: " + x + "," + z);
    }
    public void acceptVisitor(com.greencraft.blocks.visitor.BlockVisitor visitor) {
        for(var b:blocks) {
            b.accept(visitor);
        }
    }
    //По тутору обычное удаление блока тут не подойдет, нужно чтобы удаление было по хп и каждый клик по мышке только уменьшал его хп
    public Optional<Block> getLastBlock() {
        if(blocks.isEmpty()) return Optional.empty();
        return Optional.of(blocks.get(blocks.size() - 1));
    }
    public void replaceBlock(Block oldBlock, Block newBlock) {
        newBlock.getNode().setTranslateX(oldBlock.getNode().getTranslateX());
        newBlock.getNode().setTranslateZ(oldBlock.getNode().getTranslateZ());
        root.getChildren().remove(oldBlock.getNode());
        root.getChildren().add(newBlock.getNode());
        int index = blocks.indexOf(oldBlock);
        if(index != -1) {
            blocks.set(index, newBlock);
        }
        EventLogger.log("Block replaced in world.");
    }
    public void destroyBlock(Block block) {
        if(block == null) return;
        if(block instanceof GlowingBlockDecorator) {
            ((GlowingBlockDecorator) block).stopGlowing();
        }       
        if(block instanceof BlockDecorator) {
             Block inner = ((BlockDecorator) block).inner;
             if(inner instanceof GlowingBlockDecorator) {
                 ((GlowingBlockDecorator) inner).stopGlowing();
             }
        }
        if(blocks.remove(block)) {
            root.getChildren().remove(block.getNode());
            EventLogger.log("Block destroyed and removed from world.");
        }
    }
}
