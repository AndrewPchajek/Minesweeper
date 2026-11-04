package com.minesweeper.util;

import com.minesweeper.model.Board;
import com.minesweeper.model.Cell;

import java.util.ArrayList;

public class BoardUtils {

    public record CellPosition(int row, int col) {}

    public static ArrayList<CellPosition> getNeighbourPositions(Board board, int row, int col) {
        ArrayList<CellPosition> neighbourPositions = new ArrayList<>();

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                // skip the cell itself
                if (rowOffset == 0 && colOffset == 0) {
                    continue;
                }

                int neighbourRow = row + rowOffset;
                int neighbourCol = col + colOffset;

                if (isInsideBoard(board, neighbourRow, neighbourCol)) {
                    neighbourPositions.add(new CellPosition(neighbourRow, neighbourCol));
                }
            }
        }

        return neighbourPositions;
    }

    public static ArrayList<Cell> getNeighbourCells(Board board, int row, int col) {
        ArrayList<Cell> neighbourCells = new ArrayList<>();

        for (CellPosition position : getNeighbourPositions(board, row, col)) {
            neighbourCells.add(board.getCell(position.row(), position.col()));
        }

        return neighbourCells;
    }

    public static boolean isInsideBoard(Board board, int row, int column) {
        return row >= 0 && row < board.getRows() && column >= 0 && column < board.getCols();
    }
}
