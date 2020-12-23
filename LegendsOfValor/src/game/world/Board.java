package game.world;

import game.world.cell.Cell;

public interface Board {
    void print();
    void setCell(int i, int j, Cell cell);
    Cell get(Integer newX, Integer newY);
}
