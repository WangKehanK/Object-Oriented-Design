package board.impl;

import board.AbstractBoard;

/**
 * OrderAndChaos
 */
public class OrderAndChaosBoard extends AbstractBoard {

    public OrderAndChaosBoard() {
        this.colNum = 6;
        this.rowNum = 6;
        this.board = new String[rowNum][colNum];
    }
}
