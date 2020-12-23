# Legends: Monsters and Heroes

Name: Kehan Wang

Email: wangkk@bu.edu

## 1. Introduction

- I followed logistics described in the requirement file. I've tried my best to make them right.
- In the game board, H means hero; M means market; % means unreachable cells; Rest of cells are common ones.
- Anytime you need to choose heroes, you should always enter the whole string (e.g Sehanine_Monnbow); Any interactions involve armor/potion/spell/weapon, you have to enter the whole string as well (e.g Sword); Any interations related to "show bag", "show information"..., you have to enter the abbreviation (e.g B/b for bag, I/i for Information, w/s/a/d for directions, Q/q for quit...).
- The game will never end unless you choose to quit game(Q/q), if your HP is 0, you can always go market to buy potion; and if you enter a battle when your HP is 0, monster will automatically win.
- Bonus: A colorful terminal experience: To make sure the color can be displayed, you can only use IDEs (like IntelliJ in windows) or Mac Terminal to run. ***The windows terminal will only mess up the program ( print a bunch of symbols and characters.)***
- Bonus: Input is getting parsed from the input files and is not harcoded: I allow user to modify the board length, and the percent of non-accessible cells, market cells and common cells in one single file - config.properties file. Currently, I have board length 8, 20% of unreachable cells, 30% of market cells, 95% frequency of starting a fight. ***It can be located either in ./HeroAndMonster directory or ./HeroAndMonster/src directory***

## 2. Stucture

### 2.1 Explanation

- config.properties - A config file that allow you to modify the board length, the percent of non-accessible cells, market cells and common cells, and the frequency of starting a fight. ***It can be located either in ./HeroAndMonster directory or ./HeroAndMonster/src directory***. If the file is broken/disappeared for some reason, I have also set a default value for each of variables.

- **Main.java** - Note that all below files/classes are in a **game** and **util** directory; Main is located at the same level of game and util directory, which is a main game entrance; To start the game you have to go to the directory where **Main.java** located first, then follow the instruction to run.

- ./game/**HeroAndMonsterGame.java** - A game class that combines all below classes and holds the main game logic

- ./game/**component**
  - entity - all classes related to items that can be buy, sell, use or equip in the game
    - Entity.java - A basic Entity(item) Interface
    - AbstractEntity.java - An abstract Entity class, that holds the name, cost, and the minimum level (request) of an entity (item).
    - Usable.java - An usbale interface, a class implement this interface means that item is usable, like potion.
    - Saleable.java - A saleable interface, a class implement this interface means that item is saleable, like weapon, armor, etc.
    - Equipable.java - A equipable interface, a class implement this interface means that item is equipable, like weapon, armor, etc.
    - Armor.java - A armor class extends AbstractEntity and implement Equipable and Saleable interface.
    - ArmorCache.java - A armor cache class use to cache all armor-type entities (items).
    - ArmorPrototype.java - A armor prototype class, use to create Armor when needed
    - Potion.java - A Potion class extends AbstractEntity and implement Usable and Saleable interface.
    - PotionCache.java - A potion cache class use to cache all potion-type entities (items).
    - PotionPrototype.java - A potion prototype class, use to create Potion when needed
    - Spell.java - A spell class extends AbstractEntity and implement Usable and Saleable interface.
    - SpellCache.java - A spell cache class use to cache all spell-type entities (items).
    - SpellPrototype.java - A spell prototype class, use to create Spell when needed
    - Weapon.java - A weapon class extends AbstractEntity and implement Equipable and Saleable interface.
    - WeaponCache.java - A weapon cache class use to cache all weapon-type entities (items).
    - WeaponPrototype.java - A weapon prototype class, use to create Weapon when needed

  - live - all character classes have hp, level... , that including heroes and monsters
    - hero - all classes related to hero.
      - Hero.java - A basic Hero interface extends Alive interface.
      - AbstractHero.java - An abstract Hero class that implement Hero and Combatable interface, including heroes' attributes and functions.
      - Paladin.java - A paladin hero class extends AbstractHero with specific favor.
      - Sorcerer.java - A sorcerer hero class extends AbstractHero with specific favor.
      - Warrior.java - A warrior hero class extends AbstractHero with specific favor.
      - HeroCache.java - A hero cache class use to cache all heroes.
      - HeroPrototype.java - A hero prototype class, use to create Hero when needed
    - monster - all classes related to monster.
      - Monster.java - A basic Monster interface extends Alive interface.
      - AbstractMonster.java - An abstract Monster class that implement Monster interface, including monsters' attributes and functions.
      - Dragon.java - A Dragon monster class extends AbstractHero with specific favor.
      - Exoskeleton.java -  A Exoskeleton monster class extends AbstractHero with specific favor.
      - Spirit.java - A Spirit monster class extends AbstractHero with specific favor.
      - MonsterCache.java - A monster cache class use to cache all monsters.
      - MonsterPrototype.java - A monster prototype class, use to create monster when needed

    - Bag.java - A bag class, hero's inventory, used to store armors, weapons, etc...
    - HeroTeam.java - A HeroTeam class implement movable interface that allow program can be played with multiple heroes in one team;
    - Combatable.java - A combatable interface, which means that character is combatable (e.g hero battle with monster)
    - Alive.java - An alive interface, which means that the character is alive (e.g hero has hp)
    - Movable.java - A movable interface, which means that the character is movable (e.g heroes)

- ./game/**world** - all classes are used to create the board (grid) of Legends: Monsters and heroes

  - cell - cell classes, heroes can move between cells, there are Common Cell, Market cell, unreachable cell, etc..
    - Cell.java - A simple cell interface
    - AbstractCell.java - An abstract cell class implements the cell interface
    - MarketCell.java - A market cell class that extends the abstract cell class
    - Reachable.java - A reachable cell interface
    - SimpleCell.java - A simple cell that extends the abstract cell class
    - UnreachableCell.java - A unreachable cell class that extend the abstract cell class.
  
  - arena - provide operations for cells
    - Arena.java - An Arena(Battle ground) Class, provide the battle ground related operations for heroes and monsters to fight
    - Market.java - A market Class, provide the market related operations for heros to buy/sell
  
  - Board - A game board (grid), build up by all kinds of cells.

- ./**util** - Some utils that make the implementation easier
  - ConfigUtil.java - A config util class, read config.properties
  - RandomUtil.java - A random util class, use to generate random number
  - ScannerUtil.java - A scanner util class, use to parse user input
  - SystemPrintUtil.java - A system print util class, print with categories
  
### 2.2 Displays the directory structure

```bash
└─src
    │  config.properties
    │  Main.java
    ├─game
    │  │  HeroAndMonsterGame.java
    │  │
    │  ├─component
    │  │  ├─entity
    │  │  │      AbstractEntity.java
    │  │  │      Armor.java
    │  │  │      ArmorCache.java
    │  │  │      ArmorPrototype.java
    │  │  │      Entity.java
    │  │  │      Equipable.java
    │  │  │      Potion.java
    │  │  │      PotionCache.java
    │  │  │      PotionPrototype.java
    │  │  │      Saleable.java
    │  │  │      Spell.java
    │  │  │      SpellCache.java
    │  │  │      SpellPrototype.java
    │  │  │      Usable.java
    │  │  │      Weapon.java
    │  │  │      WeaponCache.java
    │  │  │      WeaponPrototype.java
    │  │  │
    │  │  └─live
    │  │      │  Alive.java
    │  │      │  Bag.java
    │  │      │  Combatable.java
    │  │      │  HeroTeam.java
    │  │      │  Movable.java
    │  │      │
    │  │      ├─hero
    │  │      │      AbstractHero.java
    │  │      │      Hero.java
    │  │      │      HeroCache.java
    │  │      │      HeroPrototype.java
    │  │      │      Paladin.java
    │  │      │      Sorcerer.java
    │  │      │      Warrior.java
    │  │      │
    │  │      └─monster
    │  │              AbstractMonster.java
    │  │              Dragon.java
    │  │              Exoskeleton.java
    │  │              Monster.java
    │  │              MonsterCache.java
    │  │              MonsterPrototype.java
    │  │              Spirit.java
    │  │
    │  └─world
    │      │  Board.java
    │      │
    │      ├─arena
    │      │      Arena.java
    │      │      Market.java
    │      │
    │      └─cell
    │              AbstractCell.java
    │              Cell.java
    │              MarketCell.java
    │              Reachable.java
    │              SimpleCell.java
    │              UnreachableCell.java
    │
    └─utils
            ConfigUtil.java
            RandomUtil.java
            ScannerUtil.java
            SystemPrintUtil.java

```

## Get Start

First you need to compile the program

```bash
> cd ./HeroAndMonster/src
> javac Main.java
```

Then run

```bash
> java Main
```

After running the program, the program will ask you to input the number of heroes you want to play with; You can only enter 1~3.

```bash
# A single-hero game
Hero Number :
> 1
```

Then the program will print a list of heroes

***You have to enter the entire name here (e.g Caliber_Heist)***

```bash
Please Choose One Hero
**************************************
              hero
Hero Type:Warrior name: Gaerdal_Ironhand  mana:100   strength:700   agility:500 dexterity:600 startingMoney:1354   startingExperience:7
Hero Type:Warrior name: Sehanine_Monnbow  mana:600   strength:700   agility:800 dexterity:500 startingMoney:2500   startingExperience:8
Hero Type:Warrior name: Muamman_Duathall  mana:300   strength:900   agility:500 dexterity:750 startingMoney:2546   startingExperience:6
Hero Type:Warrior name: Flandal_Steelskin  mana:200   strength:750   agility:650 dexterity:700 startingMoney:2500   startingExperience:7
Hero Type:Warrior name: Undefeated_Yoj  mana:400   strength:800   agility:400 dexterity:700 startingMoney:2500   startingExperience:7
Hero Type:Warrior name: Eunoia_Cyn  mana:400   strength:700   agility:800 dexterity:600 startingMoney:2500   startingExperience:6
Hero Type:Sorcerer name: Rillifane_Rallathil  mana:1300   strength:750   agility:450 dexterity:500 startingMoney:2500   startingExperience:9
Hero Type:Sorcerer name: Segojan_Earthcaller  mana:900   strength:800   agility:500 dexterity:650 startingMoney:2500   startingExperience:5
Hero Type:Sorcerer name: Reign_Havoc  mana:800   strength:800   agility:800 dexterity:800 startingMoney:2500   startingExperience:8
Hero Type:Sorcerer name: Reverie_Ashels  mana:900   strength:800   agility:700 dexterity:400 startingMoney:2500   startingExperience:7
Hero Type:Sorcerer name: Radiant_Ash  mana:800   strength:850   agility:400 dexterity:600 startingMoney:2500   startingExperience:6
Hero Type:Sorcerer name: Skye_Soar  mana:1000   strength:700   agility:400 dexterity:500 startingMoney:2500   startingExperience:5
Hero Type:Paladin name: Solonor_Thelandira  mana:300   strength:750   agility:650 dexterity:700 startingMoney:2500   startingExperience:7
Hero Type:Paladin name: Sehanine_Moonbow  mana:300   strength:750   agility:700 dexterity:700 startingMoney:2500   startingExperience:7
Hero Type:Paladin name: Skoraeus_Stonebones  mana:250   strength:650   agility:600 dexterity:350 startingMoney:2500   startingExperience:4
Hero Type:Paladin name: Garl_Glittergold  mana:100   strength:600   agility:500 dexterity:400 startingMoney:2500   startingExperience:5
Hero Type:Paladin name: Amaryllis_Astra  mana:500   strength:500   agility:500 dexterity:500 startingMoney:2500   startingExperience:5
Hero Type:Paladin name: Caliber_Heist  mana:400   strength:400   agility:400 dexterity:400 startingMoney:2500   startingExperience:2
QUIT(Q/q)
> Caliber_Heist
```

Then the program will print the game board

H (yellow) means hero; M (pink) means market; % (blue) means unreachable cells; Rest of cells are common ones.

```bash

Hero Caliber_Heist add!
*********************************
*   *   *   * M *   *   *   *   *
*********************************
*   *   * % *   *   * M * H *   *
*********************************
*   *   *   *   * M * M *   *   *
*********************************
* M * % * % * % * M * M * M *   *
*********************************
*   * M * % *   *   * M * M * M *
*********************************
* M *   *   * % * M * M * % * % *
*********************************
* M * % * M * M * M *   * M *   *
*********************************
*   *   * % *   * M * M * % *   *
*********************************
move up(W/w)
move left(A/a)
move down(S/s)
move right(D/d)
show information(I/i)
show bag(B/b)
quit game(Q/q)

```

When you enter i (for information)
It will show you two categories: Hero Info(hp, mana, equiped items, etc) and Bag; show as following.

```
> i
**************************************
              Heroes              
**************************************
              Hero Info              
name : Gaerdal_Ironhand 
level : 2 
hp : 200 
mana : 256 
strength : 770 
dexterity : 630 
agility : 500 
defense : 0 
money : 1554 
experience : 1 
--------------------
Weapon Empty!
--------------------
Armor Empty!
--------------------
----------Bag----------
**************************************
              Weapon              
Empty
**************************************
              Armor              
Empty
**************************************
              Potion              
Empty
**************************************
              Spell              
Empty
**************************************
**************************************
```

When you enter a market, the program will first ask you to input the hero name; then it will print hero's information, then give you market operations. Note you have to enter the entire string (name) for specific item (e.g Sword)

The following is an example I try to buy Sword and Axe when the hero is level 1.
```
> m
**************************************
              pick hero              
**************************************
              Hero List              
Sehanine_Monnbow
>Sehanine_Monnbow

name : Sehanine_Monnbow 
level : 1 
hp : 100 
mana : 600 
strength : 700 
dexterity : 500 
agility : 800 
defense : 0 
money : 2500 
experience : 8 
--------------------
Weapon Empty!
--------------------
Armor Empty!
--------------------
----------Bag----------
**************************************
              Weapon              
Empty
**************************************
              Armor              
Empty
**************************************
              Potion              
Empty
**************************************
              Spell              
Empty

choose one type:
weapon(W/w)
armor(A/a)
potion(P/p)
spell(S/s)
quit(Q/q)

> w

**************************************
              Weapon              
[Weapon] name: Sword, cost: 500, minLevel: 1, damage: 800, required hands: 1
[Weapon] name: TSwords, cost: 1400, minLevel: 8, damage: 1600, required hands: 2
[Weapon] name: Scythe, cost: 1000, minLevel: 6, damage: 1100, required hands: 2
[Weapon] name: Dagger, cost: 200, minLevel: 1, damage: 250, required hands: 1
[Weapon] name: Bow, cost: 300, minLevel: 2, damage: 500, required hands: 2
[Weapon] name: Axe, cost: 550, minLevel: 5, damage: 850, required hands: 1
quit(Q/q)

> Sword

Success Buy Sword use money 500
Current Money = 2000
**************************************
              Weapon              
[Weapon] name: Sword, cost: 500, minLevel: 1, damage: 800, required hands: 1
[Weapon] name: TSwords, cost: 1400, minLevel: 8, damage: 1600, required hands: 2
[Weapon] name: Scythe, cost: 1000, minLevel: 6, damage: 1100, required hands: 2
[Weapon] name: Dagger, cost: 200, minLevel: 1, damage: 250, required hands: 1
[Weapon] name: Bow, cost: 300, minLevel: 2, damage: 500, required hands: 2
[Weapon] name: Axe, cost: 550, minLevel: 5, damage: 850, required hands: 1
quit(Q/q)

> Axe
Error Name or Min Level not match or Money is not Enough
....
....
....
```

When you enter a battle, you can always attack, but if you enter "S/s" for Spell when your spell section is empty, it will warn you "Empty!". The follow is an example Hero win a battle.
```
**************************************
              Battle              
**************************************
              new round              

          Hero       
name : Sehanine_Monnbow 
level : 1 
hp : 100 
mana : 600 
strength : 700 
dexterity : 500 
agility : 800 
defense : 0 
money : 2000 
experience : 8 
--------------------
Weapon Empty!
--------------------
Armor Empty!
--------------------
----------Bag----------
**************************************
              Weapon              
Type: Weapon | name: Sword, cost: 500, minLevel: 1, damage: 800, hands: 1]
**************************************
              Armor              
Empty
**************************************
              Potion              
Empty
**************************************
              Spell              
Empty

          Monster       
**************************************
              Monster Info              
name : Aim-Haborym
lv : 1
hp : 100
baseDamage : 450
defenseStat : 350
dodgeChance : 35
**************************************

this round your opera:
        current hero status
name : Sehanine_Monnbow 
level : 1 
hp : 100 
mana : 600 
strength : 700 
dexterity : 500 
agility : 800 
defense : 0 
money : 2000 
experience : 8 
--------------------
Weapon Empty!
--------------------
Armor Empty!
--------------------
----------Bag----------
**************************************
              Weapon              
Type: Weapon | name: Sword, cost: 500, minLevel: 1, damage: 800, hands: 1]
**************************************
              Armor              
Empty
**************************************
              Potion              
Empty
**************************************
              Spell              
Empty
Attack(A/a)
Spell(S/s)
Potion(P/p)
Change(C/c)
Information(I/i)

>a

 Monster hp -17 now hp:83
 Hero hp -22 now hp:78
**************************************
              new round              

          Hero       
name : Sehanine_Monnbow 
level : 1 
hp : 85 
mana : 660 
strength : 700 
dexterity : 500 
agility : 800 
defense : 0 
money : 2000 
experience : 8 
--------------------
Weapon Empty!
--------------------
Armor Empty!
--------------------
----------Bag----------
**************************************
              Weapon              
Type: Weapon | name: Sword, cost: 500, minLevel: 1, damage: 800, hands: 1]
**************************************
              Armor              
Empty
**************************************
              Potion              
Empty
**************************************
              Spell              
Empty

          Monster       
**************************************
              Monster Info              
name : Aim-Haborym
lv : 1
hp : 83
baseDamage : 450
defenseStat : 350
dodgeChance : 35
**************************************

this round your opera:
        current hero status
name : Sehanine_Monnbow 
level : 1 
hp : 85 
mana : 660 
strength : 700 
dexterity : 500 
agility : 800 
defense : 0 
money : 2000 
experience : 8 
--------------------
Weapon Empty!
--------------------
Armor Empty!
--------------------
----------Bag----------
**************************************
              Weapon              
Type: Weapon | name: Sword, cost: 500, minLevel: 1, damage: 800, hands: 1]
**************************************
              Armor              
Empty
**************************************
              Potion              
Empty
**************************************
              Spell              
Empty
Attack(A/a)
Spell(S/s)
Potion(P/p)
Change(C/c)
Information(I/i)

>s

Empty!
 Hero hp -22 now hp:63
**************************************
              new round              

          Hero       
name : Sehanine_Monnbow 
level : 1 
hp : 69 
mana : 726 
strength : 700 
dexterity : 500 
agility : 800 
defense : 0 
money : 2000 
experience : 8 
--------------------
Weapon Empty!
--------------------
Armor Empty!
--------------------
----------Bag----------
**************************************
              Weapon              
Type: Weapon | name: Sword, cost: 500, minLevel: 1, damage: 800, hands: 1]
**************************************
              Armor              
Empty
**************************************
              Potion              
Empty
**************************************
              Spell              
Empty

          Monster       
**************************************
              Monster Info              
name : Aim-Haborym
lv : 1
hp : 83
baseDamage : 450
defenseStat : 350
dodgeChance : 35
**************************************

this round your opera:
        current hero status
name : Sehanine_Monnbow 
level : 1 
hp : 69 
mana : 726 
strength : 700 
dexterity : 500 
agility : 800 
defense : 0 
money : 2000 
experience : 8 
--------------------
Weapon Empty!
--------------------
Armor Empty!
--------------------
----------Bag----------
**************************************
              Weapon              
Type: Weapon | name: Sword, cost: 500, minLevel: 1, damage: 800, hands: 1]
**************************************
              Armor              
Empty
**************************************
              Potion              
Empty
**************************************
              Spell              
Empty
Attack(A/a)
Spell(S/s)
Potion(P/p)
Change(C/c)
Information(I/i)
....
...
....
..

Hero victory

win money 100
win experience 2
current money 2600
current experience 10
*********************************
*   * M * M *   * M * % *   *   *
*********************************
*   * % *   * % *   *   * M *   *
*********************************
* % *   * M * M *   * M *   * M *
*********************************
* M *   * M *   *   * % * M * % *
*********************************
*   * H * M *   * % *   * M *   *
*********************************
* % * M *   * % *   *   * M *   *
*********************************
* M *   * M * % * M *   * % * M *
*********************************
*   * M *   *   * M * % * M *   *
*********************************
move up(W/w)
move left(A/a)
move down(S/s)
move right(D/d)
show information(I/i)
show bag(B/b)
quit game(Q/q)

```

## Prerequisites

```
Java SE 8
```