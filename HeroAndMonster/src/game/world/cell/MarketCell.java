package game.world.cell;

import game.HeroAndMonsterGame;
import game.world.arena.Market;

/**
 * A market cell class that extends the abstract cell class
 */
public class MarketCell extends AbstractCell implements Reachable{

    private Market market;

    public MarketCell(HeroAndMonsterGame heroAndMonsterGame) {
        super(heroAndMonsterGame);
        market = new Market(heroAndMonsterGame);
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    @Override
    public String printMap() {
        return "\u001B[35mM\u001B[0m";
    }

    @Override
    public boolean isMarket() {
        return true;
    }

    @Override
    public boolean process(String str) {
        if (str.equalsIgnoreCase("b")) {
            game.operaBag();
            return true;
        } else if (str.equalsIgnoreCase("m")) {
            market.operaMarket();
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
