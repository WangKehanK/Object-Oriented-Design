package game.component.live;

/**
 * A combatable interface, which means that character is combatable (e.g hero battle with monster)
 */
public interface Combatable {
    void defense(int attackNumber);

}
