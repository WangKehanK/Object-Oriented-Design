package game.component.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A potion cache class use to cache all potion-type entities (items).
 */
public class PotionCache {
    private static Map<String, PotionPrototype> map;

    static {
        map = new HashMap<>();
        load();
    }

    private static void load() {
        map.put("Healing_Potion", new PotionPrototype("Healing_Potion", 250, 1, 100, "Health"));
        map.put("Strength_Potion", new PotionPrototype("Strength_Potion", 200, 1, 75, "Strength"));
        map.put("Magic_Potion", new PotionPrototype("Magic_Potion", 350, 2, 100, "Mana"));
        map.put("Luck_Elixir", new PotionPrototype("Luck_Elixir", 500, 4, 65, "Agility"));
        map.put("Mermaid_Tears", new PotionPrototype("Mermaid_Tears", 850, 5, 100, "Health/Mana/Strength/Agility"));
        map.put("Ambrosia", new PotionPrototype("Ambrosia", 1000, 8, 150, "All Health/Mana/Strength/Dexterity/Defense/Agility"));
    }


    /**
     * @return Potion
     */
    public static Potion getPotionByName(String name) {

        PotionPrototype potionPrototype = map.get(name);

        if(potionPrototype == null){
            return null;
        }else{
            return new Potion(potionPrototype.getName(), potionPrototype.getCost(), potionPrototype.getLevel(),
                    potionPrototype.getAttributeIncrease(), potionPrototype.getAttributeAffected());
        }
    }

    public static List<PotionPrototype> getList() {
        return new ArrayList<>(map.values());
    }
}
