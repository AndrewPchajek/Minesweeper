package com.minesweeper.engine;

import com.minesweeper.config.GameDifficulty;
import com.minesweeper.model.Board;
import com.minesweeper.model.Cell;
import com.minesweeper.model.Game;
import com.minesweeper.model.GameState;

public class GameEngine {
    private Game game;

    private final MineGenerator mineGenerator;
    private final BoardRevealer boardRevealer;
    private final FlagManager flagManager;
    private final GameCompletionChecker gameCompletionChecker;

    public GameEngine(GameDifficulty difficulty) {
       this.game = new Game(difficulty);

       this.mineGenerator = new MineGenerator();
       this.boardRevealer = new BoardRevealer();
       this.flagManager = new FlagManager();
       this.gameCompletionChecker = new GameCompletionChecker();
    }

    public Game getGame() {
        return this.game;
    }

    public void revealCell(int row, int col) {
        Board board = game.getBoard();

        // ignore if game is over
        if (game.getState() == GameState.WON || game.getState() == GameState.LOST) {
            return;
        }

        // generate the mines on first click
        if (game.getState() == GameState.READY) {
            mineGenerator.generateMines(board, row, col);
            game.setState(GameState.RUNNING);
        }

        boardRevealer.revealCell(board, row, col);

        // check for loss
        Cell cell = board.getCell(row, col);
        if (cell.isMine() && cell.isRevealed()) {
            game.setState(GameState.LOST);
            return;
        }

        // check for win
        if (gameCompletionChecker.checkWin(game)) {
            game.setState(GameState.WON);
        }
    }

    public void toggleFlag(int row, int col) {
        Board board = game.getBoard();

        // ignore if game is over
        if (game.getState() == GameState.WON || game.getState() == GameState.LOST) {
            return;
        }

        flagManager.toggleFlag(board, row, col);
    }

    public void resetGame(GameDifficulty difficulty) {
        this.game = new Game(difficulty);
    }

    public void resetGame() {
        this.game = new Game(this.game.getDifficulty());
    }
}
