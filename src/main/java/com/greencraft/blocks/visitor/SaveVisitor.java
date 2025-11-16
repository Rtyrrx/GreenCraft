package com.greencraft.blocks.visitor;
import com.greencraft.blocks.blockFactory.SimpleBlock;
public class SaveVisitor implements BlockVisitor {
    public void visit(SimpleBlock block) {
        System.out.println("Saving block at: " + block.getNode().getTranslateX() + "," + block.getNode().getTranslateZ());
    }
}
