package Steps;

import Pages.ArticlePage;
import Pages.HeaderNavigationPage;
import Pages.SignInPage;
import Tests.BaseTest;
import Utilities.ValidationHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ArticleSteps {

    HeaderNavigationPage headerNavigationPage = new HeaderNavigationPage();
    static SignInSuccessSteps signInSuccessSteps = new SignInSuccessSteps();
    static SignInPage signInPage = new SignInPage();
    ArticlePage articlePage = new ArticlePage();

    public void navigateToArticleSections() {
        HeaderNavigationPage.clickHeaderNavigationOptions(headerNavigationPage.getHeaderMenuOptions());
    }

    public void navigateToSportPage() {
        headerNavigationPage.clickSportLink();
    }

    public static void waitForAndScrollToElement(WebDriver driver, WebElement element, int timeoutInSeconds) {
        ArticlePage.waitForElementToBeInteractable(driver, element, timeoutInSeconds);
        ArticlePage.scrollToElement(driver, element);
    }

    public static void logInWithValidCredentials(String email, String password) {
        signInSuccessSteps.signInSuccessfullyFromSignInPage(email, password);
    }

    public void verifyAccountMenuIsOpen() {
        ValidationHelper.clickElement(signInPage.getUserAccountButton());
        ValidationHelper.isElementDisplayed(articlePage.getSavedArticlesLink());
    }

    public void saveArticleToSavedArticlesPage(WebDriver driver) {
        BaseTest.waitForPageToLoadGlobal(driver, 10);
        WebElement randomArticle = ValidationHelper.getRandomElement(articlePage.getInternalLinks());
        ValidationHelper.clickElement(randomArticle);
        ValidationHelper.isElementDisplayed(articlePage.getButtonSaveArticle());
        articlePage.clickButtonSaveArticle();
        verifyAccountMenuIsOpen();
        articlePage.clickSavedArticlesLink();
        BaseTest.waitForPageToLoadGlobal(driver, 10);
    }
}
