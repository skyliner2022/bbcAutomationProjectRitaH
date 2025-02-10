package Tests;

import Utilities.ValidationHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpSuccessTest extends BaseTest {
    @BeforeMethod
    public void navigateToWebsite() {
        visit("");
        BaseTest.waitForPageToLoadGlobal(driver, 10);
    }

    @AfterMethod
    public void logOut() {
        BaseTest.waitForPageToLoadGlobal(driver, 10);
        signOutSteps.userIsSignedOut();
    }

    //Test also includes flow to refuse from emails subscriptions
    @Test(description = "User is able to sign up with valid credentials from sign up page", dataProvider = "signUpDataValid")
    public void testSignUpWithValidCredentialsSignUp(String email, String password, String day, String month, String year) {
        signUpSuccessSteps.signUpSuccessSignUp(email, password, day, month, year);
        ValidationHelper.assertElementDisplayed(signInPage.getUserAccountButton(), "User account button should be visible after successful sign up");
    }

    //Test also includes flow with exclusion of the email subscription consents steps
    @Test(description = "User is able to sign up with valid credentials from sign in page", dataProvider = "signUpDataValid")
    public void testSignUpWithValidCredentialsSignIn(String email, String password, String day, String month, String year) {
        signUpSuccessSteps.signUpSuccessSignIn(email, password, day, month, year);
        ValidationHelper.assertElementDisplayed(signInPage.getUserAccountButton(), "User account button should be visible after successful sign up");
    }

    @Test(description = "User is able to sign up with valid credentials from forgot email flow", dataProvider = "signUpDataValid")
    public void testSignUpWIthValidCredentialForgotEmail(String email, String password, String day, String month, String year) {
        signUpSuccessSteps.signUpSuccessForgotEmail(email, password, day, month, year);
        ValidationHelper.assertElementDisplayed(signInPage.getUserAccountButton(), "User account button should be visible after successful sign up");
    }
}
