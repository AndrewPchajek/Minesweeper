package com.minesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MinesweeperApplication extends Application {
    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane(new Label("Minesweeper"));
        Scene scene = new Scene(root, 800, 800);

        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}