package game;

import game.component.live.hero.Hero;
import game.component.live.monster.Monster;

import java.util.ArrayList;
import java.util.List;
/**
 * An abstract RPGGame class, implements the Game interface, 
 * can be extended to Legends Of Valor Game and 
 * Legends: Monster and Heroes game.
 */
public abstract class RPGGame implements Game{
    protected List<Hero> heroes;
    protected List<Monster> monsters;
    protected int heroNumber;


    protected boolean isHeroVictory;
    protected boolean isMonsterVictory;

    protected boolean over;

    public RPGGame() {
        isHeroVictory = false;
        isMonsterVictory = false;
        heroes = new ArrayList<>();
        monsters = new ArrayList<>();
    }
}
