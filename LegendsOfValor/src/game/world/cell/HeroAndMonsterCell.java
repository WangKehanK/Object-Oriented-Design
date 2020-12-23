package game.world.cell;

/**
 * A simple cell interface
 */
public interface HeroAndMonsterCell extends Cell{
    boolean isSimple();

    String printMap();

    boolean isMarket();

    boolean process(String str);

    boolean isUnreachable();
}
