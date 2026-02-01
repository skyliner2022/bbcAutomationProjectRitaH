package Utilities;

import net.datafaker.Faker;

import java.util.Random;

public class RandomDataGenerator {
    /*
    Generates a password that meets the following criteria:
    - At least 8 characters
    - Contains at least one letter
    - Contains at least one number or symbol
    */

    public static String generateRandomPassword(int length, boolean includeLetters, boolean includeNumbers, boolean includeSpecialChars) {
        length = Math.max(length, 1); //Ensures length is at least 1

        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*";

        StringBuilder characterPool = new StringBuilder();
        if (includeLetters) {
            characterPool.append(letters);
        }
        if (includeNumbers) {
            characterPool.append(numbers);
        }
        if (includeSpecialChars) {
            characterPool.append(specialChars);
        }

        if (characterPool.isEmpty()) {
            throw new IllegalArgumentException("At least one character type must be included.");
        }

        Random random = new Random();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            password.append(characterPool.charAt(random.nextInt(characterPool.length())));
        }

        return password.toString();
    }

    //Generates a random valid email
    public static String randomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    //Generates random invalid email without domain part
    public static String generateRandomEmailMissingDomain() {
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyz1234567890";

        Random random = new Random();
        StringBuilder localPart = new StringBuilder();

        for (int i = 0; i < 6 + random.nextInt(7); i++) {
            localPart.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return localPart + "@";
    }
}
