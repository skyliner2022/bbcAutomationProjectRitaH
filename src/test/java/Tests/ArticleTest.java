package Tests;

import Steps.ArticleSteps;
import Utilities.ValidationHelper;
import lombok.SneakyThrows;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;


public class ArticleTest extends BaseTest {

    @BeforeMethod
    public void navigateToWebsiteAndOpenSignInPage() {
        visit("");
        BaseTest.waitForPageToLoadGlobal(driver, 10);
        removeAds();
    }

    @Test(description = "User is able to open main articles from home page and opened page matches the clicked article")
    public void testArticleIsOpen() {
        WebElement randomArticle = ValidationHelper.getRandomElement(articlePage.getInternalLinks());
        ArticleSteps.waitForAndScrollToElement(driver, randomArticle, 10);
        String linkHrefValue = ValidationHelper.getElementAttribute(randomArticle, "href");
        ValidationHelper.clickElement(randomArticle);
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains(linkHrefValue), "The opened page URL does not match the expected article link. Expected: " + linkHrefValue + " but got: " + driver.getCurrentUrl());
    }

    @Test(description = "User is able to open articles from topic articles and opened page matches the clicked article")
    public void testArticleIsOpenFromTopicPages() {
        articleSteps.navigateToArticleSections();
        removeAds();
        WebElement randomArticle = ValidationHelper.getRandomElement(articlePage.getInternalLinks());
        ArticleSteps.waitForAndScrollToElement(driver, randomArticle, 10);
        String linkHrefValue = ValidationHelper.getElementAttribute(randomArticle, "href");
        ValidationHelper.clickElement(randomArticle);
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains(linkHrefValue), "The opened page URL does not match the expected article link. Expected: " + linkHrefValue + " but got: " + driver.getCurrentUrl());
    }

    @Test(description = "User is able to open articles from Sports page")
    public void testArticleIsOpenFromSportPage() {
        articleSteps.navigateToSportPage();
        WebElement randomSportArticle = ValidationHelper.getRandomElement(articlePage.getSportArticles());
        ArticleSteps.waitForAndScrollToElement(driver, randomSportArticle, 10);
        String linkHrefValue = ValidationHelper.getElementAttribute(randomSportArticle, "href");
        ValidationHelper.clickElement(randomSportArticle);
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains(linkHrefValue), "The opened page URL does not match the expected article link. Expected: " + linkHrefValue + " but got: " + driver.getCurrentUrl());
    }

    @Test(description = "User is able to open the registration from modal after saving an article")
    public void verifyUserSignInFromModalAfterArticleSave() {
        String expectedPageTitle = "Register for a BBC account";
        ValidationHelper.clickRandomElement(articlePage.getArticleWithSaveFunctionality());
        articlePage.clickButtonSaveArticle();
        ValidationHelper.assertElementDisplayed(articlePage.getArticleRegisterSignInModal(), "Sign In/Registration form should be visible");
        articlePage.clickArticleRegisterButton();
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User should on the registration form");
    }

    @Test(description = "User is able to open the sign in form modal after saving an article")
    public void verifyUserRegistersFromModalAfterArticleSave() {
        String expectedPageSubTitle = "Enter your email";
        ValidationHelper.clickRandomElement(articlePage.getArticleWithSaveFunctionality());
        articlePage.clickButtonSaveArticle();
        ValidationHelper.assertElementDisplayed(articlePage.getArticleRegisterSignInModal(), "Sign In/Registration form should be visible");
        articlePage.clickArticleSignInButton();
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getEnterYourEmailTitle()), expectedPageSubTitle, "User should be on the sign in form");
    }

    @Test(description = "User is able to save an article", dataProvider = "signInDefaultUser")
    public void testUserIsAbleToSaveArticle(String email, String password) {
        ArticleSteps.userIsLoggedIn(email, password);
        WebElement randomArticle = ValidationHelper.getRandomElement(articlePage.getArticleWithSaveFunctionality());
        ValidationHelper.clickElement(randomArticle);
        String articleHeadingToSave = ValidationHelper.getText(articlePage.getArticleHeadline());
        ValidationHelper.isElementDisplayed(articlePage.getButtonSaveArticle());
        articlePage.clickButtonSaveArticle();
        articleSteps.accountMenuIsOpen();
        articlePage.clickSavedArticlesLink();
        BaseTest.waitForPageToLoadGlobal(driver, 10);
        String savedArticleHeadlineCompare = ValidationHelper.getText(articlePage.getSavedArticleHeadline());
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.bbc.com/saved", "User should be on the Saved page");
        ValidationHelper.assertTextEquals(articleHeadingToSave, savedArticleHeadlineCompare, "Heading of the saved article should match its heading on the saved articles page");
    }

    @SneakyThrows
    @Test(description = "User is able to remove a saved article", dataProvider = "signInDefaultUser")
    public void testUserIsAbleToRemoveSavedArticle(String email, String password) {
        ArticleSteps.userIsLoggedIn(email, password);
        BaseTest.waitForPageToLoadGlobal(driver, 10);
        articleSteps.articleIsSaved(driver);
        articlePage.clickRemoveSavedArticleButtons();
        //Wait for the system to process the deletion (No reliable wait condition found)
        Thread.sleep(500); //TODO: Replace with better waiting if possible
        ValidationHelper.assertElementDisplayed(articlePage.getArticleClearedValidationText(), "Message saved articles page is empty should be displayed");
    }

    @Test(description = "User is able to open a saved article", dataProvider = "signInDefaultUser")
    public void testUserIsAbleToOpenSavedArticle(String email, String password) {
        ArticleSteps.userIsLoggedIn(email, password);
        articleSteps.articleIsSaved(driver);
        String savedArticleHeadlineCompare = ValidationHelper.getText(articlePage.getSavedArticleHeadline());
        articlePage.clickSavedArticleHeadline();
        String navigatedArticleHeading = ValidationHelper.getText(articlePage.getArticleHeadline());
        ValidationHelper.assertTextEquals(savedArticleHeadlineCompare, navigatedArticleHeading, "Saved article is open and headline match");
    }
}
