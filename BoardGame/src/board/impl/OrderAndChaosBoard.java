package board.impl;

import board.AbstractBoard;

/**
 OrderAndChaosBoard - OrderAndChaos class that extends AbstractBoard, with specific game setting - the board size are always 6*6
 */
public class OrderAndChaosBoard extends AbstractBoard {

    public OrderAndChaosBoard() {
        this.colNum = 6;
        this.rowNum = 6;
        this.board = new String[rowNum][colNum];
    }
}
