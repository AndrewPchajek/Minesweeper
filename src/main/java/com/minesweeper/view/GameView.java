package com.minesweeper.view;

import com.minesweeper.engine.GameEngine;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class GameView extends BorderPane {
    private final GameEngine gameEngine;

    public GameView(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        setPadding(new Insets(20));
        setTop(createHeader());
        setCenter(new BoardView(gameEngine));
        setBottom(createFooter());
    }

    private HBox createHeader() {
        HBox header = new HBox(new Label("Header"));
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10));
        return header;
    }

    private HBox createFooter() {
        HBox footer = new HBox(new Label("Footer"));
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(10));
        return footer;
    }
}
