package game.world.cell;

import game.LegendsOfValorGame;
import game.component.live.hero.Hero;
import game.component.live.monster.Monster;
/**
 * An abstract Legend of Valor implements the valorcell interface
 */
public abstract class AbstractValorCell implements ValorCell {
    LegendsOfValorGame game;
    String printSign;
    Monster monster;
    Hero hero;

    public AbstractValorCell(LegendsOfValorGame game, String printSign) {
        this.game = game;
        this.printSign = printSign;
    }

    protected String getLine() {
        return printSign + " - " + printSign + " - " + printSign;
    }

    @Override
    public String printStartLine() {
        return getLine();
    }

    @Override
    public String printMidLine() {
        String str = "";
        str += "|";
        if (hero != null) {
            String name = ("H" + hero.getNo());
            while(name.length() < 3){
                name += " ";
            }
            str += name;
        } else {
            str += "   ";
        }
        str += " ";
        if (monster != null) {
            String name = ("M" + monster.getNo());
            while(name.length() < 3){
                name += " ";
            }
            str += name;
        } else {
            str += "   ";
        }
        str += "|";
        return str;
    }

    @Override
    public String printEndLine() {
        return getLine();
    }

    @Override
    public void enter(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void enter(Monster monster) {
        this.monster = monster;
    }

    @Override
    public void leave(Hero hero) {
        this.hero = null;
    }

    @Override
    public void leave(Monster monster) {
        this.monster = null;
    }

    @Override
    public void printMenu() {
        System.out.println("move up(W/w)");
        System.out.println("move left(A/a)");
        System.out.println("move down(S/s)");
        System.out.println("move right(D/d)");
        System.out.println("show information(I/i)");
        System.out.println("show bag(P/p)");
//
//        if (cells[teamX][teamY].isMarket()) {
//            System.out.println("Market(M/m)");
//        }
//        System.out.println("quit game(Q/q)");
    }

    @Override
    public boolean containsMonster() {
        return this.monster != null;
    }

    @Override
    public boolean containsHero() {
        return this.hero != null;
    }

    @Override
    public Monster getMonster() {
        return this.monster;
    }

    @Override
    public Hero getHero() {
        return this.hero;
    }

    @Override
    public boolean roundProcessor(String command) {
        if (command.equalsIgnoreCase("p")) {
            //multiple operation allowed
            game.operaBag(hero);
            return false;
        }else if (command.equalsIgnoreCase("i")) {
            //multiple operation allowed
            game.printInfo(hero);
            return false;
        } else if (command.equalsIgnoreCase("w")
                || command.equalsIgnoreCase("a")
                || command.equalsIgnoreCase("s")
                || command.equalsIgnoreCase("d")) {
            //once per round
            game.move(command, hero);
            return true;
        }else if (command.equalsIgnoreCase("t")) {
            //once per round
            game.teleport(hero);
            return true;
        }else if (command.equalsIgnoreCase("b")) {
            //once per round
            game.back(hero);
            return true;
        }else if(command.equalsIgnoreCase("o")){
            //attack
            //once per round
            game.attack(hero);
            return true;
        }
        return false;
    }
}
