package com.minesweeper.engine;

import com.minesweeper.model.Board;
import com.minesweeper.model.Cell;
import com.minesweeper.util.BoardUtils;

public class FlagManager {
    public void toggleFlag(Board board, int row, int col) {
        if (!BoardUtils.isInsideBoard(board, row, col)) {
            return;
        }

        Cell cell = board.getCell(row, col);

        if (!cell.isRevealed()) {
            cell.setFlagged(!cell.isFlagged());
        }
    }
}
