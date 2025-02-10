package Tests;

import Utilities.ValidationHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

public class SignOutTest extends BaseTest {

    @BeforeMethod
    public void navigateToWebsiteAndOpenSignInPage() {
        visit("");
        BaseTest.waitForPageToLoadGlobal(driver, 10);
    }

    @Test(description = "User is able to sign out", dataProvider = "signInDefaultUser")
    public void testUserIsAbleToSignOut(String email, String password) {
        String expectedSignOutUrl = "https://account.bbc.com/signout";
        signOutSteps.verifyUserIsLoggedIn(email, password);
        signOutSteps.accountMenuIsClicked();
        signOutPage.clickButtonSignOut();
        ValidationHelper.assertLinkUrlMatches(Objects.requireNonNull(driver.getCurrentUrl()), expectedSignOutUrl, "User should be signed out and URL match");
    }
}
