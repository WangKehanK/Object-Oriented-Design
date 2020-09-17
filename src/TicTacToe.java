import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicTacToe {
    static boolean isEnd = true;
    static int numberOfPlayer1Win = 0;
    static int numberOfPlayer2Win = 0;
    static List<Integer> player1PositionsList = new ArrayList<Integer>();
    static List<Integer> player2PositionsList = new ArrayList<Integer>();

    public static void main(String[] args) {
        System.out.println("Welcome to 3x3 Tic Tac Toe Game");
        char player = choosePlayer();
        char player2 = findOpponent(player);
        char[][] board = createBoard();
        printBoard(board);
        while (isEnd){
            round(board, player); //player1's round
            printBoard(board);
            //check winning status
            if (!isEnd){
                Scanner gameScan = new Scanner(System.in);
                System.out.print("enter Y/y to play again, or enter any other letter to get the summary and quit the game");
                char isPlayAgain = gameScan.next().charAt(0);
                System.out.println(isPlayAgain);
                if(isPlayAgain == 'y' || isPlayAgain == 'Y'){
                    board = createBoard();
                    clearBoard();
                    continue;
                }
                else{
                    summary();
                    break;
                }
            }
            round(board, player2);//player2's round
            printBoard(board);
            //check winning status
            if (!isEnd){
                Scanner gameScan = new Scanner(System.in);
                System.out.print("enter Y/y to play again, or enter any other letter to get the summary and quit the game");
                char isPlayAgain = gameScan.next().charAt(0);
                System.out.println(isPlayAgain);
                if(isPlayAgain == 'y' || isPlayAgain == 'Y'){
                    board = createBoard();
                    clearBoard();
                    continue;
                }
                else{
                    summary();
                    break;
                }
            }
        }
    }

    public static void summary(){
        System.out.println("Tic-Tac-Toe Summaries :");
        System.out.println("player X wins " + numberOfPlayer1Win + " times");
        System.out.println("player O wins " + numberOfPlayer2Win + " times");
    }

    // conclude everything need for each step
    public static void round(char[][] board, char player){
        boolean continueOrNot = false;
        int position1, position2;
        String inputLine;
        String[] lineVector;
        // player 1
        Scanner scan = new Scanner(System.in);
        System.out.print("Player " + player + " Enter your move(e.g: 1,2) : ");
        inputLine = scan.nextLine(); // read 1, 2
        lineVector = inputLine.split(",");
        //parsing the values to Integer
        position1=Integer.parseInt(lineVector[0]);
        position2=Integer.parseInt(lineVector[1]);
        int position = 0;
        position = findPosition(position1, position2);
        move(board, player, position);
        check(player);
    }

    // check wining status
    public static void check(char player){
        List<List<Integer>> winningCase = new ArrayList<>();
        // horizontal, vertical, diagonal
        List<Integer> row0 = Arrays.asList(1,2,3);
        List<Integer> row1 = Arrays.asList(4,5,6);
        List<Integer> row2 = Arrays.asList(7,8,9);
        List<Integer> col0 = Arrays.asList(1,4,7);
        List<Integer> col1 = Arrays.asList(2,5,8);
        List<Integer> col2 = Arrays.asList(3,6,9);
        List<Integer> diagonal0 = Arrays.asList(1,5,9);
        List<Integer> diagonal1 = Arrays.asList(3,5,7);

        // add into all winning cases
        winningCase.add(row0);
        winningCase.add(row1);
        winningCase.add(row2);
        winningCase.add(col0);
        winningCase.add(col1);
        winningCase.add(col2);
        winningCase.add(diagonal0);
        winningCase.add(diagonal1);
        //base case, tied
        if(player1PositionsList.size() + player2PositionsList.size() == 9){
            isEnd = false;
            System.out.println("player tie");
//            return isEnd;
        }
        //use a for loop to check condition;
        for(List<Integer> part : winningCase){
            if(player1PositionsList.containsAll(part)){
                isEnd = false;
                numberOfPlayer1Win++;
                System.out.println("Congraulation! player" + player + "win this round");
//                return isEnd;
            }else if(player2PositionsList.containsAll(part)){
                isEnd = false;
                numberOfPlayer2Win++;
                System.out.println("Congraulation! player2" + player + "win this round");
//                return isEnd;
            }
        }

        System.out.println("");
//        return isEnd;
    }



    // move
    public static void move(char[][] board, char player, int position){
        char whichPlayer = 'X';
        if(player == 'X') {
            whichPlayer = 'X';
            player1PositionsList.add(position);
        } else if(player == 'O') {
            whichPlayer = 'O';
            player2PositionsList.add(position);
        }
        switch(position) {
            case 1:
                board[1][1] = whichPlayer;
                break;
            case 2:
                board[1][4] = whichPlayer;
                break;
            case 3:
                board[1][7] = whichPlayer;
                break;
            case 4:
                board[3][1] = whichPlayer;
                break;
            case 5:
                board[3][4] = whichPlayer;
                break;
            case 6:
                board[3][7] = whichPlayer;
                break;
            case 7:
                board[5][1] = whichPlayer;
                break;
            case 8:
                board[5][4] = whichPlayer;
                break;
            case 9:
                board[5][7] = whichPlayer;
                break;
        }
    }

    // convert the coordinate to position
    public static int findPosition(int posX, int posY){
        int position = 0;
        switch (posX){
            case 1:
                if (posY==1){
                    position = 1;
                    break;};
                if (posY==2){
                    position = 2;
                    break;};
                if (posY==3){
                    position = 3;
                    break;};
            case 2:
                if (posY==1){
                    position = 4;
                    break;};
                if (posY==2){
                    position = 5;
                    break;};
                if (posY==3){
                    position = 6;
                    break;};
            case 3:
                if (posY==1){
                    position = 7;
                    break;};
                if (posY==2){
                    position = 8;
                    break;};
                if (posY==3){
                    position = 9;
                    break;};
        }
        return position;
    }

    /////////////////////////////////////////////////
    //  player
    public static char choosePlayer(){
        char playerChar = 'X'; //default
        Scanner playerScan = new Scanner(System.in);
        System.out.print("Choose the player X(0) or O(1):");
        int player = playerScan.nextInt();
        switch(player){
            case 0:
                playerChar = 'X';
                break;
            case 1:
                playerChar = 'O';
                break;
        }
        return playerChar;
    }

    // player
    public static char findOpponent(char player){
        char oppnent = 'X'; //default;
        if (player == 'X'){
            oppnent = 'O';
        } else {
            oppnent = 'X';
        }
        return oppnent;
    }
    ///////////////////////////////////////
    //////////////////////////////////////


    // Board
    public static char[][] createBoard(){
        char[][] board = {
                {'+', '-', '-', '+', '-', '-', '+', '-', '-', '+'},
                {'|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|'},
                {'+', '-', '-', '+', '-', '-', '+', '-', '-', '+'},
                {'|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|'},
                {'+', '-', '-', '+', '-', '-', '+', '-', '-', '+'},
                {'|', ' ', ' ', '|', ' ', ' ', '|', ' ', ' ', '|'},
                {'+', '-', '-', '+', '-', '-', '+', '-', '-', '+'},
        };
        return board;
    }

    // Board
    public static void clearBoard(){
        player1PositionsList.clear();
        player2PositionsList.clear();
        isEnd = true;
    }

    // Board
    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}