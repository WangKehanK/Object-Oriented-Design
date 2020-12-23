# Object Oriented Design: Board Game
Name: Kehan Wang

Email: wangkk@bu.edu

## 1. Introduction
- For TicTacToe, I assume my board is always NxN(smallest size if 3x3), and the game rule will be changed as the board size changes, e.g in order to win a game on a 3x3 board game, you have to place three of marks in a horizontal, vertical, or diagonal row; in order to win a game on a 4x4 board game, you have to place four of marks in a horizontal, vertical, or diagonal row...
- For Order and Chaos, I assume team Order will win as long as they can have five of same type marks in a horizontal, vertical, or diagonal row.
- (bonus) For both games, I allow each team to choose the total number of teammates they can have on their own. e.g You can choose to play a single vs single game, or you can have 10 people in your team. The system will randomly make a decision on who will placing the mark.
- For both games, each cell of the board will be printed with a number, after you can enter the corresponding number to place the mark at the desired position, the number will be replaced by the corresponding mark. 
- For both games, I've implemented to handle most of special cases. For example, when you enter 10 to a 3x3 Board or enter "a" to a team size or you enter a "" string, the system will remind you. Also, I assume the player will always enter the number of corresponding cell to place the mark, e.g for TicTacToe, when you enter 1 for team 1, the position 1 will be filled by X; and when you enter 2 for team 2, the position 2 will be filled by O. If not, the system will remind you.
- For both games, when the team size is greater than 1, I assume the player will not enter the duplicated name; I also assume the user will never input any empty contents.


## 2. Structure 

If self-defined package can be used, the structure will be
```
├─board
│  ├─Impl
│  |   ├─OrderAndChaosBoard - OrderAndChaos class that extends AbstractBoard, with specific game setting - the board size are always 6*6
│  |   └─TicTacToeBoard - TicTacToe class that extends AbstractBoard, with specific game setting - the board size can be modified
│  ├─AbstractBoard - The abstract board class implements the Board interface，and here contains abstract methods for base board, and this can be extended for any kind of board game easily,
│  └─Board - An interface includes the method that check board status(the position of marks...) and basic operations for setting up the board 
├─game
│  ├─Impl
│  |   ├─OrderAndChaos - extends AbstractBoardGame, with specific game setting
│  |   └─TicTacToe - extends AbstractBoardGame, with specific game setting
│  ├─AbstractBoardGame - The abstract board game class implements the basic game interface and winnable interface. Here contains the basic attributes and basic operations of a board game, and this can be extended for any kind of board game easily,
│  ├─Winnable - Winnable Interface including functions that check the game status, like whether the game is winnable...
│  └─Game - Game Interface, including the basic functions for starting game.
├─player
│  └─Player - A class representing a board game player, it provides support to be extended so the basic player information and condition, including the number of win, tie, or lose games can be fit to different player.
├─util
│  ├─InputUtil - A class used to obtain the data input by the user
│  └─NumberUtil - A class that providing support to handle numeric input
├─GameType - An enumeration type, provide corresponding game iterface options
├─GameFactory - A class that can generate the corresponding game according to the parameters passed in.
└─Main - is the main entrance of the game

```
If package can not be used, the structure will be
```
# board
│─OrderAndChaosBoard - OrderAndChaos class that extends AbstractBoard, with specific game setting - the board size are always 6*6
│─TicTacToeBoard - TicTacToe class that extends AbstractBoard, with specific game setting - the board size can be modified
│─AbstractBoard - The abstract board class implements the Board interface，and here contains abstract methods for base board, and this can be extended for any kind of board game easily,
│─Board - An interface includes the method that check board status(the position of marks...) and basic operations for setting up the board 
# game
│─OrderAndChaos - extends AbstractBoardGame, with specific game setting
│─TicTacToe - extends AbstractBoardGame, with specific game setting
│─AbstractBoardGame - The abstract board game class implements the basic game interface and winnable interface. Here contains the basic attributes and basic operations of a board game, and this can be extended for any kind of board game easily,
│─Winnable - Winnable Interface including functions that check the game status, like whether the game is winnable...
│─Game - Game Interface, including the basic functions for starting game.
│─Player - A class representing a board game player, it provides support to be extended so the basic player information and condition, including the number of win, tie, or lose games can be fit to different player.
# util
│─InputUtil - A class used to obtain the data input by the user
│─NumberUtil - A class that providing support to handle numeric input
# game list
├─GameType - An enumeration type, provide corresponding game iterface options
├─GameFactory - A class that can generate the corresponding game according to the parameters passed in.
└─Main - is the main entrance of the game

```
## 3. Get Start
First you need to compile the program
```
cd ./BoardGame/src

javac Main.java
```
Then run
```
java Main
```
After running the program, it will print out a game list. (Enter 1 to play TicTacToe, 2 to play Order and Chaos, 3 to exit)
```
1: TicTacToe;
2: OrderAndChaos;
3: Exit;
```

### 3.1 TicTacToe
Let's see a 1 vs 1 game example, you can enter 2(n) to play a 2 vs 2(n vs n) game
```
Please enter the team size: 
> 1 
please input team 1 player No.1 name:
> Ken
please input team 2 player No.1 name:
> Kevin
```
Now, our player is ready.
```
# Let's see a 3*3 board game
Please input board size:
> 3

Welcome to Tic Tac Toe.
Always enter the number of corresponding cell to place the mark. 

--------------------------------
+------+------+------+
|     1|     2|     3|
+------+------+------+
|     4|     5|     6|
+------+------+------+
|     7|     8|     9|
+------+------+------+
please Team 1 Player No.1 Kehan put piece:
> 1 
+------+------+------+
|     X|     2|     3|
+------+------+------+
|     4|     5|     6|
+------+------+------+
|     7|     8|     9|
+------+------+------+
please Team 2 Player No.1 Kevin put piece:
> 2
+------+------+------+
|     X|     O|     3|
+------+------+------+
|     4|     5|     6|
+------+------+------+
|     7|     8|     9|
+------+------+------+
please Team 1 Player No.1 Kehan put piece:
...
```
As you can observe above, when you enter 1 for team 1, the position 1 will be filled by X; and when you enter 2, the position 2 will be filled by O.


OK! Let's see an example game that team 1 wins. 
```
+------+------+------+
|     X|     O|     3|
+------+------+------+
|     X|     O|     6|
+------+------+------+
|     X|     8|     9|
+------+------+------+
Congratulations! Team 1 won the game.
---------------------------
continue?(Y/N)
```

```
# When there is a tied game, the program will print "This game is peace game."
+------+------+------+
|     X|     O|     X|
+------+------+------+
|     X|     X|     O|
+------+------+------+
|     O|     X|     O|
+------+------+------+
This game is peace game.
---------------------------
continue?(Y/N)
```
The program will ask whether you want to continue. If you enter Y, the system will ask the player about the board size again. If you enter N, the system will bring you back to game list, and print the number of each team wins. Note that, the summary will be initialized once you back to the main game list.
```
#If you enter Y
>Y
Please input board size:

# OR N
> N
-------Team 1---------
Player Kehan team 1 personNo.1: game Number = 1, victory Number = 1
-------Team 2---------
Player Kevin team 2 personNo.1: game Number = 1, victory Number = 0
1: TicTacToe;
2: OrderAndChaos;
3: Exit;
```
### 3.2 Order and Chaos
```
# Assume we entered 2 to play Order And Chaos
Please input teammate number:
>1
please input team Order player No.1 name:
>Ken
please input team Chaos player No.1 name:
>Kevin

```

```
Welcome to Order And Chaos.

--------------------------------
+------+------+------+------+------+------+
|     1|     2|     3|     4|     5|     6|
+------+------+------+------+------+------+
|     7|     8|     9|    10|    11|    12|
+------+------+------+------+------+------+
|    13|    14|    15|    16|    17|    18|
+------+------+------+------+------+------+
|    19|    20|    21|    22|    23|    24|
+------+------+------+------+------+------+
|    25|    26|    27|    28|    29|    30|
+------+------+------+------+------+------+
|    31|    32|    33|    34|    35|    36|
+------+------+------+------+------+------+
please Team Order Player No.1 Ken choice piece type:
X
please Team Order Player No.1 Ken put piece:
1
+------+------+------+------+------+------+
|     X|     2|     3|     4|     5|     6|
+------+------+------+------+------+------+
|     7|     8|     9|    10|    11|    12|
+------+------+------+------+------+------+
|    13|    14|    15|    16|    17|    18|
+------+------+------+------+------+------+
|    19|    20|    21|    22|    23|    24|
+------+------+------+------+------+------+
|    25|    26|    27|    28|    29|    30|
+------+------+------+------+------+------+
|    31|    32|    33|    34|    35|    36|
+------+------+------+------+------+------+
please Team Chaos Player No.1 Kevin choice piece type:
O
please Team Chaos Player No.1 Kevin put piece:
2
+------+------+------+------+------+------+
|     X|     O|     3|     4|     5|     6|
+------+------+------+------+------+------+
|     7|     8|     9|    10|    11|    12|
+------+------+------+------+------+------+
|    13|    14|    15|    16|    17|    18|
+------+------+------+------+------+------+
|    19|    20|    21|    22|    23|    24|
+------+------+------+------+------+------+
|    25|    26|    27|    28|    29|    30|
+------+------+------+------+------+------+
|    31|    32|    33|    34|    35|    36|
+------+------+------+------+------+------+
......
```
Same as TicTacToe, the program will record who is the winner at the end of game, and ask if you want to continue.
## 4. Requirement
```
JDK1.8
```


