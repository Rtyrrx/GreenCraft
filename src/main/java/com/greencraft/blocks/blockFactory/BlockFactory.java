package com.greencraft.blocks.blockFactory;

import com.greencraft.blocks.Block;
import com.greencraft.blocks.BlockType;

public interface BlockFactory {
    Block createBlock(BlockType blockType);
}


