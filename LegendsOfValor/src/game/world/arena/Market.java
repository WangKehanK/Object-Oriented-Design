package game.world.arena;

import game.component.live.hero.Hero;

public interface Market {
    void buy(Hero hero, String type);
}
