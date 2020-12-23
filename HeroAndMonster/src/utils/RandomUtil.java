package utils;

import java.util.Random;

/**
 * A random util class, use to generate random number
 */
public class RandomUtil {
    private static Random random;

    static{
        random = new Random();
    }

    public static int nextInt(){
        return random.nextInt();
    }

    public static int nextInt(int bound) {
        return  random.nextInt(bound);
    }

}
