package com.minesweeper.view;

import com.minesweeper.model.Cell;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class CellView extends StackPane {
    private static final int CELL_SIZE = 32;

    private final Cell cell;
    private final Label content;

    public CellView(Cell cell) {
       this.cell = cell;
       this.content = new Label("");

       setAlignment(Pos.CENTER);

       setPrefSize(CELL_SIZE, CELL_SIZE);
       setMaxSize(CELL_SIZE, CELL_SIZE);
       setMinSize(CELL_SIZE, CELL_SIZE);

       getChildren().add(content);
       refresh();
    }

    public void refresh() {
        if (cell.isRevealed()) {
            showRevealedCell();
        } else if (cell.isFlagged()) {
            showFlaggedCell();
        } else {
            showHiddenCell(cell);
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

    private void showHiddenCell(Cell cell) {
       setStyle("-fx-background-color: #a0aec0;" + "-fx-border-color: #718096;" + "-fx-border-width: 1;");
    }
}
