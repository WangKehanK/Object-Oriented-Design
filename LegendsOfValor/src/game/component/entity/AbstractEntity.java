package game.component.entity;

/**
 * An abstract Entity class, that holds the name, cost, and the minimum level (request) of an entity (item).
 */
public abstract class AbstractEntity implements Entity, Saleable{
    private String name;
    private int cost;
    private int minLevel;

    public AbstractEntity(String name, int cost, int minLevel) {
        this.name = name;
        this.cost = cost;
        this.minLevel = minLevel;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getMinLevel() {
        return minLevel;
    }

}
