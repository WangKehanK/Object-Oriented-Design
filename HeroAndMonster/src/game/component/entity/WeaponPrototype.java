package game.component.entity;

/**
 * A weapon prototype class, use to create Weapon when needed
 */
public class WeaponPrototype {

    private String name;
    private int cost;
    private int level;
    private int damage;
    private int hands;

    public WeaponPrototype(String name, int cost, int level, int damage, int hands) {
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.damage = damage;
        this.hands = hands;
    }

    public String getName() {
        return name;
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

    public int getHands() {
        return hands;
    }

    public void printInfo() {
        System.out.println(String.format("[Weapon] name: %s, cost: %d, minLevel: %d, damage: %d, required hands: %d",
                name, cost, level, damage, hands));
    }
}
