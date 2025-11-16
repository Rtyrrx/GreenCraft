package com.greencraft.blocks.visitor;
import com.greencraft.blocks.blockFactory.SimpleBlock;
public interface BlockVisitor {
    void visit(SimpleBlock block);
}