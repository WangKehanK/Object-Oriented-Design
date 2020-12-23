package game.world.cell;

import game.HeroAndMonsterGame;

/**
 * A simple cell that extends the abstract cell class
 */
public class SimpleCell extends AbstractCell implements Reachable{
    public SimpleCell(HeroAndMonsterGame game) {
        super(game);
    }

    @Override
    public boolean isSimple() {
        return true;
    }

    @Override
    public String printMap() {
        return " ";
    }

    @Override
    public boolean isMarket() {
        return false;
    }

    @Override
    public boolean process(String str) {
        if (str.equalsIgnoreCase("b")) {
            game.operaBag();
            return true;
        } else if (str.equalsIgnoreCase("i")) {
            game.printInfo();
            return true;
        } else if (str.equalsIgnoreCase("w")
                || str.equalsIgnoreCase("a")
                || str.equalsIgnoreCase("s")
                || str.equalsIgnoreCase("d")) {
            game.move(str);
            return true;
        }
        return false;
    }

    @Override
    public boolean isUnreachable() {
        return false;
    }
}
