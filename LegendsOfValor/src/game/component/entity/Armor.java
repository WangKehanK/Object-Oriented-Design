package game.component.entity;

/**
 * A armor class extends AbstractEntity and implement Equipable and Saleable interface.
 */
public class Armor extends AbstractEntity implements Equipable, Saleable{
    private int reduction;

    public Armor(String name, int cost, int minLevel, int reduction) {
        super(name, cost, minLevel);
        this.reduction = reduction;
    }

    @Override
    public void printInfo() {
        System.out.println(String.format("Type: Armor | name: %s, cost: %d, minLevel: %d, damageReduction: %d",
                getName(), getCost(), getMinLevel(), reduction));
    }
}
