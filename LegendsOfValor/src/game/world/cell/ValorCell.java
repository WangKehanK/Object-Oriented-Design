package game.world.cell;

import game.component.live.hero.Hero;
import game.component.live.monster.Monster;
/**
 * A simple Legends of Valor cell interface
 */
public interface ValorCell extends Cell{
    void enter(Hero hero);

    void enter(Monster monster);

    void leave(Hero hero);

    void leave(Monster monster);

    String printStartLine();

    String printMidLine();

    String printEndLine();

    boolean roundProcessor(String command);

    void printMenu();

    boolean containsMonster();

    Monster getMonster();

    boolean containsHero();

    Hero getHero();
}
