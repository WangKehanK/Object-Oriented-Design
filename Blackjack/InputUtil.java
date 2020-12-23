import java.util.Scanner;
/**
 * A class used to obtain the data input by the user
 */
public class InputUtil {

    private static Scanner sc;

    static{
        sc = new Scanner(System.in);
    }

    public static String getInput(){
        return sc.nextLine();
    }

    public static Integer getInputInteger(){
        String input = getInput();
        if(input == null || "".equals(input) || !NumberUtil.isNumber(input)){
            return null;
        }else{
            return Integer.parseInt(input);
        }
    }
}
