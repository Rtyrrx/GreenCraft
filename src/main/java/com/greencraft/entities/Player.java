package com.greencraft.entities;

import com.greencraft.utils.EventLogger;
import com.greencraft.utils.ResourceManager;
import com.greencraft.entities.movement.MovementStrategy;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Player {
    private Box node;
    private MovementStrategy movement;

    public Player() {
        node = new Box(0.8, 1, 0.8);
        node.setTranslateY(2);
        node.setTranslateZ(0);
        node.setTranslateX(0);

        PhongMaterial material = new PhongMaterial();
        Image texture = ResourceManager.getInstance().getTexture("character.png");

        if (texture != null) {
            material.setDiffuseMap(texture);
        } else {
            material.setDiffuseColor(Color.BLUE);
        }

        node.setMaterial(material);
        EventLogger.log("Player created with texture");
    }

    public Node getNode() {
        return node;
    }

    public void setMovement(MovementStrategy m) {
        this.movement = m;
        EventLogger.log("Player movement changed to: " + m.getClass().getSimpleName());
    }

    public void update() {
        if (movement != null) {
            movement.move(this);
        }
    }

    public MovementStrategy getMovement() {
        return movement;
    }

    public void jump() {
        node.setTranslateY(node.getTranslateY() - 0.5);
        EventLogger.log("Player jumped");
    }

    public double getX() {
        return node.getTranslateX();
    }

    public double getY() {
        return node.getTranslateY();
    }

    public double getZ() {
        return node.getTranslateZ();
    }
}
