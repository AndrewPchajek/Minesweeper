package com.minesweeper;

import com.minesweeper.config.GameDifficulty;
import com.minesweeper.engine.GameEngine;
import com.minesweeper.view.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MinesweeperApplication extends Application {
    @Override
    public void start(Stage stage) {
        GameEngine gameEngine = new GameEngine(GameDifficulty.BEGINNER);
        GameView gameView = new GameView(gameEngine);
        Scene scene = new Scene(gameView, 800, 800);

        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}