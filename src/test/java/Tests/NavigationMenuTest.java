package Tests;

import Utilities.ValidationHelper;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Objects;

@Epic("Navigation")
@Feature("Left-hand menu navigation")
@Story("User can navigate from the left-hand menu")
public class NavigationMenuTest extends BaseTest {

    @BeforeMethod
    public void navigateToWebsiteAndOpenSignInPage() {
        visit("");
        waitForPageToLoadGlobal(driver, 10);
        navigationMenuSteps.openMenuAndCheckItsDisplay();
    }

    @Test(description = "Verify left-hand menu can be opened and closed")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that the left-hand menu can be opened and closed")
    public void testLeftHandMenuOpensAndCloses() {
        Allure.step("Click the Close button to close the opened left-hand menu", () -> {
            navigationMenuPage.clickButtonCloseMenu();
        });

        Allure.step("Verify that the left-hand menu is closed", () -> {
            try {
                ValidationHelper.assertElementNotDisplayed(navigationMenuPage.getLeftHandMenu(), "Left hand menu is not displayed");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "Verify user is able to search by keywords", dataProvider = "randomSearchWord")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is able to find articles by keyword search on the left-hand menu")
    public void testSearchByKeyWord(String word) {
        Allure.step("Input a keyword into the left-hand menu search bar", () -> {
            navigationMenuPage.inputValueIntoSearchField(word);
        });

        Allure.step("Submit the input", () -> {
            navigationMenuPage.clickSearchSubmit();
        });

        Allure.step("Verify that the searched keyword is reflected in the URL as a parameter after submit", () -> {
            try {
                ValidationHelper.assertLinkUrlMatches(Objects.requireNonNull(driver.getCurrentUrl()), word, "Search key word is not found in the URL parameters after search");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "Verify Home left-hand menu option redirects to the home page", dataProvider = "randomSearchWord")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that the Home left-hand menu option redirects to the home page")
    public void testLeftHandMenuHomeOptionOpensHomePage(String word) {
        String expectedUrl = "https://www.bbc.com/";

        Allure.step("Input a keyword into the left-hand menu search bar", () -> {
            navigationMenuPage.inputValueIntoSearchField(word);
        });

        Allure.step("Submit the input", () -> {
            navigationMenuPage.clickSearchSubmit();
        });

        //NOTE: Uncomment this method if adds iframe is appearing during this step
        //navigationMenuPage.removeGoogleAdsIframeFromDOM(driver, navigationMenuPage.getGoogleAdsIframe());

        Allure.step("Open the left-hand menu", () -> {
            navigationMenuPage.clickButtonOpenMenu();
        });

        Allure.step("Click the Home menu option", () -> {
            navigationMenuPage.clickButtonHomeLeftMenu();
        });

        Allure.step("Verify the home page is open", () -> {
            try {
                Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "The current URL is not: " + expectedUrl);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "Verify left-hand menu level one sub-options are unfolded")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that the left-hand menu sub-options of level one can be unfolded")
    public void testLeftHandLevelOneSubOptionsUnfold() {
        Allure.step("Click a left-hand menu option", () -> {
            navigationMenuPage.clickRandomMenuOptionWithSubOptionLevelOne(navigationMenuPage.getButtonOptionsLeftMenu());
        });

        Allure.step("Verify that the sub-options list is unfolded", () -> {
            try {
                ValidationHelper.assertElementsDisplayed(navigationMenuPage.getButtonLevelOneSubOptions(), "Level one sub menu options are not displayed");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "Verify left hand menu level two sub-options are unfolded")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that the left-hand menu sub-options of level two can be unfolded")
    public void testLeftHandSubOptionsLevelTwoUnfold() {
        Allure.step("Click a left-hand menu option", () -> {
            navigationMenuPage.clickMenuOptionNewsMenu();
        });

        Allure.step("Click a left-hand menu sub-option", () -> {
            navigationMenuPage.clickSubMenuOptionLevelOneWithSubOptions();
        });

        Allure.step("Verify that the sub-options list is unfolded", () -> {
            try {
                ValidationHelper.assertElementsDisplayed(navigationMenuPage.getButtonLevelTwoSubOptions(), "Level two sub menu options are not displayed");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "Verify user is able to navigate to pages from left-hand menu sub-options")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is able to navigate to topic pages from the left-hand menu sub-options")
    public void testLeftHandMenuOptionNavigatesToPage() {
        Allure.step("Click a left-hand menu option", () -> {
            navigationMenuPage.clickRandomMenuOptionWithSubOptionLevelOne(navigationMenuPage.getButtonOptionsLeftMenu());
        });

        WebElement randomNavigationOption = ValidationHelper.getRandomElement(navigationMenuPage.getLevelOneSubOptionLinks());

        Allure.step("Select any menu sub-option", () -> {
            Allure.step("Selected menu sub-option: " + randomNavigationOption.getText());
        });

        String clickedSubOptionLink = ValidationHelper.getElementAttribute(randomNavigationOption, "href");

        Allure.step("Extract the href attribute from the selected menu sub-option to validate page redirection", () -> {
            Allure.step("Extracted menu sub-option link: " + clickedSubOptionLink);
        });

        Allure.step("Click a left-hand menu sub-option", () -> {
            ValidationHelper.clickElement(randomNavigationOption);
        });

        Allure.step("Verify that the page opened matches the menu sub-option clicked", () -> {
            try {
                ValidationHelper.assertLinkUrlMatches(Objects.requireNonNull(driver.getCurrentUrl()), clickedSubOptionLink, "Page opened does not match the name of a header menu sub-option clicked");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }
}
