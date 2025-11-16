package com.greencraft.blocks.visitor;
import com.greencraft.blocks.BlockType;
import com.greencraft.blocks.blockFactory.SimpleBlock;
// Чтобы в проекте у блоков оставалось то же самое поведение которое сделал Руслан, я просто вместо изменния всего кода добавил лишь один визитор урона
public class DamageVisitor implements BlockVisitor {
    private final int damage = 50; 
    private SimpleBlock damagedBlock = null;
    public void visit(SimpleBlock block) {
        block.takeDamage(damage);
        this.damagedBlock = block;
    }
    public boolean isBlockDestroyed() {
        if (damagedBlock == null || damagedBlock.isUnbreakable()) {
            return false;
        }
        return damagedBlock.getCurrentHP() <= 0;
    }
}