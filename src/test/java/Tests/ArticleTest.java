package Tests;

import Steps.ArticleSteps;
import Utilities.ValidationHelper;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Objects;


@Epic("Articles management")
public class ArticleTest extends BaseTest {

    @BeforeMethod
    public void navigateToWebsiteAndOpenSignInPage() {
        visit("");
        waitForPageToLoadGlobal(driver, 10);
        removeAds();
    }

    @Feature("Article Navigation")
    @Story("User is able to open articles")
    @Test(description = "User is able to open main articles from home page and opened page matches the clicked article")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is able to open articles")
    public void testUserIsAbleToOpenArticles() {
        WebElement randomArticle = ValidationHelper.getRandomElement(articlePage.getInternalLinks());

        Allure.step("Select any article on the page", () -> {
            Allure.step("Selected article: " + randomArticle.getText());
        });

        String linkHrefValue = ValidationHelper.getElementAttribute(randomArticle, "href");

        Allure.step("Extract the href attribute from the selected article to validate redirection", () -> {
            Allure.step("Extracted article link: " + linkHrefValue);
        });

        Allure.step("Click the selected article", () -> {
            ValidationHelper.clickElement(randomArticle);
        });

        Allure.step("Verify that the opened page URL matches the article clicked", () -> {
            try {
                Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains(linkHrefValue), "The opened page URL does not match the expected article link. Expected: " + linkHrefValue + " but got: " + driver.getCurrentUrl());
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Feature("Article Navigation")
    @Story("User is able to open articles")
    @Test(description = "User is able to open articles from topic pages and opened page matches the clicked article")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is able to navigate to a topic section and open an article")
    public void testUserCanOpenArticlesOnTopicPages() {
        Allure.step("Click any navigation option from header", () -> {
            articleSteps.navigateToArticleSections();
        });
        //NOTE: Uncomment this step if the issue with the advertisement <div> overlaying the element arises
        //removeAds();
        WebElement randomArticle = ValidationHelper.getRandomElement(articlePage.getInternalLinks());

        Allure.step("Select any article on the page", () -> {
            Allure.step("Selected article: " + randomArticle.getText());
        });

        Allure.step("Wait for the element to appear and scroll to this element", () -> {
            ArticleSteps.waitForAndScrollToElement(driver, randomArticle, 10);
        });

        String linkHrefValue = ValidationHelper.getElementAttribute(randomArticle, "href");

        Allure.step("Extract the href attribute from the selected article to validate page redirection", () -> {
            Allure.step("Extracted article link: " + linkHrefValue);
        });

        Allure.step("Click the selected article", () -> {
            ValidationHelper.clickElement(randomArticle);
        });

        Allure.step("Verify that the opened page URL matches the article clicked", () -> {
            try {
                Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains(linkHrefValue), "The opened page URL does not match the expected article link. Expected: " + linkHrefValue + " but got: " + driver.getCurrentUrl());
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Feature("Article Navigation")
    @Story("User is able to open articles")
    @Test(description = "User is able to open articles from Sports page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is able to open articles on the Sport page")
    public void testUserCanOpenArticlesOnSportPage() {
        Allure.step("Open the Sport page", () -> {
            articleSteps.navigateToSportPage();
        });
        WebElement randomSportArticle = ValidationHelper.getRandomElement(articlePage.getSportArticles());

        Allure.step("Select any article on the page", () -> {
            Allure.step("Selected article: " + randomSportArticle.getText());
        });

        Allure.step("Wait for the element to appear and scroll to this element", () -> {
            ArticleSteps.waitForAndScrollToElement(driver, randomSportArticle, 10);
        });

        String linkHrefValue = ValidationHelper.getElementAttribute(randomSportArticle, "href");

        Allure.step("Extract the href attribute from the selected article to validate page redirection", () -> {
            Allure.step("Extracted article link: " + linkHrefValue);
        });

        Allure.step("Click the selected article", () -> {
            ValidationHelper.clickElement(randomSportArticle);
        });

        Allure.step("Verify that the opened page URL matches the article clicked", () -> {
            try {
                Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains(linkHrefValue), "The opened page URL does not match the expected article link. Expected: " + linkHrefValue + " but got: " + driver.getCurrentUrl());
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Feature("Article Saving")
    @Story("User opens the sign-up form on the article page")
    @Test(description = "User is able to open the sign-up form on the modal on the article page")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test validates that user is able to open the sign-up form after clicking the 'save' button")
    public void testUserCanOpenSignInFormAfterSavingArticle() {
        String expectedPageTitle = "Register for a BBC account";

        Allure.step("Open an article", () -> {
            ValidationHelper.clickRandomElement(articlePage.getInternalLinks());
        });

        Allure.step("Click the 'save' button", () -> {
            articlePage.clickButtonSaveArticle();
        });

        Allure.step("Verify that sign-up/sign-in modal is open", () -> {
            try {
                ValidationHelper.assertElementDisplayed(articlePage.getArticleRegisterSignInModal(), "Sign In/Registration form is not visible");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Click the 'register' button", () -> {
            articlePage.clickArticleRegisterButton();
        });

        Allure.step("Verify that sign-up from is open", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User is not on the sign-up form");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Feature("Article Saving")
    @Story("User opens the sign-in form on the article page")
    @Test(description = "User is able to open the sign in form on the modal on the article page")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test validates that user is able to open the sign-in form after clicking the 'save' button")
    public void testUserCanOpenRegisterFormAfterSavingArticle() {
        String expectedPageSubTitle = "Enter your email";

        Allure.step("Open an article", () -> {
            ValidationHelper.clickRandomElement(articlePage.getInternalLinks());
        });

        Allure.step("Click the 'save' button", () -> {
            articlePage.clickButtonSaveArticle();
        });

        Allure.step("Verify that sign-up/sign-in modal is open", () -> {
            try {
                ValidationHelper.assertElementDisplayed(articlePage.getArticleRegisterSignInModal(), "Sign In/Registration form is not visible");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Click the 'sign in' button", () -> {
            articlePage.clickArticleSignInButton();
        });

        Allure.step("Verify that sign-in form is open", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getEnterYourEmailTitle()), expectedPageSubTitle, "User is not on the sign-in form");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Feature("Article Saving")
    @Story("User saves articles")
    @Test(description = "User is able to save an article", dataProvider = "signInDefaultUser")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test validates that user is able to save an article by clicking the 'save' button")
    public void testUserCanSaveArticle(String email, String password) {
        Allure.step("Sign in with valid credentials", () -> {
            ArticleSteps.logInWithValidCredentials(email, password);
        });

        WebElement randomArticle = ValidationHelper.getRandomElement(articlePage.getInternalLinks());

        Allure.step("Select any article on the page", () -> {
            Allure.step("Selected article: " + randomArticle.getText());
        });

        Allure.step("Click the selected article", () -> {
            ValidationHelper.clickElement(randomArticle);
        });

        String articleHeadingToSave = ValidationHelper.getText(articlePage.getArticleHeadline());

        Allure.step("Extract the headline of the clicked article to validate matching", () -> {
            Allure.step("Extracted headline of the article: " + articleHeadingToSave);
        });

        Allure.step("Verify that the 'save' button is displayed", () -> {
            ValidationHelper.isElementDisplayed(articlePage.getButtonSaveArticle());
        });

        Allure.step("Click the 'save' button", () -> {
            articlePage.clickButtonSaveArticle();
        });

        Allure.step("Verify that the account menu is open", () -> {
            articleSteps.verifyAccountMenuIsOpen();
        });

        Allure.step("Click the 'Saved' link", () -> {
            articlePage.clickSavedArticlesLink();
        });

        Allure.step("Wait for the page to fully load", () -> {
            waitForPageToLoadGlobal(driver, 10);
        });

        String savedArticleHeadlineCompare = ValidationHelper.getText(articlePage.getSavedArticleHeadline());

        Allure.step("Extract the headline of the article on the saved articles page to validate matching", () -> {
            Allure.step("Extracted headline of the article: " + articleHeadingToSave);
        });

        Allure.step("Verify that the saved articles page is open", () -> {
            try {
                Assert.assertEquals(driver.getCurrentUrl(), "https://www.bbc.com/saved", "User is not on the saved articles page");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the article is saved and heading matches", () -> {
            try {
                ValidationHelper.assertTextEquals(articleHeadingToSave, savedArticleHeadlineCompare, "Heading of the saved article does not match its heading on the saved articles page");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Feature("Article Saving")
    @Story("User removes articles")
    @Test(description = "User is able to remove the saved articles", dataProvider = "signInDefaultUser")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test validates that user is able to remove the saved articles from saved")
    public void testUserCanRemoveSavedArticle(String email, String password) {
        Allure.step("Sign in with valid credentials", () -> {
            ArticleSteps.logInWithValidCredentials(email, password);
        });

        Allure.step("Wait for the page to fully load", () -> {
            waitForPageToLoadGlobal(driver, 10);
        });

        Allure.step("Save an article", () -> {
            articleSteps.saveArticleToSavedArticlesPage(driver);
        });

        Allure.step("Click the unsave button", () -> {
            articlePage.clickRemoveSavedArticleButtons();
        });

        Allure.step("Verify that the saved article is removed from the saved", () -> {
            try {
                ValidationHelper.assertElementNotDisplayed(articlePage.getSavedArticleHeadingElement(), "Article is still displayed after being removed from saved");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Feature("Article Saving")
    @Story("User opens the saved articles")
    @Test(description = "User is able to open the saved article", dataProvider = "signInDefaultUser")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test validates that user is able to open the saved article")
    public void testUserCanOpenSavedArticle(String email, String password) {
        Allure.step("Sign in with valid credentials", () -> {
            ArticleSteps.logInWithValidCredentials(email, password);
        });

        Allure.step("Save an article", () -> {
            articleSteps.saveArticleToSavedArticlesPage(driver);
        });

        String savedArticleHeadlineCompare = ValidationHelper.getText(articlePage.getSavedArticleHeadline());

        Allure.step("Extract the headline of the saved article to validate matching", () -> {
            Allure.step("Extracted headline of the saved article: " + savedArticleHeadlineCompare);
        });

        Allure.step("Click the saved article", () -> {
            articlePage.clickSavedArticleHeadline();
        });
        String navigatedArticleHeading = ValidationHelper.getText(articlePage.getArticleHeadline());

        Allure.step("Extract the headline of the article opened to validate matching", () -> {
            Allure.step("Extracted headline of the article opened: " + navigatedArticleHeading);
        });

        Allure.step("Verify that the saved article is open and headings match", () -> {
            try {
                ValidationHelper.assertTextEquals(savedArticleHeadlineCompare, navigatedArticleHeading, "Saved article headline and headline of the page open do not match");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }
}
