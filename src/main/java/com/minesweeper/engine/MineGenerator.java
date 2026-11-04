package com.minesweeper.engine;

import com.minesweeper.model.Board;
import com.minesweeper.model.Cell;
import com.minesweeper.util.BoardUtils;
import com.minesweeper.util.BoardUtils.CellPosition;

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

                if (cell.isMine()) {
                    continue;
                }

                int adjacentMines = 0;

                for (Cell neighbour : BoardUtils.getNeighbourCells(board, row, col)) {
                    if (neighbour.isMine()) {
                        adjacentMines++;
                    }
                }

                cell.setAdjacentMines(adjacentMines);
            }
        }
    }
}
