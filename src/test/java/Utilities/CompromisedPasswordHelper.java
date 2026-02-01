package Utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class CompromisedPasswordHelper {

    private static final String FILE_PATH = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "compromised_passwords.txt").toString();

    // Method to fetch a random password from the txt file for dataProvider returning a compromised password
    public static String getRandomCompromisedPassword() {
        try {
            List<String> passwords = Files.readAllLines(Paths.get(FILE_PATH));
            if (passwords.isEmpty()) {
                throw new RuntimeException("Password file is empty.");
            }
            return passwords.get(new Random().nextInt(passwords.size()));
        } catch (IOException e) {
            throw new RuntimeException("Error reading password file.", e);
        }
    }
}
