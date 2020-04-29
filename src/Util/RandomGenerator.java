package Util;

import java.security.SecureRandom;

public class RandomGenerator {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";

    private static SecureRandom random = new SecureRandom();

    public static boolean generateRandomBoolean(){
        return random.nextBoolean();
    }

    public static String generateRandomString(int length, boolean includeUpperLetters, boolean includeLowerLetters, boolean includeDigits) {
        StringBuilder sb = new StringBuilder(length);
        String possibleChars =
                (includeLowerLetters ? CHAR_LOWER : "") +
                (includeUpperLetters ? CHAR_UPPER : "") +
                (includeDigits ? NUMBER : "");

        for (int i = 0; i < length; i++) {
            int rndCharAt = generateRandomInt(possibleChars.length());
            char rndChar = possibleChars.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }

    public static int generateRandomInt(int exclusiveMaxValue) {
        int randInt = random.nextInt(exclusiveMaxValue);
        return randInt;
    }

    public static int generateRandomInt(int inclusiveMinValue, int exclusiveMaxValue) {
        int randInt = generateRandomInt(exclusiveMaxValue) + inclusiveMinValue;
        return randInt;
    }
}
