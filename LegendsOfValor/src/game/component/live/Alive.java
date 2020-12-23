package game.component.live;

/**
 * An alive interface, which means that the character is alive (e.g hero has hp)
 */
public interface Alive {
    boolean isAlive();

    int getHp();
}
