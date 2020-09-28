package board.impl;

import board.AbstractBoard;

/**
 * OrderAndChaos 棋盘的特殊设置
 */
public class OrderAndChaosBoard extends AbstractBoard {

    public OrderAndChaosBoard() {
        this.colNum = 6;
        this.rowNum = 6;
        this.board = new String[rowNum][colNum];
    }
}
