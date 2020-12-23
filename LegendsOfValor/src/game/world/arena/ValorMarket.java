package game.world.arena;

import game.LegendsOfValorGame;
import game.component.entity.*;
import game.component.live.Bag;
import game.component.live.hero.Hero;
import utils.ScannerUtil;
import utils.SystemPrintUtil;

import java.util.List;

/**
 * A market Class, provide the market related operations for heros to buy/sell
 */
public class ValorMarket implements Market{
    private LegendsOfValorGame game;

    public ValorMarket(LegendsOfValorGame game) {
        this.game = game;
    }


    /**
     * market operations
     * @param hero
     */
    public void operaMarket(Hero hero) {
        while (true) {
            System.out.println();
            System.out.println("choose your choose:");
            System.out.println("buy something(B/b)");
            System.out.println("sale something(S/s)");
            System.out.println("quit(Q/q)");


            String input = ScannerUtil.readLine();
            if (input.equalsIgnoreCase("b")) {
                buyOpera(hero);
            } else if (input.equalsIgnoreCase("s")) {
                saleOpera(hero);
            } else if (input.equalsIgnoreCase("q")) {
                break;
            } else {
                SystemPrintUtil.printRed("Error");
            }
        }
    }

    /**
     * market operations
     * @param hero
     */
    public void buyOpera(Hero hero) {
        hero.printInfo();
        Bag bag = hero.getBag();
        while (true) {
            System.out.println();
            System.out.println("choose one type:");
            System.out.println("weapon(W/w)");
            System.out.println("armor(A/a)");
            System.out.println("potion(P/p)");
            System.out.println("spell(S/s)");
            System.out.println("quit(Q/q)");

            String input = ScannerUtil.readLine();
            if (input.equalsIgnoreCase("W")) {
                buy(hero, "Weapon");
            } else if (input.equalsIgnoreCase("A")) {
                buy(hero, "Armor");
            } else if (input.equalsIgnoreCase("P")) {
                buy(hero, "Potion");
            } else if (input.equalsIgnoreCase("S")) {
                buy(hero, "Spell");
            } else if (input.equalsIgnoreCase("Q")) {
                break;
            } else {
                SystemPrintUtil.printRed("Error");
                bag.printInfo();
            }
        }
    }


    @Override
    public void buy(Hero hero, String type) {
        while (true) {
            SystemPrintUtil.printlnWithStart(type);
            if (type.equalsIgnoreCase("Weapon")) {
                for (WeaponPrototype weaponPrototype : WeaponCache.getList()) {
                    weaponPrototype.printInfo();
                }
            } else if (type.equalsIgnoreCase("Spell")) {
                for (SpellPrototype spellPrototype : SpellCache.getList()) {
                    spellPrototype.printInfo();
                }

            } else if (type.equalsIgnoreCase("Potion")) {
                for (PotionPrototype potionPrototype : PotionCache.getList()) {
                    potionPrototype.printInfo();
                }

            } else if (type.equalsIgnoreCase("Armor")) {
                for (ArmorPrototype armorPrototype : ArmorCache.getList()) {
                    armorPrototype.printInfo();
                }
            }
            System.out.println("quit(Q/q)");
            String input = ScannerUtil.readLine();

            if (input.equalsIgnoreCase("Q")) {
                break;
            }

            AbstractEntity entity = null;

            if (type.equalsIgnoreCase("Weapon")) {
                entity = WeaponCache.getWeaponByName(input);
            } else if (type.equalsIgnoreCase("Spell")) {
                entity = SpellCache.getSpellByName(input);
            } else if (type.equalsIgnoreCase("Potion")) {
                entity = PotionCache.getPotionByName(input);
            } else if (type.equalsIgnoreCase("Armor")) {
                entity = ArmorCache.getArmorByName(input);
            }

            if (hero.canBuy(entity)) {
                hero.buy(entity, type);
                SystemPrintUtil.printBlue(String.format("Success Buy %s", entity.getName()) + " use money " + entity.getCost());
                SystemPrintUtil.printBlue("Current Money = " + hero.getMoney());
            } else {
                SystemPrintUtil.printRed("Error Name or Min Level not match or Money is not Enough");
            }
        }
    }

    private void saleOpera(Hero hero) {
        while (true) {
            hero.getBag().printInfo();
            System.out.println();
            System.out.println("choose one type:");
            System.out.println("weapon(W/w)");
            System.out.println("armor(A/a)");
            System.out.println("potion(P/p)");
            System.out.println("spell(S/s)");
            System.out.println("quit(Q/q)");

            String input = ScannerUtil.readLine();
            if (input.equalsIgnoreCase("W")) {
                saleEntity("Weapon", hero);
            } else if (input.equalsIgnoreCase("A")) {
                saleEntity("Armor", hero);
            } else if (input.equalsIgnoreCase("P")) {
                saleEntity("Potion", hero);
            } else if (input.equalsIgnoreCase("S")) {
                saleEntity("Spell", hero);
            } else if (input.equalsIgnoreCase("Q")) {
                break;
            } else {
                SystemPrintUtil.printRed("Error");
            }

        }
    }

    private void saleEntity(String type, Hero hero) {
        while (true) {
            SystemPrintUtil.printlnWithStart(type);
            List<? extends Entity> list = hero.getBag().getList(type);
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

            AbstractEntity abstractEntity = hero.getBag().findEntityByName(input, type);
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
