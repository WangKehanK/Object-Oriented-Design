package board.impl;

import board.AbstractBoard;

/**
 TicTacToeBoard - TicTacToe class that extends AbstractBoard, with specific game setting - the board size can be modified
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
