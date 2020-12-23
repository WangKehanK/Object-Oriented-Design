package game.component.live.hero;

import game.component.entity.*;
import game.component.live.Bag;
import game.component.live.Combatable;
import utils.RandomUtil;
import utils.SystemPrintUtil;

import java.util.Objects;

/**
 *  An abstract Hero class that implement Hero and Combatable interface, including heroes' attributes and functions.
 */
public abstract class AbstractHero implements Combatable, Hero {
    private String name;
    private int heroNo;
    private int level;
    private int hp;
    private int mana;
    private int money;
    private int experience;

    private int strength;
    private int dexterity;
    private int agility;
    private int realStrength;
    private int realDexterity;
    private int realAgility;
    private int defense;

    private Weapon weapon;
    private Armor armor;

    private Bag bag;

    private int x;
    private int y;

    public AbstractHero(String name, int mana, int strength, int dexterity, int agility, int money, int experience) {
        this.name = name;
        this.mana = mana;
        this.strength = strength;
        this.realStrength = strength;
        this.dexterity = dexterity;
        this.realDexterity = dexterity;
        this.agility = agility;
        this.realAgility = agility;
        this.money = money;
        this.experience = experience;
        this.level = 1;
        this.hp = level * 100;
        this.defense = 0;
        this.bag = new Bag(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractHero that = (AbstractHero) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public int getMana() {
        return mana;
    }

    public int getMoney() {
        return money;
    }

    public int getExperience() {
        return experience;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public int getDefense() {
        return defense;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    @Override
    public void printInfo() {
        System.out.println(String.format("name : %s ", name));
        System.out.println(String.format("level : %d ", level));
        System.out.println(String.format("hp : %d ", hp));
        System.out.println(String.format("mana : %d ", mana));
        System.out.println(String.format("strength : %d ", strength));
        System.out.println(String.format("dexterity : %d ", dexterity));
        System.out.println(String.format("agility : %d ", agility));
        System.out.println(String.format("defense : %d ", defense));
        System.out.println(String.format("money : %d ", money));
        System.out.println(String.format("experience : %d ", experience));
        System.out.println("--------------------");
        if (weapon != null) {
            weapon.printInfo();
        } else {
            System.out.println("Weapon Empty!");
        }
        System.out.println("--------------------");
        if (armor != null) {
            armor.printInfo();
        } else {
            System.out.println("Armor Empty!");
        }
        System.out.println("--------------------");

        printBagInfo();

    }

    public void printBagInfo() {
        System.out.println(String.format("----------Bag----------"));
        bag.printInfo();
    }

    @Override
    public boolean isAlive() {
        return this.hp > 0;
    }

    @Override
    public void revive() {
        hp = level * 100 / 2;
    }

    @Override
    public void victory(int level) {
        SystemPrintUtil.printBlue("win money " + (100*level));
        SystemPrintUtil.printBlue("win experience " + 2);
        money += 100 * level;
        experience += 2;
        SystemPrintUtil.printBlue("current money " + money);
        SystemPrintUtil.printBlue("current experience " + experience);
        if (experience >= level * 10) {
            levelUp();
        }
    }

    @Override
    public void fail() {
        money = money / 2;
    }

    @Override
    public void restore() {
        hp = (int) (hp * 1.1);
        mana = (int) (mana * 1.1);
    }

    @Override
    public Bag getBag() {
        return bag;
    }


    @Override
    public void use(Usable usable) {
        if(usable instanceof Potion){
            usePotion((Potion) usable);
        }

    }

    public void usePotion(Potion potion) {
        if (potion.getAttributeAffected().contains("Health")) {
            hp += potion.getAttributeIncrease();
        } else if (potion.getAttributeAffected().contains("Mana")) {
            mana += potion.getAttributeIncrease();
        } else if (potion.getAttributeAffected().contains("Strength")) {
            strength += potion.getAttributeIncrease();
        } else if (potion.getAttributeAffected().contains("Dexterity")) {
            dexterity += potion.getAttributeIncrease();
        } else if (potion.getAttributeAffected().contains("Defense")) {
            defense += potion.getAttributeIncrease();
        } else if (potion.getAttributeAffected().contains("Agility")) {
            agility += potion.getAttributeIncrease();
        }
        bag.removePotion(potion);

    }

    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public int attackNumber() {
        return (int) ((strength + (weapon == null ? 0 : weapon.getDamage())) * 0.05);
    }

    @Override
    public void defense(int i) {
        int chance = RandomUtil.nextInt(100);
        if (chance < agility * 0.002) {
            SystemPrintUtil.printBlue("dodging the attack");
            return;
        } else {
            int number = i > getDefense() * 0.05 ? (int) (i - getDefense() * 0.05) : 0;
            hp -= number;
            if (hp < 0) {
                hp = 0;
            }
            SystemPrintUtil.printBlue(" Hero hp -" + number + " now hp:" + hp);
        }
    }

    @Override
    public void printName() {
        System.out.println(name);
    }

    @Override
    public void sale(Saleable saleable, String type) {
        if (type.equalsIgnoreCase("Weapon")) {
            bag.removeWeapon((Weapon) saleable);
        } else if (type.equalsIgnoreCase("Spell")) {
            bag.removeSpell((Spell) saleable);
        } else if (type.equalsIgnoreCase("Potion")) {
            bag.removePotion((Potion) saleable);
        } else if (type.equalsIgnoreCase("Armor")) {
            bag.removeArmor((Armor) saleable);
        }
        this.money += (saleable.getCost() / 2);
    }

    @Override
    public boolean canBuy(AbstractEntity entity) {
        if (entity == null) {
            return false;
        }
        if (money < entity.getCost() || level < entity.getMinLevel()) {
            return false;
        }
        return true;
    }

    @Override
    public void buy(Saleable saleable, String type) {
        if (type.equalsIgnoreCase("Weapon")) {
            bag.addWeapon((Weapon) saleable);
        } else if (type.equalsIgnoreCase("Spell")) {
            bag.addSpell((Spell) saleable);
        } else if (type.equalsIgnoreCase("Potion")) {
            bag.addPotion((Potion) saleable);
        } else if (type.equalsIgnoreCase("Armor")) {
            bag.addArmor((Armor) saleable);
        }
        this.money -= saleable.getCost();
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void levelUp(){
        setExperience(getExperience() - getLevel() * 10);
        setLevel(getLevel() + 1);

        setHp(getLevel() * 100);
        setMana((int) (getMana() * 1.1));
    }

    @Override
    public void changeEquipable(Equipable equipable) {
        if(equipable instanceof  Weapon){
            if (this.weapon != null) {
                bag.addWeapon(this.weapon);
            }
            this.weapon = (Weapon) equipable;
            bag.removeWeapon(weapon);

            SystemPrintUtil.printBlue("Equiped Weapon [" + equipable.getName() + "]");
        }else if(equipable instanceof Armor){
            if (this.armor != null) {
                bag.addArmor(this.armor);
            }
            this.armor = (Armor) equipable;
            bag.removeArmor(armor);
            SystemPrintUtil.printBlue("Equiped Armor [" + equipable.getName() + "]");
        }
    }

    @Override
    public int getNo() {
        return heroNo;
    }

    @Override
    public void setNo(int heroNo) {
        this.heroNo = heroNo;
    }

    @Override
    public int getRealDexterity() {
        return this.realDexterity;
    }

    @Override
    public int getRealAgility() {
        return this.realAgility;
    }

    @Override
    public int getRealStrength() {
        return this.realStrength;
    }

    public void setRealStrength(int realStrength) {
        this.realStrength = realStrength;
    }

    public void setRealDexterity(int realDexterity) {
        this.realDexterity = realDexterity;
    }

    public void setRealAgility(int realAgility) {
        this.realAgility = realAgility;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void move(Integer newX, Integer newY) {
        setPosition(newX, newY);
    }
}
