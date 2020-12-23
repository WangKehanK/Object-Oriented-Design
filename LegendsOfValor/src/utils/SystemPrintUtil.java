package utils;

/**
 * A system print util class, print with categories
 */
public class SystemPrintUtil {

    public static void printRed(String s) {
        System.out.println("\u001B[31m" + s + "\u001B[0m");
    }

    public static void printBlue(String s) {
        System.out.println("\u001B[34m" + s + "\u001B[0m");
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public static void printlnWithStart(String s) {
        System.out.println("**************************************");
        System.out.println("              " + s + "              ");
    }

    public static void printlnWithEnd() {
        System.out.println("**************************************");
    }


}
