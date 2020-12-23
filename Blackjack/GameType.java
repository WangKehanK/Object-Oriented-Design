/**
 * An enumeration type, provide corresponding game iterface options
 */
public enum GameType {
    Single("1", "Black Jack Single-Player Mode"),
    Multi("2", "Black Jack Multi-Player Mode"),
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
