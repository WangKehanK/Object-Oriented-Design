package board;

/**
 Board - An interface includes the method that check board status(the position of marks...) and basic operations for setting up the board
 */
public interface Board {
    void setBoard(int rowNum, int colNum, String s);
    void setBoard(int number, String s);

    String getBoard(int rowNum, int colNum);
    String getBoard(int number);

    int checkDirection(int row, int col, int rowDirection, int colDirection, String lastPiece);
    int checkDirection(int putNumber, int rowDirection, int colDirection, String lastPiece);

    void printBoard();
    void printBoardNumber();

    int getCellNumber();

    boolean isFull();
}
