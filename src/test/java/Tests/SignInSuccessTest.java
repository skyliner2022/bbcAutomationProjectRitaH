package Tests;

import Utilities.ValidationHelper;
import io.qameta.allure.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Authentication")
@Feature("Sign In")
@Story("User can sign in with valid credentials")
public class SignInSuccessTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void navigateToWebsite() {
        visit("");
        waitForPageToLoadGlobal(driver, 10);
    }

    @AfterMethod(alwaysRun = true)
    public void logOut(ITestResult result) {
        //Only sign out after tests that require it
        if (!result.getMethod().getMethodName().equals("testSignInWithValidCredentialsOnSportPage")) {
            signOutSteps.signOutFromAccount();
        }
        clearBrowserData();
    }

    @Test(description = "User is able to sign in with valid credentials from sign in page", dataProvider = "signInDefaultUser", priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is able to sign in successfully from the sign in page using credentials of the already registered user")
    public void testSignInWithValidCredentialsOnSignInPage(String email, String password) {
        Allure.step("Log in with the registered valid email " + email + " and password " + password, () -> {
            signInSuccessSteps.signInSuccessfullyFromSignInPage(email, password);
        });

        Allure.step("Verify that account button is displayed after successful sign-in", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signInPage.getUserAccountButton(), "User account button is not displayed after successful sign-in");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is able to navigate to sign in page from sign up", dataProvider = "signInDefaultUser", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is able to sign in successfully from the sign up page using the sign in link on the page")
    public void testSignInWithValidCredentialsOnSignUpPage(String email, String password) {
        Allure.step("Log in with the registered valid email " + email + " and password " + password, () -> {
            signInSuccessSteps.signInSuccessfullyFromSignUpPage(email, password);
        });

        Allure.step("Verify that user account icon is displayed after the successful sign-in", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signInPage.getUserAccountButton(), "User account button is not displayed after successful sign-in");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is able to navigate to sign in page from Sport page and sign in", dataProvider = "signInDefaultUser", priority = 3)
    @Severity(SeverityLevel.MINOR)
    @Description("This test validates that user is able to open the sign in form on the Sports page and successfully sign in")
    public void testSignInWithValidCredentialsOnSportPage(String email, String password) {
        Allure.step("Click the Sport link", () -> {
            headerNavigationPage.clickSportLink();
        });

        Allure.step("Wait for the page to fully load", () -> {
            waitForPageToLoadGlobal(driver, 10);
        });

        Allure.step("Click the sign in button to open the sign in form on the Sport page", () -> {
            signInPage.clickButtonOpenSignInSport();
        });

        Allure.step("Input the registered valid email into the email field", () -> {
            signInPage.inputValueIntoRegisteredEmailField(email);
        });

        Allure.step("Confirm the input email", () -> {
            signInPage.clickButtonSignInSubmit();
        });

        Allure.step("Input the registered valid password into the password field", () -> {
            signInPage.inputValueIntoRegisteredPasswordField(password);
        });

        Allure.step("Confirm the input password", () -> {
            signInPage.clickButtonSignInSubmit();
        });

        Allure.step("Wait for the page to fully load", () -> {
            waitForPageToLoadGlobal(driver, 10);
        });

        Allure.step("Verify that account icon is displayed after the successful sign-in", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signInPage.getUserAccountButtonSport(), "User account button is not displayed after successful sign-in");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }
}
