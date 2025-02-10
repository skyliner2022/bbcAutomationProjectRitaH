package Tests;

import Utilities.ValidationHelper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

public class NavigationMenuTest extends BaseTest {

    @BeforeMethod
    public void navigateToWebsiteAndOpenSignInPage() {
        visit("");
        BaseTest.waitForPageToLoadGlobal(driver, 10);
        navigationMenuSteps.openMenuAndCheckDisplay();
    }

    @Test(description = "Verify menu can be open and closed")
    public void testLeftHandMenuIsOpenAndClose() {
        navigationMenuPage.clickButtonCloseMenu();
        ValidationHelper.assertElementNotDisplayed(navigationMenuPage.getLeftHandMenu(), "Left hand menu should not be visible");
    }

    @Test(description = "Verify user is able to search by words", dataProvider = "randomSearchWord")
    public void testSearchByWords(String word) {
        navigationMenuPage.inputValueSearchInputField(word);
        navigationMenuPage.clickSearchSubmit();
        ValidationHelper.assertLinkUrlMatches(Objects.requireNonNull(driver.getCurrentUrl()), word, "Search word should be found in the URL parameters after the search");
    }

    @Test(description = "Verify Home left hand menu option navigates to the home page", dataProvider = "randomSearchWord")
    public void testLeftHandMenuHomeOptionOpensHomePage(String word) {
        navigationMenuPage.inputValueSearchInputField(word);
        navigationMenuPage.clickSearchSubmit();
        //NOTE: Uncomment this method if adds iframe is appearing during this step
        //navigationMenuPage.removeGoogleAdsIframeFromDOM(driver, navigationMenuPage.getGoogleAdsIframe());
        navigationMenuPage.clickButtonOpenMenu();
        navigationMenuPage.clickButtonHomeLeftMenu();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.bbc.com/", "The current URL should be the home page");
    }

    @Test(description = "Verify left hand menu level one sub options are unfolded")
    public void testLeftHandLevelOneSubOptionsAreUnfolded() {
        navigationMenuPage.clickRandomMenuOptionWithSubOptionLevelOne(navigationMenuPage.getButtonOptionsLeftMenu());
        ValidationHelper.assertElementsDisplayed(navigationMenuPage.getButtonLevelOneSubOptions(), "Sub menu options level one should be visible");
    }

    @Test(description = "Verify left hand menu level two sub options are unfolded")
    public void testLeftHandSubOptionsLevelTwoAreUnfolded() {
        navigationMenuPage.clickMenuOptionNews();
        navigationMenuPage.clickSubMenuOptionLevelOneWithSubMenu();
        ValidationHelper.assertElementsDisplayed(navigationMenuPage.getButtonLevelTwoSubOptions(), "Sub menu options level two should be visible");
    }

    @Test(description = "Verify user is able to navigate to pages from left hand menu sub options")
    public void testLeftHandMenuOptionNavigateToPage() {
        navigationMenuPage.clickRandomMenuOptionWithSubOptionLevelOne(navigationMenuPage.getButtonOptionsLeftMenu());
        WebElement randomNavigationOption = ValidationHelper.getRandomElement(navigationMenuPage.getLevelOneSubOptionLinks());
        String clickedLinkText = ValidationHelper.getText(randomNavigationOption);
        ValidationHelper.clickElement(randomNavigationOption);
        ValidationHelper.assertLinkUrlMatches(Objects.requireNonNull(driver.getCurrentUrl()), clickedLinkText, "Page opened should match a header menu option clicked");
    }
}
