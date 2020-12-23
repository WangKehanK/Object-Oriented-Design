/**
 * A balance class, record the amount of money in player's pocket
 */
public class Balance {

    private double balance;


    public Balance() {
        this.balance = 0;
    }

    public Balance(double balance) {
        this.balance = balance;
    }

    /**
     * reduce when lose
     */
    public void reduce(double number) {
        balance -= number;
    }

    /**
     * add when win
     */
    public void add(double number){
        balance += number;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * check whether empty
     * @return
     */
    public boolean isEmpty() {
        return balance == 0;
    }
}
