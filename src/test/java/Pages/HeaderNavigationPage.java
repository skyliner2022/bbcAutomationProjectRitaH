package Pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HeaderNavigationPage extends BasePage {

    @Getter
    @FindBy(xpath = "//a[@data-testid='mainNavigationLink']")
    List<WebElement> headerMenuOptions;

    @FindBy(xpath = "//a[text()='Home']")
    WebElement homeLink;

    @FindBy(xpath = "//a[text()='Sport']")
    WebElement sportLink;

    @Getter
    @FindBy(xpath = "//span[contains(@class, 'ssrcss-1u47p8g-LinkTextContainer')]")
    List<WebElement> sportStyledMenuItem;

    @Getter
    @FindBy(id = "main-heading")
    List<WebElement> sportPageMainHeading;

    @Getter
    @FindBy(xpath = "//li//a//span[contains(@class, 'eis6szr1')]")
    List<WebElement> sportStyledMenuSubItems;

    @Getter
    @FindBy(xpath = "//a[@data-testid='subNavigationLink']")
    List<WebElement> subNavigationLinksArray;


    public void clickHomeLink() {
        homeLink.click();
    }

    public void clickSportLink() {
        sportLink.click();
    }

    //Method to click only particular options having similar implementation
    public static void clickRandomArticlesLink(List<WebElement> elements) {
        List<String> excludedOptions = Arrays.asList("Sport", "Live", "Video");

        List<WebElement> filteredElements = elements.stream().filter(element -> !excludedOptions.contains(element.getText().trim())).toList();

        if (filteredElements.isEmpty()) {
            throw new RuntimeException("No valid links found in the given elements");
        }

        Random random = new Random();
        WebElement randomElement = filteredElements.get(random.nextInt(filteredElements.size()));

        randomElement.click();
    }

    //This method is used to fix element visibility issue
    public static void waitForElementsToBeInteractable(WebDriver driver, List<WebElement> elements, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        for (WebElement element : elements) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }
}
