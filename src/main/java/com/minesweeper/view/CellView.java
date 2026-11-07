package com.minesweeper.view;

import com.minesweeper.model.Cell;
import com.minesweeper.model.Game;
import com.minesweeper.model.GameState;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class CellView extends StackPane {
    private static final int CELL_SIZE = 32;

    private final Game game;
    private final Cell cell;
    private final int row;
    private final int col;
    private final Label content;

    public CellView(Game game, int row, int col) {
       this.game = game;
       this.cell = game.getBoard().getCell(row, col);
       this.row = row;
       this.col = col;
       this.content = new Label("");

       setAlignment(Pos.CENTER);

       setPrefSize(CELL_SIZE, CELL_SIZE);
       setMaxSize(CELL_SIZE, CELL_SIZE);
       setMinSize(CELL_SIZE, CELL_SIZE);

       getChildren().add(content);
       refresh();
    }

    public void refresh() {
        if (game.getState() == GameState.LOST && cell.isMine()){
            showMine();
        } else if (cell.isRevealed()) {
            showRevealedCell();
        } else if (cell.isFlagged()) {
            showFlaggedCell();
        } else {
            showHiddenCell();
        }
    }

    private void showMine() {
        content.setText("M");

        if (game.isExplodedCell(row, col)) {
            setStyle("-fx-background-color: #ef4444;" + "-fx-border-color: #b91c1c;" + "-fx-border-width: 1;");
        } else {
            setStyle("-fx-background-color: #fecaca;" + "-fx-border-color: #f87171;" + "-fx-border-width: 1;");
        }
    }

    private void showRevealedCell() {
        setStyle("-fx-background-color: #e2e8f0;" + "-fx-border-color: #cbd5e0;" + "-fx-border-width: 1;");

        if (cell.isMine()) {
            content.setText("M");
        } else if (cell.getAdjacentMines() > 0) {
            content.setText(String.valueOf(cell.getAdjacentMines()));
        } else {
            content.setText("");
        }
    }

    private void showFlaggedCell() {
        content.setText("F");
        setStyle("-fx-background-color: #a0aec0;" + "-fx-border-color: #718096;" + "-fx-border-width: 1;");
    }

    private void showHiddenCell() {
        content.setText("");
       setStyle("-fx-background-color: #a0aec0;" + "-fx-border-color: #718096;" + "-fx-border-width: 1;");
    }
}
