package com.minesweeper.model;

public class Board {
    private final int rows;
    private final int cols;
    private final int mineCount;
    private final Cell[][] cells;

    public Board(int rows, int cols, int mineCount) {
        this.rows = rows;
        this.cols = cols;
        this.mineCount = mineCount;
        this.cells = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.cells[row][col] = new Cell();
            }
        }
    }

    public Cell getCell(int row, int col) {
        return this.cells[row][col];
    }

    public int getRows() {
        return this.rows;
    }
    public int getCols() {
        return this.cols;
    }

    public int getMineCount() {
        return this.mineCount;
    }
}
