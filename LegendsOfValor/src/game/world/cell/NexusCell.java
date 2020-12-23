package game.world.cell;

import game.LegendsOfValorGame;
import game.world.arena.ValorMarket;

/**
 * A nexus cell class that with specific favor
 */
public class NexusCell extends AbstractValorCell implements Reachable {
    private ValorMarket market;


    public NexusCell(LegendsOfValorGame game) {
        super(game,"N");
        market = new ValorMarket(game);
    }

    @Override
    public boolean roundProcessor(String command) {
        if(!super.roundProcessor(command)){
            if(command.equalsIgnoreCase("m")){
                //market
                market.operaMarket(getHero());
            }
            return false;
        }
        return true;
    }

    @Override
    public void printMenu() {
        super.printMenu();
        System.out.println("Market(M/m)");
    }


}
