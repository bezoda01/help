package utils;

import java.util.Random;

public class RandomUtils {
    public static int generatorInt(int maxRange) {
        Random random = new Random();
        return random.nextInt(maxRange);
    }
}
