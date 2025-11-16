package com.greencraft.core;

import com.greencraft.blocks.BlockType;
import com.greencraft.blocks.GlowingBlockDecorator;
import com.greencraft.blocks.Block;
import java.util.Optional;
import com.greencraft.blocks.visitor.DamageVisitor;
import com.greencraft.blocks.blockFactory.BlockFactory;
import com.greencraft.blocks.BlockManager;
import com.greencraft.blocks.blockFactory.GrassFactory;
import com.greencraft.blocks.blockFactory.StoneFactory;
import com.greencraft.blocks.blockFactory.WaterFactory;
import com.greencraft.entities.Player;
import com.greencraft.entities.movement.FlyMovement;
import com.greencraft.entities.movement.SlideMovement;
import com.greencraft.entities.movement.WalkMovement;
import com.greencraft.entities.movement.MovementStrategy;
import com.greencraft.utils.EventLogger;
import com.greencraft.utils.ResourceManager;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;

public class GameFacade {
    private Group sceneRoot;
    private Player player;
    private BlockManager blockManager;
    private PerspectiveCamera camera;

    public void init(Group root) {
        this.sceneRoot = root;
        this.blockManager = new BlockManager(root);
        this.player = new Player();
        camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-15);
        camera.setTranslateY(-2);
        camera.setNearClip(0.1);
        camera.setFarClip(1000);
        root.getChildren().add(player.getNode());
        setWalkMovement();
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }

    public void createGrassBlockAtPlayer() {
        BlockFactory grassFactory = new GrassFactory();
        var b = grassFactory.createBlock(BlockType.GRASS);
        blockManager.addBlockNearPlayer(player, b);
        // Play place sound using Strategy Pattern
        if (b.getSoundStrategy() != null) {
            b.getSoundStrategy().playPlace();
        }
    }

    public void createStoneBlockAtPlayer() {
        BlockFactory stoneFactory = new StoneFactory();
        var b = stoneFactory.createBlock(BlockType.STONE);
        blockManager.addBlockNearPlayer(player, b);
        // Play place sound using Strategy Pattern
        if (b.getSoundStrategy() != null) {
            b.getSoundStrategy().playPlace();
        }
    }

    public void createWaterBlockAtPlayer() {
        BlockFactory waterFactory = new WaterFactory();
        var b = waterFactory.createBlock(BlockType.WATER);
        blockManager.addBlockNearPlayer(player, b);
        // Play place sound using Strategy Pattern
        if (b.getSoundStrategy() != null) {
            b.getSoundStrategy().playPlace();
        }
    }

    // Реализация того самого Strategy патерна который долго сделать не мог, и
    // понять в то же время
    public void setWalkMovement() {
        player.setMovement(new WalkMovement());
        EventLogger.log("Movement set to Walk");
        // Play sound using Singleton ResourceManager
        ResourceManager.getInstance().playSound("movement_change.wav");
    }

    public void setFlyMovement() {
        player.setMovement(new FlyMovement());
        EventLogger.log("Movement set to Fly");
        // Play sound using Singleton ResourceManager
        ResourceManager.getInstance().playSound("movement_change.wav");
    }

    public void setSlideMovement() {
        player.setMovement(new SlideMovement());
        EventLogger.log("Movement set to Slide");
        // Play sound using Singleton ResourceManager
        ResourceManager.getInstance().playSound("movement_change.wav");
    }

    public void movePlayer(KeyCode key) {
        MovementStrategy m = player.getMovement();
        if (m != null)
            m.moveKey(player, key);
    }

    // Добавление нового функционала
    public void deleteBlockAtPlayer() {
        EventLogger.log("Player hits block (using Visitor)...");
        Optional<Block> blockOpt = blockManager.getLastBlock();
        if (blockOpt.isEmpty()) {
            EventLogger.log("No block to hit.");
            return;
        }
        Block block = blockOpt.get();
        DamageVisitor damageVisitor = new DamageVisitor();
        block.accept(damageVisitor);
        if (damageVisitor.isBlockDestroyed()) {
            blockManager.destroyBlock(block);
            // Play break sound using Strategy Pattern
            if (block.getSoundStrategy() != null) {
                block.getSoundStrategy().playBreak();
            }
        } else if (!block.isUnbreakable()) {
            EventLogger.log("Block damaged. Hit again!!!");
            // Play dig sound using Strategy Pattern
            if (block.getSoundStrategy() != null) {
                block.getSoundStrategy().playDig();
            }
        }
    }

    public void makeNearestBlockGlowing() {
        Optional<Block> blockOpt = blockManager.getLastBlock();
        if (blockOpt.isEmpty()) {
            EventLogger.log("No block to toggle glow.");
            return;
        }
        Block block = blockOpt.get();

        // Toggle: if already glowing, remove decorator; otherwise add it
        if (block instanceof GlowingBlockDecorator) {
            EventLogger.log("Removing Glowing Decorator.");
            GlowingBlockDecorator glowingBlock = (GlowingBlockDecorator) block;
            glowingBlock.stopGlowing();
            // Get the inner block and replace
            Block innerBlock = glowingBlock.getInner();
            blockManager.replaceBlock(block, innerBlock);
        } else {
            EventLogger.log("Adding Glowing Decorator.");
            GlowingBlockDecorator glowingBlock = new GlowingBlockDecorator(block);
            blockManager.replaceBlock(block, glowingBlock);
        }
    }
}