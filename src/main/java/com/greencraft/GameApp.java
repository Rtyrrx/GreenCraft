package com.greencraft;
import com.greencraft.core.GameFacade;
import com.greencraft.utils.EventLogger;
import com.greencraft.utils.ResourceManager;
import com.greencraft.utils.audio.MusicManager;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
public class GameApp extends Application {
    private GameFacade facade;
    public void start(Stage stage) {
        ResourceManager.getInstance();
        facade = new GameFacade();
        Group root = new Group();
        PhongMaterial floorMaterial = new PhongMaterial();
        javafx.scene.image.Image floorTexture = ResourceManager.getInstance().getTexture("floor.png");
        if(floorTexture != null) {
            floorMaterial.setDiffuseMap(floorTexture);
        } 
        else {
            floorMaterial.setDiffuseColor(Color.rgb(100, 150, 100));
        }
        for(int x = -20; x <= 20; x++) {
            for(int z = -20; z <= 20; z++) {
                Box tile = new Box(1, 0.05, 1);
                tile.setTranslateX(x);
                tile.setTranslateY(4);
                tile.setTranslateZ(z);
                tile.setMaterial(floorMaterial);
                root.getChildren().add(tile);
            }
        }
        facade.init(root);
        PerspectiveCamera camera = facade.getCamera();
        Scene scene = new Scene(root, 900, 600, true);
        scene.setFill(Color.SKYBLUE);
        scene.setCamera(camera);
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            switch(code) {
                case DIGIT1 -> facade.createStoneBlockAtPlayer();
                case DIGIT2 -> facade.createGrassBlockAtPlayer();
                case DIGIT3 -> facade.createWaterBlockAtPlayer();
                case G -> facade.deleteBlockAtPlayer();
                case T -> facade.makeNearestBlockGlowing();
                case H -> facade.setWalkMovement();
                case F -> facade.setFlyMovement();
                case J -> facade.setSlideMovement();
                case W, A, S, D, SPACE, CONTROL -> facade.movePlayer(code);
                default -> {
                    
                }
            }
        });
        stage.setTitle("Greencraft 3D - Patterns Demo");
        stage.setScene(scene);
        stage.show();
        MusicManager.getInstance().playBackgroundMusic("theme.mp3");
        EventLogger.log("Game started");
    }
    public static void main(String[] poop) {
        launch();
    }
}
