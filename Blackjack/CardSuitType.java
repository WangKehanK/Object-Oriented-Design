/**
 * An enum type that provides 4 suit types to poker game, Spade, Heart, and etc.
 */
public enum CardSuitType {
    SPADE("Spade", "♠"),
    HEART("Heart", "❤"),
    CLUB("Club", "♣"),
    DIAMND("diamnd", "◆");


    private String suitTypeName;
    private String suitType;


    CardSuitType(String suitTypeName, String suitType) {
        this.suitTypeName = suitTypeName;
        this.suitType = suitType;
    }

    public String getSuitTypeName() {
        return suitTypeName;
    }

    public String getSuitType() {
        return suitType;
    }
}
