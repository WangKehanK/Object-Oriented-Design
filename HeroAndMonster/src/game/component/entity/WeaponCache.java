package game.component.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A weapon cache class use to cache all weapon-type entities (items).
 */
public class WeaponCache {

    private static Map<String, WeaponPrototype> map;

    static {
        map = new HashMap<>();
        load();
    }

    private static void load() {
        map.put("Sword", new WeaponPrototype("Sword", 500,1,800,1));
        map.put("Bow", new WeaponPrototype("Bow", 300,2,500,2));
        map.put("Scythe", new WeaponPrototype("Scythe", 1000,6,1100,2));
        map.put("Axe", new WeaponPrototype("Axe", 550,5,850,1));
        map.put("TSwords", new WeaponPrototype("TSwords", 1400,8,1600,2));
        map.put("Dagger", new WeaponPrototype("Dagger", 200,1,250,1));
    }


    /**
     * @return Weapon
     */
    public static Weapon getWeaponByName(String name) {
        WeaponPrototype weaponPrototype = map.get(name);

        if(weaponPrototype == null){
            return null;
        }else{
            return new Weapon(weaponPrototype.getName(), weaponPrototype.getCost(),
                    weaponPrototype.getLevel(), weaponPrototype.getDamage(), weaponPrototype.getHands());
        }
    }


    public static List<WeaponPrototype> getList() {
        return new ArrayList<>(map.values());
    }
}
