package game.component.entity;

public class PotionPrototype {

    private String name;
    private int cost;
    private int level;
    private int attributeIncrease;
    private String attributeAffected;

    public PotionPrototype(String name, int cost, int level, int attributeIncrease, String attributeAffected) {
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.attributeIncrease = attributeIncrease;
        this.attributeAffected = attributeAffected;
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

    public int getAttributeIncrease() {
        return attributeIncrease;
    }

    public String getAttributeAffected() {
        return attributeAffected;
    }

    public void printInfo() {
        System.out.println(String.format("[Potion] name: %s, cost: %d, minLevel: %d, attribute increase: %d, attribute affected: %s",
                name, cost, level, attributeIncrease, attributeAffected));
    }
}
