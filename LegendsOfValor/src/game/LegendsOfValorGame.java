package game;

import game.component.entity.Spell;
import game.component.entity.SpellCache;
import game.component.live.Bag;
import game.component.live.hero.Hero;
import game.component.live.hero.HeroCache;
import game.component.live.monster.Monster;
import game.component.live.monster.MonsterCache;
import game.component.live.monster.MonsterPrototype;
import game.world.ValorBoard;
import game.world.cell.*;
import utils.ConfigUtil;
import utils.RandomUtil;
import utils.ScannerUtil;
import utils.SystemPrintUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * A Legends of Valor game class that combines all below classes and holds the main game logic
 */
public class LegendsOfValorGame extends RPGGame{
    private ValorBoard board;
    private int boardLength;

    private int currentHeroNo;
    private int currentMonsterNo;

    private int roundNumber;


    public LegendsOfValorGame() {
        super();
        heroNumber = 3;
        currentHeroNo = 1;
        currentMonsterNo = 1;

        roundNumber = 0;

        this.over = false;

        initGame();
    }

    @Override
    public void initGame(){
        //determine the board length
        boardLength = ConfigUtil.getConfigInteger("boardLength", 8);
        board = new ValorBoard(boardLength);

        // percent of unreachable cells, market cells...
        int bushPercent = ConfigUtil.getConfigInteger("bushPercent", 20);
        int cavePercent = ConfigUtil.getConfigInteger("cavePercent", 20);
        int koulouPercent = ConfigUtil.getConfigInteger("koulouPercent", 20);
        int plainPercent = ConfigUtil.getConfigInteger("plainPercent", 40);
        for (int j = 0; j < boardLength; j++) {
            if (j == 2 || j == 5) {
                board.setCell(0, j, new InaccessibleCell(this));
                board.setCell(boardLength - 1, j, new InaccessibleCell(this));
            } else {
                board.setCell(0, j, new NexusCell(this));
                board.setCell(boardLength - 1, j, new NexusCell(this));
            }
        }

        for (int i = 1; i < boardLength - 1; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (j == 2 || j == 5) {
                    board.setCell(i, j, new InaccessibleCell(this));
                } else {
                    int randomInt = RandomUtil.nextInt(100);
                    if (randomInt < bushPercent) {
                        board.setCell(i, j, new BushCell(this));
                    } else if (randomInt < (bushPercent + cavePercent)) {
                        board.setCell(i, j, new CaveCell(this));
                    } else if (randomInt < (bushPercent + cavePercent + koulouPercent)) {
                        board.setCell(i, j, new KoulouCell(this));
                    } else {
                        board.setCell(i, j, new PlainCell(this));
                    }
                }
            }
        }

        if (!initHero()) {
            over = true;
        }
    }

    /**
     * init the hero list
     *
     * @return
     */
    public boolean initHero() {
        for (int i = 0; i < this.heroNumber; i++) {
            while (true) {
                SystemPrintUtil.println("Please Choose One Hero");
                SystemPrintUtil.printlnWithStart("hero");
                //print the whole name list
                HeroCache.print();
                SystemPrintUtil.println("QUIT(Q/q)");

                //get the input
                String str = ScannerUtil.readLine();

                //leave the game
                if (str.equalsIgnoreCase("q")) {
                    return false;
                }

                Hero hero = HeroCache.getHeroObjectByName(str);


                //determine whether the hero is created
                if (hero != null) {
                    if (!contains(hero)) {
                        heroes.add(hero);
                        hero.setNo(currentHeroNo);
                        SystemPrintUtil.printBlue(hero.getNo() + " " + hero.getName() + " add!");
                        int line = RandomUtil.nextInt(2);
                        hero.setPosition(boardLength - 1, (currentHeroNo - 1) * 3 + line);
                        board.setHeroPosition(boardLength - 1, (currentHeroNo - 1) * 3 + line, hero);
                        currentHeroNo++;
                        break;
                    } else {
                        SystemPrintUtil.printRed("Hero is existing!");
                    }
                } else {
                    SystemPrintUtil.printRed("Hero name error!");
                }
            }
        }
        return true;
    }


    /**
     * use to determine whether the hero is created
     *
     * @param findHero
     * @return
     */
    private boolean contains(Hero findHero) {
        for (Hero hero : heroes) {
            if (hero.equals(findHero)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void start() {
        int monsterCreateRoundNum = ConfigUtil.getConfigInteger("monsterCreateRoundNum", 8);
        while (!over) {
            if (roundNumber % monsterCreateRoundNum == 0) {
                List<Monster> newMonsters = createMonster();
                //add monster to battle
                for (int i = 0; i < newMonsters.size(); i++) {
                    Monster monster = newMonsters.get(i);
                    int line = RandomUtil.nextInt(2);
                    //if the position has a monster, can not move
                    if(board.containsMonster(0, (currentMonsterNo - 1) % 3 * 3 + line)) {
                        if(line == 0){
                            if(board.containsMonster(0, (currentMonsterNo - 1) % 3 * 3 + 1)){
                                continue;
                            }else{
                                line = 1;
                            }
                        }else{
                            if(board.containsMonster(0, (currentMonsterNo - 1) % 3 * 3 + 0)){
                                continue;
                            }else{
                                line = 0;
                            }
                        }
                    }
                    monsters.add(monster);
                    monster.setNo(currentMonsterNo);
                    SystemPrintUtil.printBlue(monster.getNo() + " " + monster.getName() + " add!");
                    monster.setPosition(0, (currentMonsterNo - 1) % 3 * 3 + line);
                    board.setMonsterPosition(0, (currentMonsterNo - 1) % 3 * 3 + line, monster);
                    currentMonsterNo++;
                }
            }

            roundNumber++;

            board.print();
            roundOpera();

            for (Hero hero : heroes) {
                hero.restore();
            }


            if (isHeroVictory) {
                board.print();
                SystemPrintUtil.printBlue("Hero Victory!!!");
                break;
            }
            if (isMonsterVictory) {
                board.print();
                SystemPrintUtil.printBlue("Monster Victory!!!");
                break;
            }


        }
        System.out.println("Game Over!");
    }

    /**
     * Input from user
     */
    private void roundOpera() {

        SystemPrintUtil.printlnWithStart("****Round " + roundNumber + "****");

        for (Hero hero : heroes) {
            SystemPrintUtil.printlnWithStart("H" + hero.getNo() + " choose opera:");
            while (true) {
                //print the entire menu
                board.printMenu(hero.getX(), hero.getY());

                //input from user
                String str = ScannerUtil.readLine();
                Boolean result = board.process(hero.getX(), hero.getY(), str);
                //some operations are repeatable in one round
                if (!result) {
                    if (str.equalsIgnoreCase("m")
                            || str.equalsIgnoreCase("p")
                            || str.equalsIgnoreCase("i")) {
                        continue;
                    } else {
                        SystemPrintUtil.printRed("Error Command!");
                    }
                    board.print();
                } else {
                    if (hero.getX() == 0) {
                        isHeroVictory = true;
                    }
                    break;
                }
            }
            if(isHeroVictory){
                break;
            }
            board.print();
        }

        for (Monster monster : monsters) {
            SystemPrintUtil.printlnWithStart("M" + monster.getNo() + "'s round");

            if (monster.getX() == boardLength - 2) {
                SystemPrintUtil.println("M" + monster.getNo() + " arrive Nexus!");
                board.moveMonster(monster.getX() + 1, monster.getY(), monster);
                monster.move(monster.getX() + 1, monster.getY());

                //game end
                isMonsterVictory = true;
                break;
            }

            //find monster
            List<Hero> list = board.monsterAttackList(monster.getX(), monster.getY());
            if (list.size() > 0) {
                Hero hero = list.get(0);
                SystemPrintUtil.println("M" + monster.getNo() + " attack " + "H" + hero.getNo());
                hero.defense((int) (monster.getDamage() * 0.05));
                if (!hero.isAlive()) {
                    hero.fail();
                    //back to nexus when hp = 0
                    hero.revive();
                    board.moveHero(boardLength - 1, hero.getY(), hero);
                    hero.move(boardLength - 1, hero.getY());
                }
            } else {
                //move forward
                if (!board.containsMonster(monster.getX() + 1, monster.getY())) {
                    SystemPrintUtil.println("M" + monster.getNo() + " move");
                    board.moveMonster(monster.getX() + 1, monster.getY(), monster);
                    monster.move(monster.getX() + 1, monster.getY());
                } else {
                    SystemPrintUtil.println("M" + monster.getNo() + " stay on.");
                }
            }
        }
    }

    /**
     * create monster by heroes
     */
    private List<Monster> createMonster() {
        int highestLevel = 0;
        for (Hero hero : heroes) {
            if (highestLevel < hero.getLevel()) {
                highestLevel = hero.getLevel();
            }
        }

        List<MonsterPrototype> monsterPrototypeList = MonsterCache.getList();
        Collections.shuffle(monsterPrototypeList);

        List<Monster> monsterList = new ArrayList<>(heroNumber);
        // get hero highest level, and match with monster
        for (MonsterPrototype monsterPrototype : monsterPrototypeList) {
            if (monsterList.size() >= heroNumber) {
                break;
            }
            if (monsterPrototype.getLevel() == highestLevel) {
                monsterList.add(MonsterCache.createObject(monsterPrototype));
            }
        }
        return monsterList;
    }

    public void operaBag(Hero hero) {
        Bag bag = hero.getBag();
        bag.printInfo();
        bag.opera();
        board.print();
    }

    public void printInfo(Hero hero) {
        hero.printInfo();
    }

    public void move(String command, Hero hero) {
        Integer newX = getNewX(command, hero.getX());
        Integer newY = getNewY(command, hero.getY());
        if (newX == null || newY == null) {
            SystemPrintUtil.printRed("can not go to out of board");
            return;
        } else {
            if (board.canMove(newX, newY, hero, command)) {
                board.moveHero(newX, newY, hero);
                hero.move(newX, newY);
            } else {
                SystemPrintUtil.printRed("can not go to the cell");
            }
        }
    }

    public void teleport(Hero hero) {
        int y = hero.getY();
        int laneNumber = y / 3;

        List<String> chooses = new ArrayList<>();
        System.out.println("Choose one lane want to teleport:");
        if (laneNumber != 0 && !board.existMonster(0, hero.getX(), hero.getY())
                && !board.containsHero(hero.getX(), hero.getY() % 3)) {
            System.out.println("Lane 1 (1)");
            chooses.add("1");
        }
        if (laneNumber != 1 && !board.existMonster(1, hero.getX(), hero.getY())
                && !board.containsHero(hero.getX(), hero.getY() % 3 + 3)) {
            System.out.println("Lane 2 (2)");
            chooses.add("2");
        }
        if (laneNumber != 2 && !board.existMonster(2, hero.getX(), hero.getY())
                && !board.containsHero(hero.getX(), hero.getY() % 3 + 6)) {
            System.out.println("Lane 3 (3)");
            chooses.add("3");
        }

        if (chooses.size() == 0) {
            SystemPrintUtil.printRed("Sorry, the hero can not teleport");
        } else {
            while (true) {
                //input from user
                String str = ScannerUtil.readLine();
                if (chooses.contains(str.trim())) {
                    int newLane = Integer.parseInt(str);
                    board.moveHero(hero.getX(), (newLane - 1) * 3 + (y - laneNumber * 3), hero);
                    hero.move(hero.getX(), (newLane - 1) * 3 + (y - laneNumber * 3));
                    break;
                } else {
                    SystemPrintUtil.printRed("Error Choose!");
                }
            }
        }
    }

    public void back(Hero hero) {
        if (!board.containsHero(boardLength - 1, hero.getY())) {
            board.moveHero(boardLength - 1, hero.getY(), hero);
            hero.move(boardLength - 1, hero.getY());
        } else {
            SystemPrintUtil.printRed("The Nexus Cell exist Hero");
        }

    }

    private Integer getNewY(String str, int y) {
        if (str.equalsIgnoreCase("a")) {
            y = y - 1;
        } else if (str.equalsIgnoreCase("d")) {
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

    public void attack(Hero hero) {
        List<Monster> monsterList = board.heroAttackList(hero.getX(), hero.getY());
        if (monsterList.size() == 0) {
            SystemPrintUtil.printRed("There are no Monster that can attack!");
            return;
        }

        Monster monster = monsterList.get(0);
        while (true) {
            System.out.println("this round your opera:");
            System.out.println("        current hero status");
            hero.printInfo();
            printMenu();
            String str = ScannerUtil.readLine();

            if (str.equalsIgnoreCase("a")) {
                attack(hero, monster);
                break;
            } else if (str.equalsIgnoreCase("s")) {
                spell(hero, monster);
                break;
            } else {
                SystemPrintUtil.printRed("Error Command");
            }
        }
    }

    private void printMenu() {
        System.out.println("Attack(A/a)");
        System.out.println("Spell(S/s)");
    }

    private void attack(Hero hero, Monster monster) {
        int attackNumber = hero.attackNumber();
        monster.defense(attackNumber);

        //kill monster 
        if (!monster.isAlive()) {

            board.killMonster(monster.getX(), monster.getY(), monster);
            monsters.remove(monster);

            SystemPrintUtil.printBlue("Hero victory.M" + monster.getNo() + " killed!");
            hero.victory(monster.getLevel());
        }
    }

    private void spell(Hero hero, Monster monster) {
        Bag bag = hero.getBag();
        List<Spell> spellList = bag.getSpellList();
        if (spellList.size() == 0) {
            SystemPrintUtil.printRed("Empty!");
            return;
        }
        SystemPrintUtil.printlnWithStart("choose one Spell");
        while (true) {
            bag.spellPrint();
            System.out.println("quit(Q/q)");

            String choose = ScannerUtil.readLine();
            if (choose.equalsIgnoreCase("q")) {
                break;
            }
            Spell spell = bag.findSpellByName(choose);
            if (spell != null) {
                if (hero.getMana() < spell.getMana()) {
                    SystemPrintUtil.printRed("Mana is not enough!");
                    return;
                }
                int attackNumber = (int) ((spell.getDamage() + (hero.getDexterity() / 10000) * spell.getDamage()) * 0.05);

                hero.setMana(hero.getMana() - spell.getMana());
                monster.defense(attackNumber);

                if (spell.getSpellType().equalsIgnoreCase(SpellCache.FIRE)) {
                    monster.fired();
                } else if (spell.getSpellType().equals(SpellCache.ICE)) {
                    monster.iced();
                } else if (spell.getSpellType().equals(SpellCache.LIGHTNING)) {
                    monster.lighted();
                }
                bag.removeSpell(spell);
            } else {
                SystemPrintUtil.printRed("Error!");
            }

        }
    }

}
