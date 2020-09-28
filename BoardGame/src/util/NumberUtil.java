package util;

import java.util.regex.Pattern;

/**
 NumberUtil - A class that providing support to handle numeric input
 */
public class NumberUtil {
    public static boolean isNumber(String s) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(s).matches();
    }


    public static int getRandomInt(int number) {
        return (int) (Math.random() * number);
    }
}
