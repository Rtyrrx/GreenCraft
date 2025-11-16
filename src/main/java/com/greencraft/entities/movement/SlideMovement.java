package com.greencraft.entities.movement;
import com.greencraft.entities.Player;
import javafx.scene.input.KeyCode;
public class SlideMovement implements MovementStrategy {
    private final double imp = 1.5;
    public void move(Player player) {
        
    }
    //Тут в принципе ничего такого сложного не реализуется: просто добавляется еще движение при нажатии на клавишу
    public void moveKey(Player player, KeyCode key){
        switch(key) {
            case W -> player.getNode().setTranslateZ(player.getNode().getTranslateZ() + imp); 
            case S -> player.getNode().setTranslateZ(player.getNode().getTranslateZ() - imp);
            case A -> player.getNode().setTranslateX(player.getNode().getTranslateX() - imp);
            case D -> player.getNode().setTranslateX(player.getNode().getTranslateX() + imp);
            case SPACE -> player.jump();
        }
    }
}