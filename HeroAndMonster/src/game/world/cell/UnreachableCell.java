package game.world.cell;

import game.HeroAndMonsterGame;

/**
 * A unreachable cell class that extend the abstract cell class.
 */
public class UnreachableCell extends AbstractCell{
    public UnreachableCell(HeroAndMonsterGame heroAndMonsterGame) {
        super(heroAndMonsterGame);
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    @Override
    public String printMap() {
        return "\u001B[36m%\u001B[0m";
    }

    @Override
    public boolean isMarket() {
        return false;
    }

    @Override
    public boolean process(String str) {
        return true;
    }

    @Override
    public boolean isUnreachable() {
        return true;
    }
}
