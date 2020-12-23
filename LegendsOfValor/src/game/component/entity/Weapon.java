package game.component.entity;

/**
 * A weapon class extends AbstractEntity and implement Equipable and Saleable interface.
 */
public class Weapon extends AbstractEntity  implements Equipable, Saleable{
    private int damage;
    private int hands;

    public Weapon(String name, int cost, int minLevel, int damage, int hands) {
        super(name, cost, minLevel);
        this.damage = damage;
        this.hands = hands;
    }


    @Override
    public void printInfo() {
        System.out.println(String.format("Type: Weapon | name: %s, cost: %d, minLevel: %d, damage: %d, hands: %d]",
                getName(), getCost(), getMinLevel(), damage, hands));
    }

    public int getDamage() {
        return this.damage;
    }
}
