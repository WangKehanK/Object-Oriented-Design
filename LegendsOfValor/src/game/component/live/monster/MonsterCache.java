package game.component.live.monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A monster cache class use to cache all monsters.
 */
public class MonsterCache {

    private static Map<String, MonsterPrototype> map;

    private static final String DRAGON = "Dragon";
    private static final String EXOSKELETON = "Exoskeleton";
    private static final String SPIRIT = "Spirit";

    static{
        map = new HashMap<>();
        loadMonsters();
    }
    private static void loadMonsters(){
        // DRAGON
        map.put("Desghidorrah",new MonsterPrototype("Desghidorrah",MonsterCache.DRAGON, 3,300,400,35));
        map.put("Chrysophylax",new MonsterPrototype("Chrysophylax",MonsterCache.DRAGON, 2,200,500,20));
        map.put("BunsenBurner",new MonsterPrototype("BunsenBurner",MonsterCache.DRAGON, 4,400,500,45));
        map.put("Natsunomeryu",new MonsterPrototype("Natsunomeryu",MonsterCache.DRAGON, 1,100,200,10));
        map.put("TheScaleless",new MonsterPrototype("TheScaleless",MonsterCache.DRAGON, 7,700,600,75));
        map.put("Kas_Ethelinh",new MonsterPrototype("Kas-Ethelinh",MonsterCache.DRAGON, 5,600,500,60));
        map.put("Alexstraszan",new MonsterPrototype("Alexstraszan",MonsterCache.DRAGON, 10,1000,9000,55));
        map.put("Phaarthurnax",new MonsterPrototype("Phaarthurnax",MonsterCache.DRAGON, 6,600,700,60));
        map.put("D_Maleficent",new MonsterPrototype("D-Maleficent",MonsterCache.DRAGON, 9,900,950,85));
        map.put("TheWeatherbe",new MonsterPrototype("TheWeatherbe",MonsterCache.DRAGON, 8,800,900,80));
        map.put("Igneel",new MonsterPrototype("Igneel",MonsterCache.DRAGON, 6,600,400,68));
        map.put("BlueEyesWhite",new MonsterPrototype("BlueEyesWhite",MonsterCache.DRAGON, 9,900,600,75));

        //EXOSKELETON
        map.put("Cyrrollalee",new MonsterPrototype("Cyrrollalee",MonsterCache.EXOSKELETON, 7,700,800,75));
        map.put("Brandobaris",new MonsterPrototype("Brandobaris",MonsterCache.EXOSKELETON, 3,350,450,30));
        map.put("BigBad_Wolf",new MonsterPrototype("BigBad-Wolf",MonsterCache.EXOSKELETON, 1,150,250,15));
        map.put("WickedWitch",new MonsterPrototype("WickedWitch",MonsterCache.EXOSKELETON, 2,250,350,25));
        map.put("Aasterinian",new MonsterPrototype("Aasterinian",MonsterCache.EXOSKELETON, 4,400,500,45));
        map.put("Chronepsish",new MonsterPrototype("Chronepsish",MonsterCache.EXOSKELETON, 6,650,750,60));
        map.put("Kiaransalee",new MonsterPrototype("Kiaransalee",MonsterCache.EXOSKELETON, 8,850,950,85));
        map.put("St_Shargaas",new MonsterPrototype("St-Shargaas",MonsterCache.EXOSKELETON, 5,550,650,55));
        map.put("Merrshaullk",new MonsterPrototype("Merrshaullk",MonsterCache.EXOSKELETON, 10,1000,900,55));
        map.put("St_Yeenoghu",new MonsterPrototype("St-Yeenoghu ",MonsterCache.EXOSKELETON, 9,950,850,90));
        map.put("DocOck",new MonsterPrototype("DocOck",MonsterCache.EXOSKELETON, 6,600,600,55));
        map.put("Exodia",new MonsterPrototype("Exodia",MonsterCache.EXOSKELETON, 10,1000,1000,50));


        //SPIRIT
        map.put("Andrealphus",new MonsterPrototype("Andrealphus",MonsterCache.SPIRIT, 2,600,500,40));
        map.put("Aim_Haborym",new MonsterPrototype("Aim-Haborym",MonsterCache.SPIRIT, 1,450,350,35));
        map.put("Andromalius",new MonsterPrototype("Andromalius",MonsterCache.SPIRIT, 3,550,450,25));
        map.put("Chiang_shih",new MonsterPrototype("Chiang-shih ",MonsterCache.SPIRIT, 4,700,600,40));
        map.put("FallenAngel",new MonsterPrototype("FallenAngel",MonsterCache.SPIRIT, 5,800,700,50));
        map.put("Ereshkigall",new MonsterPrototype("Ereshkigall",MonsterCache.SPIRIT, 6,950,450,35));
        map.put("Melchiresas",new MonsterPrototype("Melchiresas",MonsterCache.SPIRIT, 7,350,150,75));
        map.put("Jormunngand",new MonsterPrototype("Jormunngand",MonsterCache.SPIRIT, 8,600,900,28));
        map.put("Rakkshasass",new MonsterPrototype("Rakkshasass",MonsterCache.SPIRIT,9,550,600,20 ));
        map.put("Taltecuhtli",new MonsterPrototype("Taltecuhtli",MonsterCache.SPIRIT, 10,300,200,50));
        map.put("Casper",new MonsterPrototype("Casper",MonsterCache.SPIRIT, 1,100,100,35));
    }

    public static Monster createObject(MonsterPrototype monsterPrototype) {
        if(monsterPrototype.getType().equals(DRAGON)){
            return new Dragon(monsterPrototype.getName(), monsterPrototype.getLevel(), monsterPrototype.getDamage(), monsterPrototype.getDefense(), monsterPrototype.getDodgeChance());
        }else if(monsterPrototype.getType().equals(EXOSKELETON)){
            return new Exoskeleton(monsterPrototype.getName(), monsterPrototype.getLevel(), monsterPrototype.getDamage(), monsterPrototype.getDefense(), monsterPrototype.getDodgeChance());
        }else if(monsterPrototype.getType().equals(SPIRIT)){
            return new Spirit(monsterPrototype.getName(), monsterPrototype.getLevel(), monsterPrototype.getDamage(), monsterPrototype.getDefense(), monsterPrototype.getDodgeChance());
        }
        return null;
    }

    public static List<MonsterPrototype> getList() {
        return new ArrayList<>(map.values());
    }
}
