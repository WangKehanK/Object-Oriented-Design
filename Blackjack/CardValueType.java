/**
 * An enum type that provides all the value types to poker game, from A to K
 */
public enum CardValueType {
    ACE("Ace", "A"),
    TWO("Two", "2"),
    THREE("Three", "3"),
    FOUR("Four", "4"),
    FIVE("Five", "5"),
    SIX("Six","6"),
    SEVEN("Seven", "7"),
    EIGHT("Eight", "8"),
    NINE("Nine", "9"),
    TEN("Ten", "10"),
    JACK("Jack", "J"),
    QUEEN("Queen","Q"),
    KING("King", "K"),
    BLACK_JOKER("Black Joker", "BJ"),
    RED_JOKER("Red Joker", "RJ");


    private String valueName;
    private String shortName;

    CardValueType(String valueName, String shortName) {
        this.valueName = valueName;
        this.shortName = shortName;
    }


    public String getValueName() {
        return valueName;
    }

    public String getShortName() {
        return shortName;
    }
}

