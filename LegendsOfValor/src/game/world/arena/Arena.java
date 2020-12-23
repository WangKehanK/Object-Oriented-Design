package game.world.arena;

import game.component.entity.*;
import game.component.live.Bag;
import game.component.live.HeroTeam;
import game.component.live.hero.Hero;
import game.component.live.monster.Monster;
import utils.ScannerUtil;
import utils.SystemPrintUtil;

import java.util.List;

/**
 *  An Arena(Battle ground) Class, provide the battle ground related operations for heroes to fight
 */
public class Arena {

    private List<Hero> heroList;
    private List<Monster> monsterList;

    public Arena(List<Hero> heroList, List<Monster> monsterList) {
        this.heroList = heroList;
        this.monsterList = monsterList;
    }

    public void battle() {
        //loop each round
        while (true) {
            SystemPrintUtil.printlnWithStart("new round");
            SystemPrintUtil.println("");
            SystemPrintUtil.println("          Hero       ");
            for(Hero hero: heroList){
                hero.printInfo();
            }
            SystemPrintUtil.println("");
            SystemPrintUtil.println("          Monster       ");
            for(Monster monster: monsterList){
                monster.printInfo();
            }
            SystemPrintUtil.println("");
            if (!isHeroAlive() || !isMonsterAlive()) {
                break;
            } else {
                for (int i = 0; i < heroList.size(); i++) {
                    Hero hero = heroList.get(i);
                    battleChoose(hero, i);
                }

                for (int i = 0; i < monsterList.size(); i++) {
                    for(int j = 0; j < monsterList.size(); j++){
                        int index = i + j;
                        if(i  >= monsterList.size() - j){
                            index = index - monsterList.size();
                        }

                        if(heroList.get(index).isAlive()){
                            heroList.get(index).defense((int) (monsterList.get(i).getDamage() * 0.05));
                        }else{
                            continue;
                        }
                    }

                }

                //for alive heroes, restore hp and mana
                for (Hero hero : heroList) {
                    if (hero.isAlive()) {
                        hero.restore();
                    }
                }
            }
        }
        if (!isHeroAlive()) {
            monsterVictory();
        }
        if (!isMonsterAlive()) {
            heroVictory();
        }
    }

    private void battleChoose(Hero hero, int index) {
        while (true) {
            System.out.println("this round your opera:");
            System.out.println("        current hero status");
            hero.printInfo();
            printMenu();
            String str = ScannerUtil.readLine();

            if (str.equalsIgnoreCase("a")) {
                attack(hero, index);
                break;
            } else if (str.equalsIgnoreCase("s")) {
                spell(hero, index);
                break;
            } else if (str.equalsIgnoreCase("p")) {
                potion(hero);
                break;
            } else if (str.equalsIgnoreCase("c")) {
                change(hero);
                break;
            } else if (str.equalsIgnoreCase("i")) {
                printInfo();
            } else {
                SystemPrintUtil.printRed("Error Command");
            }
        }
    }

    private void printInfo() {
        SystemPrintUtil.printlnWithStart("Hero");
        for(Hero hero : heroList){
            hero.printInfo();
        }
        SystemPrintUtil.printlnWithStart("Monster");
        for(Monster monster : monsterList){
            monster.printInfo();
        }
    }

    private void change(Hero hero) {
        hero.printBagInfo();
        Bag bag = hero.getBag();

        while (true) {
            System.out.println("change Weapon(W/w)");
            System.out.println("change Armor(A/a)");
            System.out.println("finish(Q/q)");

            String str = ScannerUtil.readLine();
            if (str.equalsIgnoreCase("Q")) {
                break;
            } else if (str.equalsIgnoreCase("W")) {
                bag.change("Weapon");
            } else if (str.equalsIgnoreCase("A")) {
                bag. change("Armor");
            }
        }
    }

    private void potion(Hero hero) {
        Bag bag = hero.getBag();
        if (bag.getSpellList().size() == 0) {
            SystemPrintUtil.printRed("Empty!");
            return;
        }
        SystemPrintUtil.printlnWithStart("choose one potion");
        Potion potion = null;
        while (true) {
            bag.potionPrint();
            System.out.println("quit(Q/q)");
            String choose = ScannerUtil.readLine();
            if(choose.equalsIgnoreCase("Q")){
                break;
            }
            potion = bag.findPotionByName(choose);
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

    private void spell(Hero hero, int index) {
        Bag bag = hero.getBag();
        List<Spell> spellList = bag.getSpellList();
        if (spellList.size() == 0) {
            SystemPrintUtil.printRed("Empty!");
            return;
        }
        SystemPrintUtil.printlnWithStart("choose one Spell");
        while (true) {
            bag.spellPrint();
            System.out.println("quit(Q/q)");

            String choose = ScannerUtil.readLine();
            if (choose.equalsIgnoreCase("q")) {
                break;
            }
            Spell spell = bag.findSpellByName(choose);
            if (spell != null) {
                if(hero.getMana() < spell.getMana()){
                    SystemPrintUtil.printRed("Mana is not enough!");
                    return;
                }
                int attackNumber = (int) ((spell.getDamage() + (hero.getDexterity() / 10000) * spell.getDamage()) * 0.05);
                for (int i = 0; i < monsterList.size(); i++) {
                    int j = index + i;
                    if(!monsterList.get(i).isAlive()){
                        continue;
                    }
                    if (j >= monsterList.size()) {
                        j = index + i - monsterList.size();
                    }

                    hero.setMana(hero.getMana() - spell.getMana());
                    monsterList.get(j).defense(attackNumber);

                    if (spell.getSpellType().equalsIgnoreCase(SpellCache.FIRE)) {
                        monsterList.get(j).fired();
                    } else if (spell.getSpellType().equals(SpellCache.ICE)) {
                        monsterList.get(j).iced();
                    } else if (spell.getSpellType().equals(SpellCache.LIGHTNING)) {
                        monsterList.get(j).lighted();
                    }
                }
                bag.removeSpell(spell);
            } else {
                SystemPrintUtil.printRed("Error!");
            }

        }
    }

    private void attack(Hero hero, int index) {
        int attackNumber = hero.attackNumber();
        for (int i = 0; i < monsterList.size(); i++) {
            int j = index + i;
            if(!monsterList.get(i).isAlive()){
                continue;
            }
            if (j >= monsterList.size()) {
                j = index + i - monsterList.size();
            }

            monsterList.get(j).defense(attackNumber);
        }
    }

    private void printMenu() {
        System.out.println("Attack(A/a)");
        System.out.println("Spell(S/s)");
        System.out.println("Potion(P/p)");
        System.out.println("Change(C/c)");
        System.out.println("Information(I/i)");
    }

    /**
     * hero victory
     */
    private void heroVictory() {
        System.out.println("");
        SystemPrintUtil.printBlue("Hero victory");
        System.out.println("");
        for (Hero hero : heroList) {
            if (hero.getHp() == 0) {
                hero.revive();
            } else {
                hero.victory(monsterList.get(0).getLevel());
            }
        }
    }

    /**
     * monster victory
     */
    private void monsterVictory() {
        System.out.println("");
        SystemPrintUtil.printBlue("Monster victory");
        System.out.println("");
        for (Hero hero : heroList) {
            hero.fail();
        }
    }

    /**
     * determine whether any alive monsters
     * @return
     */
    private boolean isMonsterAlive() {
        for (Monster monster : monsterList) {
            if (monster.isAlive()) {
                return true;
            }
        }
        return false;
    }

    /**
     * determine whether any alive heroes
     * @return
     */
    private boolean isHeroAlive() {
        for (Hero hero : heroList) {
            if (hero.isAlive()) {
                return true;
            }
        }
        return false;
    }
}
