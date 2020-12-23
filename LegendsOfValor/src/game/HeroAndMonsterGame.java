package game;

import game.component.live.Bag;
import game.component.live.HeroTeam;
import game.component.live.hero.Hero;
import game.component.live.monster.*;
import game.world.HeroAndMonsterBoard;
import game.world.arena.Arena;
import game.world.cell.*;
import utils.ConfigUtil;
import utils.RandomUtil;
import utils.ScannerUtil;
import utils.SystemPrintUtil;

import java.util.*;

/**
 * A game class that combines all below classes and holds the main game logic
 */
public class HeroAndMonsterGame extends RPGGame{
    private HeroAndMonsterBoard heroAndMonsterBoard;
    private int boardLength;
    private HeroTeam heroTeam;
    private int heroNumber;


    public HeroAndMonsterGame() {
        this.over = false;
        initGame();
    }

    /**
     * Get the number of heroes
     */
    private int inputHeroNumber() {
        SystemPrintUtil.printBlue("Welcome to Legends: Monsters and Heroes");
        int heroNumber = ScannerUtil.readInteger("Please input the total number of Heroes (1-3):");
        while(heroNumber > 3 || heroNumber < 1){
            SystemPrintUtil.printRed("Number Range Error (1-3)");
            System.out.println();
            heroNumber = ScannerUtil.readInteger("Hero Number (1-3) :");
        }

        return heroNumber;
    }

    /**
     * Get the initial position
     */
    private void initPosition() {
        while (true) {
            int x = RandomUtil.nextInt(boardLength);
            int y = RandomUtil.nextInt(boardLength);
            if (heroAndMonsterBoard.isSimple(x, y)) {
                heroTeam.setPosition(x, y);
                heroAndMonsterBoard.setTeamPosition(x, y);
                break;
            }
        }
    }

    @Override
    public void initGame() {
        //determine the board length
        boardLength = ConfigUtil.getConfigInteger("boardLength", 8);
        heroAndMonsterBoard = new HeroAndMonsterBoard(boardLength);

        // percent of unreachable cells, market cells...
        int unreachablePercent = ConfigUtil.getConfigInteger("unreachablePercent", 20);
        int marketPercent = ConfigUtil.getConfigInteger("marketPercent", 30);
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                int randomInt = RandomUtil.nextInt(100);
                if (randomInt < unreachablePercent) {
                    heroAndMonsterBoard.setCell(i, j, new UnreachableCell(this));
                } else if (randomInt < (marketPercent + unreachablePercent)) {
                    heroAndMonsterBoard.setCell(i, j, new MarketCell(this));
                } else {
                    heroAndMonsterBoard.setCell(i, j, new SimpleCell(this));
                }
            }
        }

        heroNumber = inputHeroNumber(); // 1-3
        // movable
        heroTeam = new HeroTeam(heroNumber);

        if(!heroTeam.initHero()){
            over = true;
        }

        initPosition();
    }

    public void start() {
        while (!over) {
            heroAndMonsterBoard.print();
            opera();
        }
        System.out.println("Game Over!");
    }

    /**
     * Input from user
     */
    private void opera() {
        while (true) {
            //print the entire menu
            heroAndMonsterBoard.printMenu();
            //input from user
            String str = ScannerUtil.readLine();
            if(str.equalsIgnoreCase("q")){
                over = true;
                break;
            }

            Boolean result = heroAndMonsterBoard.process(str);
            if(!result){
                SystemPrintUtil.printRed("Error Command!");
            }
            heroAndMonsterBoard.print();
        }
    }

    /**
     * Bag
     */
    public void operaBag() {
        SystemPrintUtil.printlnWithStart("pick hero");
        Hero hero = pickHero();
        Bag bag = hero.getBag();
        bag.printInfo();

        bag.opera();
    }

    public Hero pickHero() {
        while (true) {
            heroTeam.printHeroList();
            String choose = ScannerUtil.readLine();
            Hero hero = heroTeam.findHeroByName(choose);
            if (hero == null) {
                SystemPrintUtil.printRed("Error Hero Name");
            } else {
                return hero;
            }
        }
    }


    public void printInfo() {
        heroTeam.printInfo();
    }

    /**
     * Move, directions
     * @param str specific direction
     */
    public void move(String str) {
        Integer newX = getNewX(str, heroTeam.getX());
        Integer newY = getNewY(str, heroTeam.getY());
        if(newX == null || newY == null){
            SystemPrintUtil.printRed("can not go to out of board");
            return;
        }else{
            if(heroAndMonsterBoard.canMove(newX, newY)){
                heroTeam.move(newX, newY);
                heroAndMonsterBoard.moveTeam(newX, newY);
                //battle only starts in common cells
                HeroAndMonsterCell cell = (HeroAndMonsterCell) heroAndMonsterBoard.get(newX, newY);
                if (cell.isSimple()) {
                    if (isCreateBattle()) {

                        SystemPrintUtil.printlnWithStart("Battle");
                        List<Monster> monsterList = createMonster(heroTeam.getList());

                        Arena arena = new Arena(heroTeam.getList(), monsterList);

                        arena.battle();

                        boolean isOver = true;
                        //hero dies
                        for (Hero hero : heroTeam.getList()) {
                            if (hero.isAlive()) {
                                isOver = false;
                            }
                        }
                        if(isOver){
                            over = true;
                        }
                    }
                }
            }else{
                SystemPrintUtil.printRed("can not go to Unreachable Cell");
            }
        }

    }

    /**
     * create monster by heroes
     * @param list
     */
    private List<Monster> createMonster(List<Hero> list) {
        int highestLevel = 0;
        for(Hero hero: list){
            if(highestLevel < hero.getLevel()){
                highestLevel = hero.getLevel();
            }
        }

        List<MonsterPrototype> monsterPrototypeList = MonsterCache.getList();
        Collections.shuffle(monsterPrototypeList);

        List<Monster> monsterList = new ArrayList<>(heroNumber);
        // get hero highest level, and match with monster
        for(MonsterPrototype monsterPrototype: monsterPrototypeList){
            if(monsterList.size() >= heroNumber){
                break;
            }
            if(monsterPrototype.getLevel() == highestLevel){
                monsterList.add(MonsterCache.createObject(monsterPrototype));
            }
        }
        return monsterList;
    }

    /**
     * determine by the battle frequency
     * @return
     */
    private boolean isCreateBattle() {
        int percent = ConfigUtil.getConfigInteger("battlePercent", 30);
        if(RandomUtil.nextInt(100) < percent){
            return true;
        }
        return false;
    }

    private Integer getNewY(String str, int y) {
        if (str.equalsIgnoreCase("a")) {
            y = y - 1;
        }else if (str.equalsIgnoreCase("d")) {
            y = y + 1;
        }
        if (y < 0 || y >= boardLength) {
            return null;
        }
        return y;
    }

    private Integer getNewX(String str, int x) {
        if (str.equalsIgnoreCase("w")) {
            x = x - 1;
        } else if (str.equalsIgnoreCase("s")) {
            x = x + 1;
        }
        if (x < 0 || x >= boardLength) {
            return null;
        }
        return x;
    }
}
