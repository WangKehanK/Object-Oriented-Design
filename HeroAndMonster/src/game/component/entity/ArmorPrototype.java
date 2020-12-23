package game.component.entity;

/**
 * A armor prototype class, use to create Armor when needed
 */
public class ArmorPrototype {
    private String name;
    private int cost;
    private int level;
    private int reduction;

    public ArmorPrototype(String name, int cost, int level, int reduction) {
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.reduction = reduction;
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

    public int getReduction() {
        return reduction;
    }

    public void printInfo() {
        System.out.println(String.format("[Armor] name: %s, cost: %d, minLevel: %d, damage reduction: %d",
                name, cost, level, reduction));
    }

}
