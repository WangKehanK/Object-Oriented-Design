import java.util.Scanner;

/**
 InputUtil - A class used to obtain the data input by the user
 */
public class InputUtil {

    private static Scanner sc;

    static{
        sc = new Scanner(System.in);
    }

    public static String getInput(){
        return sc.nextLine();
    }
}
