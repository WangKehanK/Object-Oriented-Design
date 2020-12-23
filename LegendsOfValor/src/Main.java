import game.HeroAndMonsterGame;
import game.LegendsOfValorGame;

/**
 * Main is located at the same level of game and util directory, which is a main game entrance;
 * To start the game you have to go to the directory where Main.java located first (./src),
 * then follow the instruction to run.
 */
public class Main {
    public static void main(String[] args) {
        new LegendsOfValorGame().start();
    }
}
