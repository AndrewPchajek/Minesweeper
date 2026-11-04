package com.minesweeper.engine;

import com.minesweeper.model.Board;
import com.minesweeper.model.Cell;
import com.minesweeper.model.Game;
import com.minesweeper.model.GameState;

public class GameCompletionChecker {
    public boolean checkWin(Game game) {
        Board board = game.getBoard();

        if (game.getState() != GameState.RUNNING) {
            return false;
        }

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                Cell cell = board.getCell(row, col);

                // if a non-mine is not revealed, game has not yet been won
                if (!cell.isMine() && !cell.isRevealed()) {
                    return false;
                }
            }
        }

        game.setState(GameState.WON);
        return true;
    }
}
