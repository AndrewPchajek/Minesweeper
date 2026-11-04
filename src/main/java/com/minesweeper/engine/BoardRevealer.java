package com.minesweeper.engine;

import com.minesweeper.model.Board;
import com.minesweeper.model.Cell;
import com.minesweeper.util.BoardUtils.CellPosition;
import com.minesweeper.util.BoardUtils;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.minesweeper.util.BoardUtils.isInsideBoard;

public class BoardRevealer {
    public void revealCell(Board board, int row, int col) {
        if (!isInsideBoard(board, row, col)) {
            return;
        }

        Cell cell = board.getCell(row, col);

        // don't allow player to reveal flagged cells and don't recompute revealed cells
        if (cell.isFlagged() || cell.isRevealed()) {
            return;
        }

        cell.setRevealed(true);

        if (cell.getAdjacentMines() == 0) {
            revealAdjacentCells(board, row, col);
        }
    }

    private void revealAdjacentCells(Board board, int row, int col) {
        Queue<CellPosition> queue = new ArrayDeque<>();

        queue.add(new CellPosition(row, col));

        while (!queue.isEmpty()) {
            CellPosition position = queue.poll();

            for (CellPosition neighbourPosition : BoardUtils.getNeighbourPositions(board, position.row(), position.col())) {
                Cell neighbour = board.getCell(neighbourPosition.row(), neighbourPosition.col());

                if (neighbour.isFlagged() || neighbour.isRevealed() || neighbour.isMine()) {
                    continue;
                }

                neighbour.setRevealed(true);

                if (neighbour.getAdjacentMines() == 0) {
                    queue.add(neighbourPosition);
                }
            }
        }
    }
}
