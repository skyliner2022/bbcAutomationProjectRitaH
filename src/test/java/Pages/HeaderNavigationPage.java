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
    @FindBy(xpath = "//div[@id='product-navigation-menu']//li//a")
    List<WebElement> sportStyledMenuItem;

    @Getter
    @FindBy(xpath = "//a[@data-testid='subNavigationLink']")
    List<WebElement> subNavigationLinksArray;


    public void clickHomeLink() {
        homeLink.click();
    }

    public void clickSportLink() {
        sportLink.click();
    }

    /*
    Sport header option is excluded, since it has a different structure of articles,
    some page functionalities will be tested separately. Video and Live header options
    are excluded, since they do not contain articles targeted for testing in the project.
    This method will be used to locate header options redirecting to articles devoted
    to particular topics
    */
    public static void clickHeaderNavigationOptions(List<WebElement> elements) {
        List<String> excludedOptions = Arrays.asList("Sport", "Video", "Live");

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
