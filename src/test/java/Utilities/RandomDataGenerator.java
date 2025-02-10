package Utilities;

import net.datafaker.Faker;

import java.util.Random;

public class RandomDataGenerator {
    // Generates a password that meets the following criteria:
    // - At least 8 characters
    // - Contains at least one letter
    // - Contains at least one number or symbol
    public static String randomPassword() {
        Random random = new Random();

        String letters = "abcdefghijklmnopqrstuvwxyz";
        String numbersAndSymbols = "0123456789!@#$%^&*";
        String fullPassword = letters + numbersAndSymbols;

        StringBuilder password = new StringBuilder();
        password.append(letters.charAt(random.nextInt(letters.length())));
        password.append(numbersAndSymbols.charAt(random.nextInt(numbersAndSymbols.length())));

        for (int i = 2; i < 8; i++) {
            password.append(fullPassword.charAt(random.nextInt(fullPassword.length())));
        }

        return password.toString();
    }

    //Generates password having length less than 8
    public static String randomSevenLengthPassword() {
        Random random = new Random();

        String letters = "abcdefghijklmnopqrstuvwxyz";
        String numbersAndSymbols = "0123456789!@#$%^&*";
        String fullPassword = letters + numbersAndSymbols;

        StringBuilder password = new StringBuilder();
        password.append(letters.charAt(random.nextInt(letters.length())));
        password.append(numbersAndSymbols.charAt(random.nextInt(numbersAndSymbols.length())));

        for (int i = 2; i < 7; i++) {
            password.append(fullPassword.charAt(random.nextInt(fullPassword.length())));
        }

        return password.toString();
    }

    //Generates password having no letters
    public static String randomNoLetterPassword() {
        Random random = new Random();

        String numbersAndSymbols = "0123456789!@#$%^&*";

        StringBuilder password = new StringBuilder();
        password.append(numbersAndSymbols.charAt(random.nextInt(numbersAndSymbols.length())));

        for (int i = 1; i < 8; i++) {
            password.append(numbersAndSymbols.charAt(random.nextInt(numbersAndSymbols.length())));
        }

        return password.toString();
    }

    //Generates password having no special characters and numbers
    public static String randomNoCharactersAndNumbersPassword() {
        Random random = new Random();

        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder password = new StringBuilder();
        password.append(letters.charAt(random.nextInt(letters.length())));

        for (int i = 1; i < 8; i++) {
            password.append(letters.charAt(random.nextInt(letters.length())));
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
