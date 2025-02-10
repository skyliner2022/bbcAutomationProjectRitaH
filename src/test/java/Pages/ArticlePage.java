package Pages;

import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ArticlePage extends BasePage {

    @Getter
    @FindBy(xpath = "//a[@data-testid='internal-link']")
    List<WebElement> internalLinks;

    @Getter
    @FindBy(css = ".ssrcss-zmz0hi-PromoLink.exn3ah91")
    List<WebElement> sportArticles;

    @Getter
    @FindBy(css = "h2.dhclWg")
    List<WebElement> articleWithSaveFunctionality;

    @Getter
    @FindBy(xpath = "//button[@type='button' and @data-testid='saveButton']")
    WebElement buttonSaveArticle;

    @Getter
    @FindBy(xpath = "//button[@type='button' and @data-testid='wrexham-remove-button']")
    List<WebElement> buttonRemoveSavedArticle;

    @Getter
    @FindBy(xpath = "//div[@data-testid='popoverStyled']")
    WebElement articleRegisterSignInModal;

    @FindBy(xpath = "//button[@type='button' and @aria-label='Sign In']")
    WebElement articleSignInButton;

    @FindBy(xpath = "//button[@type='button' and @aria-label='Register']")
    WebElement articleRegisterButton;

    @Getter
    @FindBy(xpath = "//a[@href='https://www.bbc.com/saved']")
    WebElement savedArticlesLink;

    @Getter
    @FindBy(tagName = "h1")
    WebElement articleHeadline;

    @Getter
    @FindBy(css = "h2[data-testid='card-headline']")
    WebElement savedArticleHeadline;

    @Getter
    @FindBy(css = ".sc-8e61d307-3.gCEcex")
    WebElement articleClearedValidationText;

    public void clickButtonSaveArticle() {
        buttonSaveArticle.click();
    }

    public void clickArticleSignInButton() {
        articleSignInButton.click();
    }

    public void clickArticleRegisterButton() {
        articleRegisterButton.click();
    }

    public void clickSavedArticlesLink() {
        savedArticlesLink.click();
    }

    public void clickRemoveSavedArticleButtons() {
        for (WebElement button : buttonRemoveSavedArticle) {
            try {
                button.click();
            } catch (Exception e) {
                //break;
            }
        }
    }

    public void clickSavedArticleHeadline() {
        savedArticleHeadline.click();
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void waitForElementToBeInteractable(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
