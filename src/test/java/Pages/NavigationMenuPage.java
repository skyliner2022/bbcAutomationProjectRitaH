package Pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NavigationMenuPage extends BasePage {

    @FindBy(xpath = "//*[@aria-label='Open menu']")
    WebElement buttonOpenMenu;

    @FindBy(xpath = "//*[@aria-label='Close menu']")
    WebElement buttonCloseMenu;

    @Getter
    @FindBy(xpath = "//div[@data-testid='drawer-background']")
    WebElement leftHandMenu;

    @FindBy(css = "[data-testid='search-input-field']")
    WebElement searchField;

    @FindBy(xpath = "//button[@data-testid='search-input-search-button']")
    WebElement buttonSearchSubmit;

    @Getter
    @FindBy(xpath = "//button[@data-testid='level0NavButton-/home']")
    WebElement buttonHomeLeftMenu;

    @Getter
    @FindBy(xpath = "//button[contains(@data-testid, 'level0NavButton')]")
    List<WebElement> buttonOptionsLeftMenu;

    @Getter
    @FindBy(xpath = "//button[contains(@data-testid, 'level1NavButton')]")
    List<WebElement> buttonLevelOneSubOptions;

    @Getter
    @FindBy(xpath = "//button[contains(@data-testid, 'level2NavButton')]")
    List<WebElement> buttonLevelTwoSubOptions;

    @Getter
    @FindBy(xpath = "//div[contains(@data-testid, 'navigationPanel-navItem-level1')]//a[@data-testid='internal-link']")
    List<WebElement> levelOneSubOptionLinks;

    @Getter
    @FindBy(xpath = "//button[@data-testid='level0NavButton-/news']")
    WebElement menuOptionNewsLeftMenu;

    @Getter
    @FindBy(xpath = "//button[contains(@data-testid, '/news/uk')]")
    WebElement subMenuOptionLevelOneWithSubOptions;

    @Getter
    @FindBy(xpath = "//iframe[contains(@id, 'google_ads_iframe')]")
    WebElement googleAdsIframe;

    public void clickButtonOpenMenu() {
        buttonOpenMenu.click();
    }

    public void clickButtonCloseMenu() {
        buttonCloseMenu.click();
    }

    public void inputValueIntoSearchField(String word) {
        searchField.sendKeys(word);
    }

    public void clickSearchSubmit() {
        buttonSearchSubmit.click();
    }

    public void clickButtonHomeLeftMenu() {
        buttonHomeLeftMenu.click();
    }

    public void clickMenuOptionNewsMenu() {
        menuOptionNewsLeftMenu.click();
    }

    public void clickSubMenuOptionLevelOneWithSubOptions() {
        subMenuOptionLevelOneWithSubOptions.click();
    }

    /*
    This method targets all options from the left-hand menu that have sub-options
    This method is used specifically for the test checking sub-options navigation
    */
    public void clickRandomMenuOptionWithSubOptionLevelOne(List<WebElement> elements) {
        List<String> allowedOptions = Arrays.asList("Business", "Innovation", "Culture", "Arts", "Travel", "Earth");

        List<WebElement> filteredElements = elements.stream().filter(element -> allowedOptions.contains(element.getText().trim())).toList();

        if (filteredElements.isEmpty()) {
            throw new RuntimeException("No valid links are found in the given elements");
        }

        Random random = new Random();
        WebElement randomElement = filteredElements.get(random.nextInt(filteredElements.size()));

        randomElement.click();
    }

    //NOTE: Uncomment this method to remove iframe overlaying the entire page if issue arises
    /*
    public void removeGoogleAdsIframeFromDOM(WebDriver driver, WebElement googleAdsIframe) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].remove();", googleAdsIframe);
    }
    */

}
