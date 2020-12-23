package game.component.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A spell cache class use to cache all spell-type entities (items).
 */
public class SpellCache {

    private static Map<String, SpellPrototype> map;

    public static final String ICE = "Ice";
    public static final String FIRE = "Fire";
    public static final String LIGHTNING = "Lightning";

    static {
        map = new HashMap<>();
        load();
    }

    private static void load() {
        map.put("Snow_Cannon", new SpellPrototype("Snow_Cannon", SpellCache.ICE, 500, 2, 650, 250));
        map.put("Ice_Blade", new SpellPrototype("Ice_Blade", SpellCache.ICE, 250, 1, 450, 100));
        map.put("Frost_Blizzard", new SpellPrototype("Frost_Blizzard", SpellCache.ICE, 750, 5, 850, 350));
        map.put("Arctic_Storm", new SpellPrototype("Arctic_Storm", SpellCache.ICE, 700, 6, 800, 300));

        map.put("Flame_Tornado", new SpellPrototype("Flame_Tornado", SpellCache.FIRE, 700, 4, 850, 300));
        map.put("Breath_of_Fire", new SpellPrototype("Breath_of_Fire", SpellCache.FIRE, 350, 1, 450, 100));
        map.put("Heat_Wave", new SpellPrototype("Heat_Wave", SpellCache.FIRE, 450, 2, 600, 150));
        map.put("Lava_Comet", new SpellPrototype("Lava_Comet", SpellCache.FIRE, 800, 7, 1000, 550));
        map.put("Hell_Storm", new SpellPrototype("Hell_Storm", SpellCache.FIRE, 600, 3, 950, 600));

        map.put("Lightning_Dagger", new SpellPrototype("Lightning_Dagger", SpellCache.LIGHTNING, 400, 1, 500, 150));
        map.put("Thunder_Blast", new SpellPrototype("Thunder_Blast", SpellCache.LIGHTNING, 750, 4, 950, 400));
        map.put("Electric_Arrows", new SpellPrototype("Electric_Arrows", SpellCache.LIGHTNING, 550, 5, 650, 200));
        map.put("Spark_Needles", new SpellPrototype("Spark_Needles", SpellCache.LIGHTNING, 500, 2, 600, 200));
    }

    /**
     * @return Spell
     */
    public static Spell getSpellByName(String name) {
        SpellPrototype spellPrototype = map.get(name);

        if(spellPrototype == null){
            return null;
        }else{
            return new Spell(spellPrototype.getName(), spellPrototype.getCost(), spellPrototype.getLevel(),
                    spellPrototype.getDamage(), spellPrototype.getManaCost(), spellPrototype.getType());
        }
    }



    public static List<SpellPrototype> getList() {
        return new ArrayList<>(map.values());
    }
}
