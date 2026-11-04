package com.minesweeper.engine;

import com.minesweeper.model.Board;
import com.minesweeper.model.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MineGenerator {
    private final Random random = new Random();

    public void generateMines(Board board, int safeRow, int safeCol) {
        ArrayList<CellPosition> positions = new ArrayList<CellPosition>();

        // find all cells that are safe for mine placement
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                // the cell can be added if it is not adjacent to the safe row and col
                if (Math.abs(row-safeRow) > 1 || Math.abs(col-safeCol) > 1) {
                    positions.add(new CellPosition(row, col));
                }
            }
        }

        // shuffle the available positions
        Collections.shuffle(positions, random);

        // place the required number of mines
        for (int i = 0; i<board.getMineCount(); i++) {
            CellPosition position = positions.get(i);
            board.getCell(position.row(), position.col()).setMine(true);
        }

        calculateAdjacentMines(board);
    }

    private void calculateAdjacentMines(Board board) {
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                Cell cell = board.getCell(row, col);

                if (!cell.isMine()) {
                    cell.setAdjacentMines(countAdjacentMines(board, row, col));
                }
            }
        }
    }

    private int countAdjacentMines(Board board, int row, int col) {
        int mineCount = 0;

        for (int rowOffset = -1;  rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1;  colOffset <= 1; colOffset++) {
                // skip the cell itself
                if (rowOffset == 0 && colOffset == 0) {
                    continue;
                }

                int neighbourRow = row + rowOffset;
                int neighbourCol = col + colOffset;

                if (isInsideBoard(board,  neighbourRow, neighbourCol)) {
                    if (board.getCell(neighbourRow, neighbourCol).isMine()) {
                        mineCount++;
                    }
                }
            }
        }

        return mineCount;
    }

    private boolean isInsideBoard(Board board, int row, int column) {
       return row >= 0 && row < board.getRows() && column >= 0 && column < board.getCols();
    }

    private record CellPosition(int row, int col) {}
}
