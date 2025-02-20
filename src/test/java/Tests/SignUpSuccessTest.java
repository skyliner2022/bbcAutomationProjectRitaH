package Tests;

import Utilities.ValidationHelper;
import io.qameta.allure.*;
import org.openqa.selenium.NoSuchSessionException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Epic("Authentication")
@Feature("Sign Up")
@Story("User can sign up successfully")
public class SignUpSuccessTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(SignUpSuccessTest.class);

    @BeforeMethod(alwaysRun = true)
    public void navigateToWebsite() {
        visit("");
        waitForPageToLoadGlobal(driver, 10);
    }

    @AfterMethod(alwaysRun = true)
    public void logOut() {
        //This try-catch block fixes the issue with the NoSuchSessionException error when executing tests in testng
        try {
            signOutSteps.signOutFromAccount();
        } catch (NoSuchSessionException e) {
            log.info("NoSuchSessionException is caught");
        }
        clearBrowserData();
    }

    @Test(description = "User is able to sign up with valid credentials from sign-up page and refuse to subscribe to emails", dataProvider = "signUpDataValid")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is able to sign up successfully with email subscription declined on the sign-up page")
    public void testSignUpWithValidCredentialsOnSignUpPageAndRefuseToSubscribe(String email, String password, String day, String month, String year) {
        Allure.step("Sign up with email subscription declined on the sign-up page " + email + " and password " + password, () -> {
            signUpSuccessSteps.signUpSuccessfullyFromSignUpPage(email, password, day, month, year);
        });

        Allure.step("Verify that account button is displayed after successful sign up", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signInPage.getUserAccountButton(), "User account button is not displayed after successful sign up");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is able to sign up with valid credentials from sign-in page and skip email subscription form", dataProvider = "signUpDataValid")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is able to sign up successfully with subscription to emails consents skipped on the sign-in page")
    public void testSignUpWithValidCredentialsOnSignInPageAndSkipSubscriptionFrom(String email, String password, String day, String month, String year) {
        Allure.step("Sign up with subscription to emails consents skipped on the sign-in page " + email + " and password " + password, () -> {
            signUpSuccessSteps.signUpSuccessfullyFromSignInPage(email, password, day, month, year);
        });

        Allure.step("Verify that account button is displayed after successful sign up", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signInPage.getUserAccountButton(), "User account button is not displayed after successful sign up");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is able to sign up with valid credentials using forgot email flow", dataProvider = "signUpDataValid")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is able to sign up successfully from the forgot email link")
    public void testSignUpWithValidCredentialsViaForgotEmailLink(String email, String password, String day, String month, String year) {
        Allure.step("Sign up from the forgot email link " + email + " and password " + password, () -> {
            signUpSuccessSteps.signUpSuccessfullyByForgotEmailLink(email, password, day, month, year);
        });

        Allure.step("Verify that account button is displayed after successful sign up", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signInPage.getUserAccountButton(), "User account button is not displayed after successful sign up");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }
}
