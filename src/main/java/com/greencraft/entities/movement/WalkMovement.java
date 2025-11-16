package com.greencraft.entities.movement;
import com.greencraft.entities.Player;
import javafx.scene.input.KeyCode;
public class WalkMovement implements MovementStrategy {
    private final double speed = 0.5;
    public void move(Player player) {
    }
    public void moveKey(Player player, KeyCode key) {
        //тут как раз таки используется strategy: до этого space был по умолчанию в игре на пробел, но теперь и он делегируется другим способам передвижения. Короче говоря, что то я замудрил с описанием данной функции
        switch(key) {
            case W -> player.getNode().setTranslateZ(player.getNode().getTranslateZ() + speed);
            case A -> player.getNode().setTranslateX(player.getNode().getTranslateX() - speed);
            case S -> player.getNode().setTranslateZ(player.getNode().getTranslateZ() - speed);
            case D -> player.getNode().setTranslateX(player.getNode().getTranslateX() + speed);
            case SPACE -> player.jump();
        }
    }
}
