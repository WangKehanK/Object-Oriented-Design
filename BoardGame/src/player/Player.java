package player;

/**
 * Player - is a class save basic player information and game condition, including the number of win, tie, or lose games
 */
public class Player {
    private String name;
    private String teamName;
    private int personNum;

    private int victoryNum;
    private int peaceNum;
    private int gameNum;

    public Player(String name, String teamName, int personNum) {
        this.name = name;
        this.teamName = teamName;
        this.personNum = personNum;

        this.victoryNum = 0;
        this.peaceNum = 0;
        this.gameNum = 0;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPersonNum() {
        return personNum;
    }
    
    

    @Override
    public String toString() {
        return "Player " + name + " team " + teamName + " personNo." + personNum +
                ": game Number = " + gameNum + ", victory Number = "+ victoryNum + ", tied game Number = " + this.peaceNum;
    }

    public void addVictoryNum() {
        this.victoryNum++;
    }

    public void addPeaceNum(){
        this.peaceNum++;
    }

    public void addGameNum(){
        this.gameNum++;
    }


}
