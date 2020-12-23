package game.component.live.hero;

/**
 * A warrior hero class extends AbstractHero with specific favor.
 */
public class Warrior extends AbstractHero{

    public Warrior(String name, int mana, int strength, int dexterity, int agility, int money, int experience) {
        super(name, mana, strength, dexterity, agility, money, experience);
    }

    @Override
    public void levelUp() {
        super.levelUp();

        setStrength((int) (getStrength() * 1.1));
        setDexterity((int) (getDexterity() * 1.05));
        setAgility((int) (getAgility() * 1.));
        setDefense((int) (getDefense() * 1.05));

    }
}
