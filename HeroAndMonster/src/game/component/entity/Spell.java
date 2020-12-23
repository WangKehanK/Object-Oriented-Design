package game.component.entity;

/**
 * A spell class extends AbstractEntity and implement Usable and Saleable interface.
 */
public class Spell extends AbstractEntity implements Usable, Saleable{
    private int damage;
    private int mana;
    private String spellType;

    public Spell(String name, int cost, int minLevel, int damage, int mana, String spellType) {
        super(name, cost, minLevel);
        this.damage = damage;
        this.mana = mana;
        this.spellType = spellType;
    }


    @Override
    public void printInfo() {
        System.out.println(String.format("Type: Spell | name: %s, type: %s, cost: %d, minLevel: %d, damage: %d, mana: %d",
                getName(),spellType, getCost(), getMinLevel(), damage, mana));
    }

    public int getDamage() {
        return damage;
    }

    public int getMana() {
        return mana;
    }

    public String getSpellType() {
        return spellType;
    }
}
