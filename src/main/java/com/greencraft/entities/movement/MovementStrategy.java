package com.greencraft.entities.movement;

import com.greencraft.entities.Player;
import javafx.scene.input.KeyCode;

public interface MovementStrategy {
    void move(Player player);
    void moveKey(Player player, KeyCode key);
}