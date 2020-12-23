package game.component.live;

import game.component.live.hero.Hero;
import game.component.live.hero.HeroCache;
import utils.ScannerUtil;
import utils.SystemPrintUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A HeroTeam class implement movable interface that allow program can be played with multiple heroes in one team;
 */
public class HeroTeam implements Movable {
    private int x;
    private int y;

    private int heroNumber;
    private List<Hero> list;

    public HeroTeam(int heroNumber) {
        this.heroNumber =heroNumber;
        this.list = new ArrayList<>(heroNumber);
    }


    /**
     * init the hero list
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
                        list.add(hero);
                        SystemPrintUtil.printBlue("Hero " + hero.getName() + " add!");
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

    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * use to determine whether the hero is created
     * @param findHero
     * @return
     */
    private boolean contains(Hero findHero) {
        for(Hero hero: list){
            if(hero.equals(findHero)){
                return true;
            }
        }
        return false;
    }

    /**
     * hero(es) position
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void printInfo() {
        SystemPrintUtil.printlnWithStart("Heroes");
        for(Hero hero: list){
            SystemPrintUtil.printlnWithStart("Hero Info");
            hero.printInfo();
            SystemPrintUtil.printlnWithEnd();
        }
        SystemPrintUtil.printlnWithEnd();
        SystemPrintUtil.println("");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeroNumber() {
        return heroNumber;
    }

    public List<Hero> getList() {
        return list;
    }

    public void printHeroList() {
        SystemPrintUtil.printlnWithStart("Hero List");
        for(Hero hero: list){
            hero.printName();
        }
    }

    public Hero findHeroByName(String choose) {
        for(Hero hero: list){
            if(hero.getName().equals(choose)){
                return hero;
            }
        }
        return null;
    }
}
