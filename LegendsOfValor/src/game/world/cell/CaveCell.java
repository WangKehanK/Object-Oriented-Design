package game.world.cell;

import game.LegendsOfValorGame;
import game.component.live.hero.Hero;
import game.component.live.monster.Monster;

/**
 * A cave cell class that with specific favor
 */
public class CaveCell  extends AbstractValorCell implements Reachable {

    private int roundCount;

    public CaveCell(LegendsOfValorGame game) {
        super(game,"C");
        roundCount = 0;
    }

    @Override
    public void enter(Hero hero) {
        super.enter(hero);
        roundCount = 1;
        hero.setAgility((int) (hero.getRealAgility() * Math.pow(1.1, roundCount)));
    }

    @Override
    public void enter(Monster monster) {
        super.enter(monster);
    }

    @Override
    public void leave(Hero hero) {
        super.leave(hero);
        roundCount = 0;
        hero.setAgility((int) (hero.getRealAgility() * Math.pow(1.1, roundCount)));
    }

    @Override
    public void leave(Monster monster) {
        super.leave(monster);
    }

    @Override
    public boolean roundProcessor(String command) {
        roundCount++;
        hero.setAgility((int) (hero.getRealAgility() * Math.pow(1.1, roundCount)));

        return super.roundProcessor(command);
    }
}
