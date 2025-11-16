package com.greencraft.entities.movement;
import com.greencraft.entities.Player;
import javafx.scene.input.KeyCode;
import com.greencraft.utils.EventLogger; 
public class FlyMovement implements MovementStrategy {
    private final double speed = 0.8;
    public void move(Player player) {

    }
    public void moveKey(Player player, KeyCode key){
        switch(key) {
            case W -> player.getNode().setTranslateZ(player.getNode().getTranslateZ() + speed);
            case S -> player.getNode().setTranslateZ(player.getNode().getTranslateZ() - speed);
            case A -> player.getNode().setTranslateX(player.getNode().getTranslateX() - speed);
            case D -> player.getNode().setTranslateX(player.getNode().getTranslateX() + speed);
            case SPACE -> player.getNode().setTranslateY(player.getNode().getTranslateY() - speed); 
            case CONTROL -> player.getNode().setTranslateY(player.getNode().getTranslateY() + speed); 
            default -> EventLogger.log("Fly mode key is pressed: " + key);
        }
    }
}