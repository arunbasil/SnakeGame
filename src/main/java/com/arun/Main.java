package com.arun;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameController game = new GameController();

        // Create a Group as the root node
        Group root = new Group();
        root.getChildren().add(game.getCanvas());

        // Create the Scene with the Group as the root node
        Scene scene = new Scene(root, GameController.WIDTH,
                GameController.HEIGHT);

        // Set up key event handling
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP, W -> game.setDirection(Direction.UP);
                case DOWN, S -> game.setDirection(Direction.DOWN);
                case LEFT, A -> game.setDirection(Direction.LEFT);
                case RIGHT, D -> game.setDirection(Direction.RIGHT);
                default -> {
                }
            }
        });

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        game.startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
