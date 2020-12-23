package game.component.live;

import game.component.entity.*;
import game.component.live.hero.Hero;
import utils.ScannerUtil;
import utils.SystemPrintUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A bag class, hero's inventory, used to store armors, weapons, etc...
 */
public class Bag {
    private List<Weapon> weaponList;
    private List<Armor> armorList;
    private List<Potion> potionList;
    private List<Spell> spellList;

    private Hero hero;

    public Bag(Hero hero) {
        weaponList = new ArrayList<>();
        armorList = new ArrayList<>();
        potionList = new ArrayList<>();
        spellList = new ArrayList<>();

        this.hero = hero;
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }

    public List<Armor> getArmorList() {
        return armorList;
    }

    public List<Potion> getPotionList() {
        return potionList;
    }

    public List<Spell> getSpellList() {
        return spellList;
    }

    public void printInfo() {
        weaponPrint();
        armorPrint();
        potionPrint();
        spellPrint();
    }

    public void spellPrint() {
        if (!spellList.isEmpty()) {
            SystemPrintUtil.printlnWithStart("Spell");
            for (Spell spell : spellList) {
                spell.printInfo();
            }
        } else {
            SystemPrintUtil.printlnWithStart("Spell");
            SystemPrintUtil.printRed("Empty");
        }
    }

    public void potionPrint() {
        if (!potionList.isEmpty()) {
            SystemPrintUtil.printlnWithStart("Potion");
            for (Potion potion : potionList) {
                potion.printInfo();
            }
        } else {
            SystemPrintUtil.printlnWithStart("Potion");
            SystemPrintUtil.printRed("Empty");
        }
    }

    public void armorPrint() {
        if (!armorList.isEmpty()) {
            SystemPrintUtil.printlnWithStart("Armor");
            for (Armor armor : armorList) {
                armor.printInfo();
            }
        } else {
            SystemPrintUtil.printlnWithStart("Armor");
            SystemPrintUtil.printRed("Empty");
        }
    }

    public void weaponPrint() {
        if (!weaponList.isEmpty()) {
            SystemPrintUtil.printlnWithStart("Weapon");
            for (Weapon weapon : weaponList) {
                weapon.printInfo();
            }
        } else {
            SystemPrintUtil.printlnWithStart("Weapon");
            SystemPrintUtil.printRed("Empty");
        }
    }


    public Weapon findWeaponByName(String name) {
        for (Weapon weapon : weaponList) {
            if (weapon.getName().equals(name)) {
                return weapon;
            }
        }
        return null;
    }

    public Armor findArmorByName(String name) {
        for (Armor armor : armorList) {
            if (armor.getName().equals(name)) {
                return armor;
            }
        }
        return null;
    }

    public Potion findPotionByName(String name) {
        for (Potion potion : potionList) {
            if (potion.getName().equals(name)) {
                return potion;
            }
        }
        return null;
    }

    public Spell findSpellByName(String name) {
        for (Spell spell : spellList) {
            if (spell.getName().equals(name)) {
                return spell;
            }
        }
        return null;
    }

    public void addWeapon(Weapon weapon) {
        weaponList.add(weapon);
    }

    public void addArmor(Armor armor) {
        armorList.add(armor);
    }

    public void addPotion(Potion potion) {
        potionList.add(potion);
    }

    public void addSpell(Spell spell) {
        spellList.add(spell);
    }


    public void removeWeapon(Weapon weapon) {
        weaponList.remove(weapon);
    }


    public void removeSpell(Spell spell) {
        spellList.remove(spell);
    }

    public void removePotion(Potion potion) {
        potionList.remove(potion);
    }

    public void removeArmor(Armor armor) {
        armorList.remove(armor);
    }

    public List<? extends Entity> getList(String type) {
        if(type.equalsIgnoreCase("Weapon")){
            return weaponList;
        }else if(type.equalsIgnoreCase("Spell")){
            return spellList;
        }else if(type.equalsIgnoreCase("Potion")){
            return potionList;
        }else if(type.equalsIgnoreCase("Armor")){
            return armorList;
        }
        return null;
    }

    public AbstractEntity findEntityByName(String input, String type) {
        if(type.equalsIgnoreCase("Weapon")){
            return findWeaponByName(input);
        }else if(type.equalsIgnoreCase("Spell")){
            return findSpellByName(input);
        }else if(type.equalsIgnoreCase("Potion")){
            return findPotionByName(input);
        }else if(type.equalsIgnoreCase("Armor")){
            return findArmorByName(input);
        }
        return null;
    }

    public void opera() {
        while (true) {
            System.out.println("change Weapon(W/w)");
            System.out.println("change Armor(A/a)");
            System.out.println("use Potion(P/p)");
            System.out.println("Sale(S/s)");
            System.out.println("finish(Q/q)");

            String input = ScannerUtil.readLine();
            if (input.equalsIgnoreCase("Q")) {
                break;
            } else if (input.equalsIgnoreCase("S")) {
                sale();
            } else if (input.equalsIgnoreCase("W")) {
                change("Weapon");
            } else if (input.equalsIgnoreCase("A")) {
                change("Armor");
            }else if (input.equalsIgnoreCase("P")) {
                if (getSpellList().size() == 0) {
                    SystemPrintUtil.printRed("Empty!");
                    return;
                }
                SystemPrintUtil.printlnWithStart("choose one potion");
                Potion potion;
                while (true) {
                    potionPrint();
                    String choose = ScannerUtil.readLine();
                    potion = findPotionByName(choose);
                    if (potion == null) {
                        SystemPrintUtil.printRed("Error!");
                    } else {
                        break;
                    }
                }

                if(potion != null){
                    hero.use(potion);
                }
            }
        }
    }

    public void change(String type) {
        SystemPrintUtil.printlnWithStart("pick a " + type);
        if(type.equalsIgnoreCase("Weapon")){
            weaponPrint();
            if(getWeaponList().size() == 0){
                return;
            }
        }else if(type.equalsIgnoreCase("Armor")){
            armorPrint();
            if(getArmorList().size() == 0){
                return;
            }
        }

        SystemPrintUtil.println("quit (Q/q)");
        Equipable equipable = null;
        while (true) {
            String choose = ScannerUtil.readLine();
            if(choose.equalsIgnoreCase("q")){
                break;
            }

            if(type.equalsIgnoreCase("Weapon")){
                equipable = findWeaponByName(choose);
            }else if(type.equalsIgnoreCase("Armor")){
                equipable = findArmorByName(choose);
            }

            if (equipable == null) {
                SystemPrintUtil.printRed("Error");
            } else {
                break;
            }
        }

        hero.changeEquipable(equipable);
    }

    private void sale() {
        while (true) {
            printInfo();
            System.out.println();
            System.out.println("choose one type:");
            System.out.println("weapon(W/w)");
            System.out.println("armor(A/a)");
            System.out.println("potion(P/p)");
            System.out.println("spell(S/s)");
            System.out.println("quit(Q/q)");

            String input = ScannerUtil.readLine();
            if (input.equalsIgnoreCase("W")) {
                saleEntity("Weapon");
            } else if (input.equalsIgnoreCase("A")) {
                saleEntity("Armor");
            } else if (input.equalsIgnoreCase("P")) {
                saleEntity("Potion");
            } else if (input.equalsIgnoreCase("S")) {
                saleEntity("Spell");
            } else if (input.equalsIgnoreCase("Q")) {
                break;
            } else {
                SystemPrintUtil.printRed("Error");
            }

        }
    }

    private void saleEntity(String type) {
        while (true) {
            SystemPrintUtil.printlnWithStart(type);
            List<? extends Entity> list = getList(type);
            if(list.size() == 0){
                SystemPrintUtil.printRed("Empty!");
                break;
            }else{
                for (Entity entity : list) {
                    entity.printInfo();
                }
            }
            System.out.println("quit(Q/q)");

            String input = ScannerUtil.readLine();
            if (input.equalsIgnoreCase("Q")) {
                break;
            }

            AbstractEntity abstractEntity = findEntityByName(input, type);
            if (abstractEntity != null) {
                hero.sale(abstractEntity,type);
                SystemPrintUtil.printBlue("Sale "+ abstractEntity.getName() + " Add " + abstractEntity.getCost()/2);
                SystemPrintUtil.printBlue("current money = " + hero.getMoney());
            } else {
                SystemPrintUtil.printRed("Error");
            }
        }
    }

}
