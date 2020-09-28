package board.impl;

import board.AbstractBoard;

/**
 * TicTacToe class that extends AbstractBoard
 */
public class TicTacToeBoard extends AbstractBoard {
    private int size;

    public TicTacToeBoard(int size) {
        this(size, size);
    }

    public TicTacToeBoard(int rowNum, int colNum) {
        this.size = rowNum;
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.board = new String[rowNum][colNum];
    }

    public int getSize() {
        return size;
    }
}
