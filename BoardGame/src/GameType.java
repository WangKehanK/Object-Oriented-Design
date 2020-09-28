/**
 * Game Type - An enumeration type, provide game interface options
 */
public enum GameType {
    TicTacToe("1", "TicTacToe"),
    OrderAndChaos("2", "OrderAndChaos"),
    Exit("3", "Exit");

    private final String gameType;
    private final String gameName;

    GameType(String gameType, String gameName) {
        this.gameType = gameType;
        this.gameName = gameName;
    }

    public String getGameType() {
        return gameType;
    }

    public String getGameName() {
        return gameName;
    }

    @Override
    public String toString() {
        return gameType + ": " + gameName + ";";
    }
}
