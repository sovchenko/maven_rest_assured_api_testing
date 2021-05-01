package util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class RandomUtil {
    private static int generateRandomInteger(int upperBound) {
        int random = new Random().nextInt(upperBound);
        System.out.println("Random Number" + random);
        return random;
    }

    public static int generateRandomUserId() {
        int upperBoundForUserId = 1000;
        return generateRandomInteger(upperBoundForUserId);
    }
}
