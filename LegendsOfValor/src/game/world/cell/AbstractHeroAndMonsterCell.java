package game.world.cell;

import game.HeroAndMonsterGame;

/**
 * An abstract cell class implements the cell interface
 */
public abstract class AbstractHeroAndMonsterCell implements HeroAndMonsterCell {
    HeroAndMonsterGame game;

    public AbstractHeroAndMonsterCell(HeroAndMonsterGame game) {
        this.game = game;
    }
}
