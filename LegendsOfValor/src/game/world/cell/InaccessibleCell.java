package game.world.cell;

import game.LegendsOfValorGame;

/**
 * A unreachable cell class with specific favor extend the abstract ValorCell
 */
public class InaccessibleCell extends AbstractValorCell {


    public InaccessibleCell(LegendsOfValorGame game) {
        super(game,"I");
    }


    @Override
    public String printMidLine() {
        return "| X X X |";
    }

    @Override
    public boolean roundProcessor(String command) {
        return super.roundProcessor(command);
    }
}
