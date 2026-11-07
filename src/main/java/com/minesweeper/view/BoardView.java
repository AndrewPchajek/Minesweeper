package com.minesweeper.view;

import com.minesweeper.model.Board;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

import com.minesweeper.engine.GameEngine;

public class BoardView extends GridPane{
    private final GameEngine gameEngine;

    public BoardView(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

        setAlignment(Pos.CENTER);
        setHgap(2);
        setVgap(2);

        createBoard();
    }

    public void createBoard() {
        Board board = gameEngine.getGame().getBoard();

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                CellView cellView = new CellView(gameEngine.getGame(), row, col);

                final int cellRow = row;
                final int cellCol = col;

                cellView.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        gameEngine.revealCell(cellRow, cellCol);
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        gameEngine.toggleFlag(cellRow, cellCol);
                    }

                    refreshBoard();
                });

                add(cellView, col, row);
            }
        }
    }

    public void refreshBoard() {
        for (javafx.scene.Node node : getChildren()) {
            if (node instanceof CellView cellView) {
                cellView.refresh();
            }
        }
    }
}
