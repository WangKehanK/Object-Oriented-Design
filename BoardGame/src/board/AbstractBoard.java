package board;

/**
 AbstractBoard - The abstract board class implements the Board interface，and here contains abstract methods for base board, and this can be extended for any kind of board game easily,
 */
public abstract class AbstractBoard implements Board{
    protected int rowNum;
    protected int colNum;

    protected String[][] board;
    protected int pieceNum;

    @Override
    public void setBoard(int row, int col, String s) {
        this.board[row][col] = s;
        pieceNum++;
    }

    @Override
    public void setBoard(int number, String s) {
        int row = number / rowNum;
        int col = number % colNum;
        setBoard(row, col, s);
    }


    @Override
    public String getBoard(int rowNum, int colNum) {
        return this.board[rowNum][colNum];
    }

    @Override
    public String getBoard(int number) {
        int row = number / rowNum;
        int col = number % colNum;
        return this.board[row][col];
    }

    @Override
    public void printBoard() {
        String separatorLine = "";
        for (int i = 0; i < rowNum; i++) {
            String line = "";
            separatorLine = "";
            for (int j = 0; j < colNum; j++) {
                separatorLine += "+------";
                line += "|";
                if (board[i][j] != null && board[i][j].equals("")) {
                    line += "      ";
                    line += board[i][j];
                } else {
                    line += "      ";
                }
            }
            separatorLine += "+";
            line += "|";
            System.out.println(separatorLine);
            System.out.println(line);
        }
        System.out.println(separatorLine);
    }


    @Override
    public void printBoardNumber() {
        String separatorLine = "";
        for (int i = 0; i < rowNum; i++) {
            String line = "";
            separatorLine = "";
            for (int j = 0; j < colNum; j++) {
                separatorLine += "+------";
                line += "|";
                if (board[i][j] != null && !board[i][j].equals("")) {
                    line += "     ";
                    line += board[i][j];
                } else {
                    int number = i * rowNum + j + 1;
                    line += String.format("%6d", number);
                }
            }
            separatorLine += "+";
            line += "|";
            System.out.println(separatorLine);
            System.out.println(line);
        }
        System.out.println(separatorLine);
    }

    @Override
    public int getCellNumber(){
        return rowNum * colNum;
    }

    @Override
    public boolean isFull(){
        return this.pieceNum == getCellNumber();
    }

    public int checkDirection(int row, int col, int rowDirection, int colDirection, String s) {
        int difference = 1;
        int count = 1;
        while (true) {
            int newRow = row + rowDirection * difference;
            int newCol = col + colDirection * difference;
            if (newRow >= 0 && newRow < rowNum && newCol >= 0 && newCol < colNum) {
                if (s.equalsIgnoreCase(board[newRow][newCol])) {
                    count++;
                    difference++;
                } else {
                    break;
                }
            }else{
                break;
            }
        }
        difference = -1;
        while (true) {
            int newRow = row + rowDirection * difference;
            int newCol = col + colDirection * difference;
            if (newRow >= 0 && newRow < rowNum && newCol >= 0 && newCol < colNum) {
                if (s.equalsIgnoreCase(board[newRow][newCol])) {
                    count++;
                    difference--;
                } else {
                    break;
                }
            }else{
                break;
            }
        }
        return count;
    }

    public int checkDirection(int lastPutNumber, int rowDirection, int colDirection, String lastPiece) {
        int row = lastPutNumber / rowNum;
        int col = lastPutNumber % colNum;

        return checkDirection(row, col, rowDirection, colDirection, lastPiece);
    }
}
