package com.minesweeper.model;

import com.minesweeper.config.GameDifficulty;

public class Game {
    private final GameDifficulty difficulty;
    private final Board board;
    private GameState state;

    public Game(GameDifficulty difficulty) {
        this.difficulty = difficulty;
        this.board = new Board(difficulty.getRows(), difficulty.getCols(), difficulty.getMines());
        this.state = GameState.READY;
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
}
