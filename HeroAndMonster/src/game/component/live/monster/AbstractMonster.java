package game.component.live.monster;

import game.component.entity.SpellCache;
import utils.RandomUtil;
import utils.SystemPrintUtil;

/**
 * A basic Monster interface extends Alive interface.
 */
public abstract class AbstractMonster implements Monster {
    private String name;
    private int level;
    private int hp;

    private int damage;
    private int defense;
    private int dodgeChance;

    public AbstractMonster(String name, int level, int damage, int defense, int dodgeChance) {
        this.name = name;
        this.level = level;
        this.hp = level * 100;
        this.damage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }


    @Override
    public boolean isAlive() {
        return this.hp > 0;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
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

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    @Override
    public void defense(int attackNumber) {
        int chance = RandomUtil.nextInt(100);
        if (chance < dodgeChance * 0.01) {
            //the condition dodge the attack
            SystemPrintUtil.printBlue("dodging the attack");
            return;
        } else {
            int number = attackNumber > defense * 0.05 ? (int) (attackNumber - (defense * 0.05)) : 0;
            hp -= number;
            SystemPrintUtil.printBlue(" Monster hp -" + number + " now hp:" + hp);
            if (hp < 0) {
                hp = 0;
            }
        }
    }

    @Override
    public void printInfo() {
        SystemPrintUtil.printlnWithStart("Monster Info");
        System.out.println("name : " + name);
        System.out.println("lv : " + level);
        System.out.println("hp : " + hp);
        System.out.println("baseDamage : " + damage);
        System.out.println("defenseStat : " + defense);
        System.out.println("dodgeChance : " + dodgeChance);
        SystemPrintUtil.printlnWithEnd();
    }


    @Override
    public void fired() {
        defense = (int) (defense * 0.9);
    }

    @Override
    public void iced() {
        damage = (int) (damage * 0.9);
    }

    @Override
    public void lighted() {
        dodgeChance = (int) (dodgeChance * 0.9);

    }
}
