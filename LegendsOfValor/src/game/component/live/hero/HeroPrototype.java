package game.component.live.hero;

import utils.SystemPrintUtil;

/**
 *  A hero prototype class, use to create Hero when needed
 */
public class HeroPrototype {
    private String name;
    private int mana;
    private int strength;
    private int agility;
    private int dexterity;
    private int startingMoney;
    private int startingExperience;

    private String heroType;


    public HeroPrototype(String name, int mana, int strength, int agility, int dexterity, int startingMoney, int startingExperience, String heroType) {
        this.name = name;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.startingMoney = startingMoney;
        this.startingExperience = startingExperience;
        this.heroType = heroType;
    }

    public String getName() {
        return name;
    }

    public int getMana() {
        return mana;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getStartingMoney() {
        return startingMoney;
    }

    public int getStartingExperience() {
        return startingExperience;
    }

    public String getHeroType() {
        return heroType;
    }


    public void printHero() {
        SystemPrintUtil.println("Hero Type:" + heroType + " name: " + name + "  mana:" + mana +
                "   strength:" + strength + "   agility:" + agility + " dexterity:" + dexterity + " startingMoney:" + startingMoney +
                "   startingExperience:" + startingExperience);
    }
}
