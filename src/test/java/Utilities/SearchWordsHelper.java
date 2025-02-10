package Utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class SearchWordsHelper {

    private static final String FILE_PATH = "src/test/resources/search_words.txt";

    // Method to fetch a random word from the txt file for search functionality
    public static String getRandomSearchWord() {
        try {
            List<String> searchWords = Files.readAllLines(Paths.get(FILE_PATH));
            if (searchWords.isEmpty()) {
                throw new RuntimeException("Search words file is empty.");
            }
            return searchWords.get(new Random().nextInt(searchWords.size()));
        } catch (IOException e) {
            throw new RuntimeException("Error reading search words file.", e);
        }
    }
}
