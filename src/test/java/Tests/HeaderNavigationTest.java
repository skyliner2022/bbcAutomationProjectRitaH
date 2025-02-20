package Tests;

import Pages.HeaderNavigationPage;
import Utilities.ValidationHelper;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Objects;

@Epic("Navigation")
@Feature("Header navigation options")
@Story("User can navigate through header navigation options")
public class HeaderNavigationTest extends BaseTest {

    @BeforeMethod
    public void navigateToWebsiteAndOpenSignInPage() {
        visit("");
        waitForPageToLoadGlobal(driver, 10);
    }

    @Test(description = "Home header menu option navigates to the home page", priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is able to navigate to the home page from the Home header navigation option")
    public void testHeaderHomeNavigationLink() {
        String expectedUrl = "https://www.bbc.com/";

        Allure.step("Click a navigation option on header", () -> {
            ValidationHelper.clickRandomElement(headerNavigationPage.getHeaderMenuOptions());
        });

        Allure.step("Click the Home navigation option on header", () -> {
            headerNavigationPage.clickHomeLink();
        });

        Allure.step("Verify that the home page is open", () -> {
            try {
                Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "The current URL is not: " + expectedUrl);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is able to navigate to topic pages from header menu options", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is able to open topic sections by clicking the header navigation options")
    public void testHeaderNavigationLinks() {
        WebElement randomNavigationOption = ValidationHelper.getRandomElement(headerNavigationPage.getHeaderMenuOptions());

        Allure.step("Select any navigation option from header", () -> {
            Allure.step("Selected option: " + randomNavigationOption.getText());
        });

        String clickedLinkText = ValidationHelper.getText(randomNavigationOption);

        Allure.step("Get the clicked option link to validate redirection", () -> {
            Allure.step("Link of the clicked option: " + clickedLinkText);
        });

        Allure.step("Click the selected navigation option on header", () -> {
            ValidationHelper.clickElement(randomNavigationOption);
        });

        Allure.step("Verify that the page open matches the clicked navigation option", () -> {
            try {
                ValidationHelper.assertLinkUrlMatches(Objects.requireNonNull(driver.getCurrentUrl()), clickedLinkText, "Page opened does not match the name of a header menu option clicked");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is able to navigate through sub-options on header", priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is able to navigate through header navigation sub-options")
    public void testHeaderSecondaryNavigationLinks() {
        Allure.step("Click a navigation option on header", () -> {
            HeaderNavigationPage.clickHeaderNavigationOptions(headerNavigationPage.getHeaderMenuOptions());
        });

        WebElement randomSecondaryNavigationOption = ValidationHelper.getRandomElement(headerNavigationPage.getSubNavigationLinksArray());

        Allure.step("Select a navigation sub-option", () -> {
            Allure.step("Selected sub-option: " + randomSecondaryNavigationOption.getText());
        });

        Allure.step("Click the selected sub-option", () -> {
            ValidationHelper.clickElement(randomSecondaryNavigationOption);
        });

        //Additional step to re-fetch element to address stale element exception
        WebElement reFetchedRandomElement = driver.findElement(By.xpath("//a[@data-testid='subNavigationLink-active']"));

        Allure.step("Verify that element is displayed", () -> {
            Allure.step("Displayed element: " + reFetchedRandomElement.getText());
        });

        String attributeValue = ValidationHelper.getElementAttribute(reFetchedRandomElement, "data-testid");

        Allure.step("Extract data-test-id attribute value of the displayed element to validate the active state", () -> {
            Allure.step("Extracted attribute value: " + attributeValue);
        });

        Allure.step("Verify that the clicked navigation option is active", () -> {
            try {
                Assert.assertEquals(attributeValue, "subNavigationLink-active", "The clicked navigation option link did not become active");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "Verify user is able to navigate through navigation options on the Sports page header", priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is able to navigate through options on the Sport page")
    public void testSecondaryNavigationLinksOnSportPage() {
        String expectedUrl = "https://www.bbc.com/sport";
        //headerNavigationPage.clickSportLink();
        Allure.step("Click the Sport option on header", () -> {
            headerNavigationPage.clickSportLink();
        });
        //Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "The current URL is not: " + expectedUrl);
        Allure.step("Verify that the Sport page is open", () -> {
            try {
                Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "The current URL is not: " + expectedUrl);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Wait for the element to be interactable", () -> {
            HeaderNavigationPage.waitForElementsToBeInteractable(driver, headerNavigationPage.getSportStyledMenuItem(), 10);
        });

        WebElement randomNavigationOption = ValidationHelper.getRandomElement(headerNavigationPage.getSportStyledMenuItem());

        Allure.step("Select a navigation option on the Sport page", () -> {
            Allure.step("Selected option: " + randomNavigationOption.getText());
        });

        String clickedMenuOptionLink = ValidationHelper.getElementAttribute(randomNavigationOption, "href");

        Allure.step("Extract href attribute of the selected option to validate page redirection", () -> {
            Allure.step("Extracted attribute value: " + clickedMenuOptionLink);
        });

        Allure.step("Click the selected navigation option on the Sport page", () -> {
            ValidationHelper.clickElement(randomNavigationOption);
        });

        Allure.step("Wait for the page to fully load", () -> {
            waitForPageToLoadGlobal(driver, 10);
        });

        Allure.step("Verify that page open matches the clicked navigation option", () -> {
            try {
                ValidationHelper.assertLinkUrlMatches(Objects.requireNonNull(driver.getCurrentUrl()), clickedMenuOptionLink, "Page opened does not match  menu option clicked on the Sport page");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }
}
