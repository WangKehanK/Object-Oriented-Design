package game.component.live.monster;

import game.component.live.Alive;
import game.component.live.Combatable;

/**
 *  A basic Monster interface extends Alive, Combatable interface.
 */
public interface Monster extends Alive, Combatable {

    int getLevel();

    void printInfo();

    int getDamage();

    int getDefense();

    int getDodgeChance();

    void setDefense(int i);

    void setDamage(int i);

    void setDodgeChance(int i);

    void fired();

    void iced();

    void lighted();

    int getNo();

    void setNo(int currentMonsterNo);

    String getName();

    void setPosition(int x, int y);

    int getX();

    int getY();

    void move(Integer newX, Integer newY);
}
