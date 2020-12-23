package game.component.entity;

/**
 * A Potion class extends AbstractEntity and implement Usable and Saleable interface.
 */
public class Potion extends AbstractEntity implements Usable, Saleable {
    private int attributeIncrease;
    private String attributeAffected;


    public Potion(String name, int cost, int minLevel, int attributeIncrease, String attributeAffected) {
        super(name, cost, minLevel);
        this.attributeIncrease = attributeIncrease;
        this.attributeAffected = attributeAffected;
    }

    @Override
    public void printInfo() {
        System.out.println(String.format("Type: Potion | name: %s, cost: %d, minLevel: %d, attributeIncrease: %d, attributeAffected: %s",
                getName(), getCost(), getMinLevel(), attributeIncrease, attributeAffected));
    }

    public int getAttributeIncrease() {
        return attributeIncrease;
    }

    public String getAttributeAffected() {
        return attributeAffected;
    }
}
