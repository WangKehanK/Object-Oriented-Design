# Object Oriented Design: Black Jack

Name: Kehan Wang

Email: wangkk@bu.edu

## 1. Introduction

- The game provide user two options: single-player mode or multi-player mode. You can choose which mode to play at the beginning. If you choose multi-player mode, the program will ask user the number of players.
- To be fair, my implementation assumes the deck will be re-shuffled at the end of each bet.
- My implementation does not ask user how many decks they want to play with, but in the "BlackJackGame.java" line 41, developer can modify it very easily
    ```
    # 2 mean 2 decks, you can change it to 1 (1 deck), 3 (3 deck)...
    deck = new MultipleDeck(2);
    ```
- In multi-player mode, assume there are 2 players in total, the game will not be over if one player left. The game will only be ended when all player left. Also, in each round, the result will only be shown when all player finish their bet.
- If player get black jack at first round, the player can win 1.5x
## 2. Structure

```
├─game
│  ├─Game - A game interface, can be implemented into any kinds of games
│  ├─BlackJackGame - A black jack game class, implement Game interface, combine deck, hand, balance, player class to create black jack game, allow multiple players in one game by modify playerNumber parameter
├─deck - Given options to play with/without jokers, multi-decks, this can be fit into all kind of poker games
│  ├─SimpleDeck - A public simple deck class, exclude Jokers
│  ├─AllDeck - A public deck class, with Jokers
│  ├─MultipleDeck - A public multi-deck class, allow game can be started with multiple decks
│  ├─Deck - A public deck class, represent decks of poker card, provide features like shuffled...
├─card - Can be fit into all kind of poker games
│  ├─CardValueType - An enum type that provides all the value types to poker game, from A to K
│  ├─CardSuitType - An enum type that provides 4 suit types to poker game, Spade, Heart, and etc.
│  ├─Card - A public class use CardValue Type and CardSuitTyp, can be use to build a deck of poker
├─hand - Simple modify the game logic, can be fit into any card games
│  ├─hand - A hand class, record what card does a player have in their hand, calculate the total score of card, handle A card(can be 1 or 11)
├─balance - Can be fit into all kind of gambling games
│  ├─balance - A balance class, record the amount of money in player's pocket, can be reduced when lose the game or added when win.
├─player - Simple modify the game logic, can be fit into any card games
│  ├─BlackJackDealer - A black jack dealer class extends the Abstract Player class, implement Dealer interface, contains the dealer logic, e.g keep drawing card when the score is less than 17
│  ├─Dealer - A dealer interface, can be implemented into any kinds of dealer
│  ├─BlackJackPlayer - A black jack player class extends the Abstract Player class, contains functions that help printing bet, print cards, ask user whether continue, give game options(stand, hit)
│  ├─AbstractBlackJackPlayer - A abstract player class implement player interface, use Hand, Balance class to build the basic logic, like check the game status of player
│  └─Player - A player interface, can be implemented into any kinds of players
├─util
│  ├─InputUtil - A class used to obtain the data input by the user
│  └─NumberUtil - A class that providing support to handle numeric input
├─GameType - An enumeration type, provide corresponding game iterface options
├─GameFactory - A class that can generate the corresponding game according to the parameters passed in.
└─Main - is the main entrance of the game
```

## 3. Get Start
First you need to compile the program
```
cd ./src
javac Main.java
```
Then run
```
java Main
```
After running the program, it will print out a game list. (Enter 1 to play single-player mode, 2 to play multi-player mode, 3 to exit)
```
1: Black Jack Single-Player Mode;
2: Black Jack Multi-Player Mode;
3: Exit;
```
### 3.1 Single-Player
```
1: Black Jack Single-Player Mode;
2: Black Jack Multi-Player Mode;
3: Exit;
> 1
```
Assume we enter 1
```
please input player No.1 name:
> Ken

Shuffling

Player Ken balance = 1000.0
please input Hand No.1 bet number.

# Assume I bet 200 in this round
> 200
```

After the player input the amount of bet, the program will show you dealer's card (one is hidden), and your card. You can decide to hit, stand, double(only first round), or split(when you have two same card)

```
Black Jack Dealer card :
[♣K，**]

Ken Hand No.1 card :
[◆K,◆4] Score = 14
Now bet = 200

[◆K,◆4] Score = 14
0 hit, 1 stand, 2 double
# Assume I decide to hit
> 0

# if you input 2(double), then the program will print the following
> 2
Now bet = 400
[♠8,♠5] Score = 13
0 hit, 1 stand, 2 double

# if you get two same cards, you will be given option 3: split. Enter 3, you will have two hands
[♠8,◆8] Score = 16
0 hit, 1 stand, 2 double, 3 split
```

```
# continue with hit
[◆K,◆4,♣Q] Score = 24

Black Jack Dealer card :
[♣K,♣6] Score = 16
Black Jack Dealer card :
[♣K,♣6,❤3] Score = 19
Ken lose Hand. balance - 200
Black Jack Dealer win Hand. balance + 200
Player Ken balance = 800.0
Player Ken Continue? (yes/no)
```
In the above case, the player's score exceeded 21, so he lost the game.
```
Player Ken Continue? (yes/no)
> yes

Shuffling

Player Ken balance = 800.0
please input Hand No.1 bet number.
> 800

Black Jack Dealer card :
[❤K，**]

Ken Hand No.1 card :
[♣4,◆Q] Score = 14
Now bet = 800

[♣4,◆Q] Score = 14
0 hit, 1 stand, 2 double
> 0
[♣4,◆Q,❤4] Score = 18
0 hit, 1 stand
> 1

[♣4,◆Q,❤4] Score = 18

Black Jack Dealer card :
[❤K,◆3] Score = 13
Black Jack Dealer card :
[❤K,◆3,♠2] Score = 15
hand is over.
[❤K,◆3,♠2,♠9] Score = 24
Black Jack Dealer card :
[❤K,◆3,♠2,♠9] Score = 24
Ken win Hand. balance + 800
Black Jack Dealer lose Hand. balance - 800
Player Ken balance = 1600.0
Player Ken Continue? (yes/no)

```
In the above case, player bet 800, and player's score was 18; at the same time, the dealer's score was exceeded 21, so dealer lost this round. The player's balance is 1600 now.
```
> yes

Shuffling

Player Ken balance = 1600.0
please input Hand No.1 bet number.
> 200
Black Jack Dealer card :
[❤9，**]
[◆Q,◆A] Score = 21
Black Jack！

Ken Hand No.1 card :
[◆Q,◆A] Score = 21
Now bet = 200

[◆Q,◆A] Score = 21

Black Jack Dealer card :
[❤9,♠6] Score = 15
Black Jack Dealer card :
[❤9,♠6,◆3] Score = 18
Ken win Hand. balance + 300.0
Black Jack Dealer lose Hand. balance - 300.0
Player Ken balance = 1900.0
Player Ken Continue? (yes/no)
```
In the above case, the player got black jack at first round, so I won 1.5x.
```
....
....
....

Ken lose Hand. balance - 3800
Ken Balance = 0. Game over
Black Jack Dealer win Hand. balance + 3800
GAME OVER!
Player:
Player Ken: Balance = 0.0,Game Number = 5,Win Number = 3
Dealer:
Black Jack Dealer: Balance = 1000.0

1: Black Jack Single-Player Mode;
2: Black Jack Multi-Player Mode;
3: Exit;
```
Here is a case that the player lost all the money, the program will print the total number of games, and how many games each player win. Then, the player will be brought back to initial page.

### 3.2 Multi-player mode
Assume there are two players
```
1: Black Jack Single-Player Mode;
2: Black Jack Multi-Player Mode;
3: Exit;
> 2
please input player number:
> 2
```

```
please input player No.1 name:
> Ken
please input player No.2 name:
> Kevin

Shuffling

Player Ken balance = 1000.0
please input Hand No.1 bet number.
> 200
Player Kevin balance = 1000.0
please input Hand No.1 bet number.
> 200
Black Jack Dealer card :
[♠2，**]


Ken Hand No.1 card :
[◆10,❤6] Score = 16
Now bet = 200

[◆10,❤6] Score = 16
0 hit, 1 stand, 2 double
> 1
[◆10,❤6] Score = 16

Kevin Hand No.1 card :
[♣Q,♣4] Score = 14
Now bet = 200

[♣Q,♣4] Score = 14
0 hit, 1 stand, 2 double
> 1
[♣Q,♣4] Score = 14

Black Jack Dealer card :
[♠2,♣J] Score = 12
Black Jack Dealer card :
[♠2,♣J,◆8] Score = 20
Ken lose Hand. balance - 200
Black Jack Dealer win Hand. balance + 200
Kevin lose Hand. balance - 200
Black Jack Dealer win Hand. balance + 200
Player Ken balance = 800.0
Player Ken Continue? (yes/no)
```
Here is an example run of multi-player mode, the program will first ask player to input their name. Then for each player, the players are required to input how much they want to bet respectively. The result will be shown when all player finish their bet.
The game will not be over if one player left. The game will only be ended when all player left.