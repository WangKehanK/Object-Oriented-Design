package game.world;

import game.component.live.hero.Hero;
import game.component.live.monster.Monster;
import game.world.cell.Cell;
import game.world.cell.InaccessibleCell;
import game.world.cell.NexusCell;
import game.world.cell.ValorCell;

import java.util.ArrayList;
import java.util.List;
/**
 * A Legends of Valor game board (grid), build up by all kinds of cells
 */
public class ValorBoard implements Board{
    private ValorCell[][] cells;
    private int boardLength;

    public ValorBoard(int boardLength) {
        this.boardLength = boardLength;
        cells = new ValorCell[boardLength][boardLength];
    }

    @Override
    public void setCell(int i, int j, Cell cell) {
        this.cells[i][j] = (ValorCell) cell;
    }

    public void print() {
        for (ValorCell[] col : cells) {
            String startStr = "";
            String midStr = "";
            String endStr = "";
            for (int i = 0; i < col.length; i++) {
                startStr += col[i].printStartLine();
                midStr += col[i].printMidLine();
                endStr += col[i].printEndLine();
                if (i != col.length - 1) {
                    startStr += "   ";
                    midStr += "   ";
                    endStr += "   ";
                }
            }

            System.out.println(startStr);
            System.out.println(midStr);
            System.out.println(endStr);
            System.out.println();
        }

    }


    /**
     * print the operation menu
     *
     * @param x
     * @param y
     */
    public void printMenu(int x, int y) {

        cells[x][y].printMenu();
        //determine is there is enemies
        List<Monster> monsterList = heroAttackList(x, y);
        if (monsterList.size() != 0) {
            System.out.println("attack(O/o)");
        }
        if (!(cells[x][y] instanceof NexusCell)) {
            System.out.println("Teleport(T/t)");
            System.out.println("Back(B/b)");
        }
    }

    /**
     * find monster
     *
     * @param x
     * @param y
     * @return
     */
    public List<Monster> heroAttackList(int x, int y) {
        List<Monster> monsterList = new ArrayList<>();
        int[] derictions = {-1, 0, 1};
        for (int derictionX : derictions) {
            for (int derictionY : derictions) {
                int newX = x + derictionX;
                int newY = y + derictionY;
                if (newX < 0 || newX >= boardLength
                        || newY < 0 || newY >= boardLength) {
                    continue;
                } else {
                    if (cells[newX][newY] instanceof InaccessibleCell) {
                        continue;
                    } else {
                        if (cells[newX][newY].containsMonster()) {
                            monsterList.add(cells[newX][newY].getMonster());
                        }
                    }
                }

            }
        }

        return monsterList;
    }

    public boolean process(int x, int y, String str) {
        return cells[x][y].roundProcessor(str);
    }

    /**
     * determine whether the cell is reachable
     *
     * @param newX
     * @param newY
     * @param hero
     * @param command
     * @return
     */
    public boolean canMove(Integer newX, Integer newY, Hero hero, String command) {
        if (cells[newX][newY] instanceof InaccessibleCell) {
            return false;
        } else {
            if(checkHeroExist(newX, newY)){
                return false;
            }

            if (command.equalsIgnoreCase("w")) {
                if (checkMonsterExist(newX + 1, newY + 1)
                        || checkMonsterExist(newX + 1, newY)
                        || checkMonsterExist(newX + 1, newY - 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * check the existence of monster
     *
     * @param x
     * @param y
     * @return
     */
    private boolean checkMonsterExist(int x, int y) {
        if (x >= 0 && x < boardLength && y >= 0 && y < boardLength) {
            return cells[x][y].containsMonster();
        }
        return false;
    }

    /**
     * check the existence of heroes
     *
     * @param x
     * @param y
     * @return
     */
    private boolean checkHeroExist(int x, int y) {
        if (x >= 0 && x < boardLength && y >= 0 && y < boardLength) {
            return cells[x][y].containsHero();
        }
        return false;
    }


    @Override
    public Cell get(Integer newX, Integer newY) {
        return cells[newX][newY];
    }

    public void setHeroPosition(int x, int y, Hero hero) {
        cells[x][y].enter(hero);
    }

    public void moveHero(Integer newX, Integer newY, Hero hero) {
        cells[hero.getX()][hero.getY()].leave(hero);
        cells[newX][newY].enter(hero);
    }

    public void setMonsterPosition(int x, int y, Monster monster) {
        cells[x][y].enter(monster);
    }

    public void moveMonster(Integer newX, Integer newY, Monster monster) {
        cells[monster.getX()][monster.getY()].leave(monster);
        cells[newX][newY].enter(monster);
    }

    public boolean containsHero(int x, int y) {
        return cells[x][y].containsHero();
    }

    public boolean existMonster(int lane, int x, int y) {
        for (int i = x; i >= 0; i--) {
            if (cells[x][lane * 3].containsMonster() || cells[x][lane * 3 + 1].containsMonster()) {
                return true;
            }
        }
        return false;
    }

    public List<Hero> monsterAttackList(int x, int y) {
        List<Hero> heroList = new ArrayList<>();
        int[] derictions = {-1, 0, 1};
        for (int derictionX : derictions) {
            for (int derictionY : derictions) {
                int newX = x + derictionX;
                int newY = y + derictionY;
                if (newX < 0 || newX >= boardLength
                        || newY < 0 || newY >= boardLength) {
                    continue;
                } else {
                    if (cells[newX][newY] instanceof InaccessibleCell) {
                        continue;
                    } else {
                        if (cells[newX][newY].containsHero()) {
                            heroList.add(cells[newX][newY].getHero());
                        }
                    }
                }

            }
        }
        return heroList;
    }

    public boolean containsMonster(int x, int y) {
        return cells[x][y].containsMonster();
    }

    public void killMonster(int x, int y, Monster monster) {
        cells[x][y].leave(monster);
    }
}
