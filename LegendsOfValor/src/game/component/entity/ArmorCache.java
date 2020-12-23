package game.component.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A armor cache class use to cache all armor-type entities (items).
 */
public class ArmorCache {


    private static Map<String, ArmorPrototype> map;

    static{
        map = new HashMap<>();
        loadArmor();
    }

    private static void loadArmor() {
        map.put("Platinum_Shield",new ArmorPrototype("Platinum_Shield",150,1,200));
        map.put("Breastplate",new ArmorPrototype("Breastplate", 350,3,600));
        map.put("Full_Body_Armor",new ArmorPrototype("Full_Body_Armor",1000,8,1100));
        map.put("Wizard_Shield",new ArmorPrototype("Wizard_Shield",1200,10,1500));
        map.put("Speed_Boots",new ArmorPrototype("Speed_Boots",550,4,600));
        map.put("Guardian_Angel",new ArmorPrototype("Guardian_Angel",1000,10,1000));
    }


    /**
     * @return Armor
     */
    public static Armor getArmorByName(String name) {
        ArmorPrototype armorPrototype = map.get(name);
        if(armorPrototype == null){
            return null;
        }else{
            return new Armor(armorPrototype.getName(), armorPrototype.getCost(), armorPrototype.getLevel(), armorPrototype.getReduction());
        }
    }

    public static List<ArmorPrototype> getList() {
        return new ArrayList<>(map.values());
    }
}
