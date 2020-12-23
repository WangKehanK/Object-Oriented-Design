package game.component.live.hero;


import utils.SystemPrintUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * A hero cache class use to cache all heroes.
 */
public class HeroCache {
    private static Map<String, HeroPrototype> map;

    private static final String WARRIOR = "Warrior";
    private static final String SORCERER = "Sorcerer";
    private static final String PALADIN = "Paladin";

    static{
        map = new HashMap<>();
        loadHeroes();
    }
    private static void loadHeroes(){
        //WARRIOR
        map.put("Gaerdal_Ironhand",new HeroPrototype("Gaerdal_Ironhand", 100, 700, 500, 600, 1354, 7, HeroCache.WARRIOR));
        map.put("Sehanine_Monnbow",new HeroPrototype("Sehanine_Monnbow", 600, 700, 800, 500, 2500, 8, HeroCache.WARRIOR));
        map.put("Muamman_Duathall",new HeroPrototype("Muamman_Duathall", 300, 900, 500, 750, 2546, 6, HeroCache.WARRIOR));
        map.put("Flandal_Steelskin",new HeroPrototype("Flandal_Steelskin", 200, 750, 650, 700, 2500, 7, HeroCache.WARRIOR));
        map.put("Undefeated_Yoj",new HeroPrototype("Undefeated_Yoj", 400, 800, 400, 700, 2500, 7, HeroCache.WARRIOR));
        map.put("Eunoia_Cyn",new HeroPrototype("Eunoia_Cyn", 400, 700, 800, 600, 2500, 6, HeroCache.WARRIOR));
        //SORCERER
        map.put("Rillifane_Rallathil",new HeroPrototype("Rillifane_Rallathil", 1300, 750, 450, 500, 2500, 9, HeroCache.SORCERER));
        map.put("Segojan_Earthcaller",new HeroPrototype("Segojan_Earthcaller", 900, 800, 500, 650, 2500, 5, HeroCache.SORCERER));
        map.put("Reign_Havoc",new HeroPrototype("Reign_Havoc", 800, 800, 800, 800, 2500, 8, HeroCache.SORCERER));
        map.put("Reverie_Ashels",new HeroPrototype("Reverie_Ashels", 900, 800, 700, 400, 2500, 7, HeroCache.SORCERER));
        map.put("Radiant_Ash",new HeroPrototype("Radiant_Ash", 800, 850, 400, 600, 2500, 6, HeroCache.SORCERER));
        map.put("Skye_Soar",new HeroPrototype("Skye_Soar", 1000, 700, 400, 500, 2500, 5, HeroCache.SORCERER));
        //PALADIN
        map.put("Solonor_Thelandira",new HeroPrototype("Solonor_Thelandira", 300, 750, 650, 700, 2500, 7, HeroCache.PALADIN));
        map.put("Sehanine_Moonbow",new HeroPrototype("Sehanine_Moonbow", 300, 750, 700, 700, 2500, 7, HeroCache.PALADIN));
        map.put("Skoraeus_Stonebones",new HeroPrototype("Skoraeus_Stonebones", 250, 650, 600, 350, 2500, 4, HeroCache.PALADIN));
        map.put("Garl_Glittergold",new HeroPrototype("Garl_Glittergold", 100, 600, 500, 400, 2500, 5, HeroCache.PALADIN));
        map.put("Amaryllis_Astra",new HeroPrototype("Amaryllis_Astra", 500, 500, 500, 500, 2500, 5, HeroCache.PALADIN));
        map.put("Caliber_Heist",new HeroPrototype("Caliber_Heist", 400, 400, 400, 400, 2500, 2, HeroCache.PALADIN));
    }

    /**
     * According to the name input, get the relative hero
     *
     * @param name
     * @return null or hero
     */
    public static Hero getHeroObjectByName(String name) {
        if (name == null || name.trim().equals("")) {
            return null;
        }

        HeroPrototype heroPrototype = map.get(name);
        if(heroPrototype == null){
            return null;
        }
        if (heroPrototype.getHeroType().equals(WARRIOR)) {
            return new Warrior(heroPrototype.getName(), heroPrototype.getMana(), heroPrototype.getStrength(),
                    heroPrototype.getDexterity(), heroPrototype.getAgility(), heroPrototype.getStartingMoney(), heroPrototype.getStartingExperience());
        } else if (heroPrototype.getHeroType().equals(SORCERER)) {
            return new Sorcerer(heroPrototype.getName(), heroPrototype.getMana(), heroPrototype.getStrength(),
                    heroPrototype.getDexterity(), heroPrototype.getAgility(), heroPrototype.getStartingMoney(), heroPrototype.getStartingExperience());
        } else if (heroPrototype.getHeroType().equals(PALADIN)) {
            return new Paladin(heroPrototype.getName(), heroPrototype.getMana(), heroPrototype.getStrength(),
                    heroPrototype.getDexterity(), heroPrototype.getAgility(), heroPrototype.getStartingMoney(), heroPrototype.getStartingExperience());
        }
        return null;
    }

    public static void print() {
        for (HeroPrototype heroPrototype : map.values()) {
            heroPrototype.printHero();
        }
    }
}
