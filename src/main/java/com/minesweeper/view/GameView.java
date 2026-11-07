package com.minesweeper.view;

import com.minesweeper.config.GameDifficulty;
import com.minesweeper.engine.GameEngine;
import com.minesweeper.model.GameState;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameView extends BorderPane {
    private final GameEngine gameEngine;
    private Label statusLabel;
    private Label minesLabel;

    public GameView(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        setPadding(new Insets(20));
        setTop(createHeader());
        setCenter(new BoardView(gameEngine, this::refreshStatus));
        setBottom(createFooter());
    }

    private HBox createHeader() {
        Label title = new Label("Minesweeper");

        Button restartButton = new Button("New Game");
        ComboBox<GameDifficulty> difficultySelector = new ComboBox<>();
        difficultySelector.getItems().addAll(GameDifficulty.values());
        difficultySelector.setValue(gameEngine.getGame().getDifficulty());

        restartButton.setOnAction(event -> {
            gameEngine.resetGame();
            refreshGameView();
        });

        difficultySelector.setOnAction(event -> {
            GameDifficulty selectedDifficulty = difficultySelector.getValue();

            if (selectedDifficulty != null) {
                gameEngine.resetGame(selectedDifficulty);
                refreshGameView();
            }
        });

        HBox header = new HBox(15, title, restartButton, difficultySelector);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10));

        return header;
    }

    private HBox createFooter() {
        statusLabel = new Label();
        minesLabel = new Label();

        HBox footer = new HBox(15, statusLabel, minesLabel);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(10));

        refreshStatus();
        return footer;
    }

    private void refreshGameView() {
        setCenter(new BoardView(gameEngine, this::refreshStatus));
        refreshStatus();

        if (getScene() != null && getScene().getWindow() instanceof Stage stage) {
            stage.sizeToScene();
        }
    }

    private void refreshStatus() {
        minesLabel.setText("Remaining Mines: " + gameEngine.getRemainingMines());
        GameState gameState = gameEngine.getGame().getState();

        if (gameState == GameState.READY) {
            statusLabel.setText("READY");
        } else if (gameState == GameState.RUNNING) {
            statusLabel.setText("RUNNING");
        } else if (gameState == GameState.WON) {
            statusLabel.setText("YOU WON!");
        } else {
            statusLabel.setText("YOU LOST!");
        }
    }
}
