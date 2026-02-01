package Tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Authentication")
@Feature("Sign Out")
@Story("User can sign out successfully")
public class SignOutTest extends BaseTest {

    @BeforeMethod
    public void navigateToWebsiteAndOpenSignInPage() {
        visit("");
        waitForPageToLoadGlobal(driver, 10);
    }

    @Test(description = "User is able to sign out", dataProvider = "signInDefaultUser")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test validates the sign-out functionality of the user by checking if they are redirected to the sign-out page after logging out.")
    public void testUserCanSignOut(String email, String password) {
        String expectedSignOutUrl = "https://account.bbc.com/signout";

        Allure.step("Log in with the registered valid email " + email + " and password " + password, () -> {
            signOutSteps.logInWithValidCredentials(email, password);
        });

        Allure.step("Open the account menu", () -> {
            signOutSteps.openAccountMenu();
        });

        Allure.step("Click the sign-out button", () -> {
            signOutPage.clickButtonSignOut();
        });

        Allure.step("Verify redirection to " + expectedSignOutUrl + " after signing out", () -> {
            try {
                Assert.assertEquals(driver.getCurrentUrl(), expectedSignOutUrl, "Redirection URL does not match the expected value.");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }
}
