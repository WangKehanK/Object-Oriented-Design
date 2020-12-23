package utils;

import java.util.Scanner;

/**
 * A scanner util class, use to parse user input
 */
public class ScannerUtil {

    private static Scanner sc;
    static{
        sc = new Scanner(System.in);
    }

    public static String readLine(){
        return sc.nextLine();
    }

    public static int readInteger(String tip) {
        Integer number;
        while(true){
            System.out.println(tip);
            String str = sc.nextLine();
            try {
                number = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("Error Integer");
                continue;
            }
            break;
        }
        return number;
    }
}
