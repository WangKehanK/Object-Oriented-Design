package game.world.cell;


import game.LegendsOfValorGame;

/**
 * A plain cell class
 */
public class PlainCell extends AbstractValorCell implements Reachable{


    public PlainCell(LegendsOfValorGame game) {
        super(game,"P");
    }

    @Override
    public boolean roundProcessor(String command) {
        return super.roundProcessor(command);
    }
}
