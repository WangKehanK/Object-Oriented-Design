package game.world.cell;

import game.HeroAndMonsterGame;

/**
 * An abstract cell class implements the cell interface
 */
public abstract class AbstractCell implements Cell{
    HeroAndMonsterGame game;

    public AbstractCell(HeroAndMonsterGame game) {
        this.game = game;
    }
}
