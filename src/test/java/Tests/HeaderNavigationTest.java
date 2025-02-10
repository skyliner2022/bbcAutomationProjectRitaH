package Tests;

import Pages.HeaderNavigationPage;
import Utilities.ValidationHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

public class HeaderNavigationTest extends BaseTest {

    @BeforeMethod
    public void navigateToWebsiteAndOpenSignInPage() {
        visit("");
        BaseTest.waitForPageToLoadGlobal(driver, 10);
    }

    @Test(description = "Home header menu option navigates to the home page", priority = 1)
    public void testHeaderHomeOptionNavigation() {
        ValidationHelper.clickRandomElement(headerNavigationPage.getHeaderMenuOptions());
        headerNavigationPage.clickHomeLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.bbc.com/", "The current URL should match");
    }

    @Test(description = "User is able to navigate to pages from header menu options", priority = 2)
    public void testHeaderOptionsNavigation() {
        WebElement randomNavigationOption = ValidationHelper.getRandomElement(headerNavigationPage.getHeaderMenuOptions());
        String clickedLinkText = ValidationHelper.getText(randomNavigationOption);
        ValidationHelper.clickElement(randomNavigationOption);
        ValidationHelper.assertLinkUrlMatches(Objects.requireNonNull(driver.getCurrentUrl()), clickedLinkText, "Page opened should match a header menu option clicked");
    }

    //Additional step is added to the test to re-fetch element to fix stale element exception
    @Test(description = "User is able to navigate to pages from header submenu menu options", priority = 3)
    public void testHeaderSecondaryOptionsNavigation() {
        HeaderNavigationPage.clickRandomArticlesLink(headerNavigationPage.getHeaderMenuOptions());
        WebElement randomSecondaryNavigationOption = ValidationHelper.getRandomElement(headerNavigationPage.getSubNavigationLinksArray());
        ValidationHelper.clickElement(randomSecondaryNavigationOption);
        WebElement reFetchedRandomElement = driver.findElement(By.xpath("//a[@data-testid='subNavigationLink-active']"));
        String attributeValue = ValidationHelper.getElementAttribute(reFetchedRandomElement, "data-testid");
        Assert.assertEquals(attributeValue, "subNavigationLink-active", "The clicked sub-navigation link did not become active.");
    }

    @Test(description = "Verify user is able to navigate through secondary menu options on the Sports page header", priority = 4)
    public void testSecondaryOptionsNavigationSportPage() {
        headerNavigationPage.clickSportLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.bbc.com/sport", "The current URL should be the sport page");
        HeaderNavigationPage.waitForElementsToBeInteractable(driver, headerNavigationPage.getSportStyledMenuItem(), 10);
        WebElement randomNavigationOption = ValidationHelper.getRandomElement(headerNavigationPage.getSportStyledMenuItem());
        String clickedLinkText = ValidationHelper.getText(randomNavigationOption);
        ValidationHelper.clickElement(randomNavigationOption);
        BaseTest.waitForPageToLoadGlobal(driver, 10);
        String sportPageMainHeading = ValidationHelper.getTextFromRandomElement(headerNavigationPage.getSportPageMainHeading());
        ValidationHelper.assertLinkUrlMatches(sportPageMainHeading, clickedLinkText, "Page opened should match a header menu option clicked on the Sport page");
    }
}
