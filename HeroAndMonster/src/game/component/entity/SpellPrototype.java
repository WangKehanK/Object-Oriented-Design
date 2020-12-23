package game.component.entity;

/**
 * A spell prototype class, use to create Spell when needed
 */
public class SpellPrototype {
    private String name;
    private String type;
    private int cost;
    private int level;
    private int damage;
    private int manaCost;


    public SpellPrototype(String name, String type, int cost, int level, int damage, int manaCost) {
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.level = level;
        this.damage = damage;
        this.manaCost = manaCost;
    }


    public void printInfo() {
        System.out.println(String.format("[Spell] name: %s, Type: %s, cost: %d, minLevel: %d, damage: %d, mana cost: %d",
                name, type, cost, level, damage, manaCost));
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }
}
