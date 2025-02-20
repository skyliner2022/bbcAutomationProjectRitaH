package Utilities;

import org.openqa.selenium.*;
import org.testng.Assert;
import java.util.List;
import java.util.Random;

public class ValidationHelper {
    //Generic method to check a WebElement is displayed
    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //Generic method to assert element is displayed
    public static void assertElementDisplayed(WebElement element, String errorMessage) {
        boolean isDisplayed = isElementDisplayed(element);
        Assert.assertTrue(isDisplayed, errorMessage);
    }

    //Generic method to assert element is not displayed
    public static void assertElementNotDisplayed(WebElement element, String errorMessage) {
        boolean isDisplayed = isElementDisplayed(element);
        Assert.assertFalse(isDisplayed, errorMessage);
    }

    //Generic method to assert that a list of element is displayed
    public static void assertElementsDisplayed(List<WebElement> elements, String errorMessage) {
        for (WebElement element : elements) {
            boolean isDisplayed = isElementDisplayed(element);
            Assert.assertTrue(isDisplayed, errorMessage + " Element: " + element);
        }
    }

    //Generic method to extract text from elements
    public static String getText(WebElement elementText) {
        return elementText.getText();
    }

    //Generic method to click element
    public static void clickElement(WebElement element) {
        element.click();
    }

    //Generic method to click a random element
    public static void clickRandomElement(List<WebElement> randomElement) {
        if (randomElement.isEmpty()) {
            throw new RuntimeException("No header menu options found.");
        }

        WebElement randomOption = randomElement.get(new Random().nextInt(randomElement.size()));
        randomOption.click();
    }

    //Generic method to get an element from array
    public static WebElement getRandomElement(List<WebElement> elements) {
        if (elements.isEmpty()) {
            throw new RuntimeException("No links found with the specified locator");
        }
        Random random = new Random();
        return elements.get(random.nextInt(elements.size()));
    }

    //Generic method to assert text comparison
    public static void assertTextEquals(String actualText, String expectedText, String errorMessage) {
        Assert.assertEquals(actualText, expectedText, errorMessage);
    }

    //Generic method to check element has class
    public static void assertClassOfElementMatches(WebElement element, WebElement classElement, String errorMessage) {
        String elementClass = element.getCssValue("class");
        String classValue = classElement.getCssValue("class");
        boolean isClassMatch = elementClass.contains(classValue);
        Assert.assertTrue(isClassMatch, errorMessage);
    }

    //Generic method to assert that a search word is present in the URL as parameter
    public static void assertLinkUrlMatches(String currentUrl, String word, String errorMessage) {
        Assert.assertTrue(currentUrl.toLowerCase().contains(word.toLowerCase()), errorMessage);
    }

    //Generic method to get element attribute
    public static String getElementAttribute(WebElement element, String attributeName) {
        return element.getDomAttribute(attributeName);
    }
}
