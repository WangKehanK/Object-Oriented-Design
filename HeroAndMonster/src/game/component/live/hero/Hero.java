package game.component.live.hero;

import game.component.entity.*;
import game.component.live.Alive;
import game.component.live.Bag;
import game.component.live.Combatable;

/**
 * A basic Hero interface extends Alive interface.
 */
public interface Hero  extends Alive, Combatable {
    String getName();

    void printInfo();

    int getLevel();

    void revive();

    void victory(int level);

    void fail();

    void restore();

    void printBagInfo();

    Bag getBag();

    int getMana();

    int getDexterity();

    void setMana(int i);

    int attackNumber();

    void printName();

    void sale(Saleable saleable, String type);

    boolean canBuy(AbstractEntity entity);

    void buy(Saleable saleable, String type);

    int getMoney();

    void changeEquipable(Equipable equipable);

    void use(Usable usable);
}
