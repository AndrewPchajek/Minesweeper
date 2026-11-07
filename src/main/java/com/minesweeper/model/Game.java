package com.minesweeper.model;

import com.minesweeper.config.GameDifficulty;

public class Game {
    private final GameDifficulty difficulty;
    private final Board board;
    private GameState state;

    private int explodedRow;
    private int explodedCol;

    public Game(GameDifficulty difficulty) {
        this.difficulty = difficulty;
        this.board = new Board(difficulty.getRows(), difficulty.getCols(), difficulty.getMines());
        this.state = GameState.READY;

        explodedRow = -1;
        explodedCol = -1;
    }

    public GameDifficulty getDifficulty() {
        return this.difficulty;
    }

    public Board getBoard() {
        return this.board;
    }

    public GameState getState() {
        return this.state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void setExplodedCell(int row, int col) {
        this.explodedRow = row;
        this.explodedCol = col;
    }

    public boolean isExplodedCell(int row, int col) {
        return explodedRow == row && explodedCol == col;
    }
}
