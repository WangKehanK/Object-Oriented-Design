package game.component.live.monster;

/**
 * A monster prototype class, use to create monster when needed
 */
public class MonsterPrototype {
    private String name;
    private String type;
    private int level;

    private int damage;
    private int defense;
    private int dodgeChance;

    public MonsterPrototype(String name, String type,int level, int damage, int defense, int dodgeChance) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.damage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }
}
