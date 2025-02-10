package Tests;

import Utilities.ValidationHelper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignInSuccessTest extends BaseTest {

    @BeforeMethod
    public void navigateToWebsite() {
        visit("");
        BaseTest.waitForPageToLoadGlobal(driver, 10);
    }

    @AfterMethod
    public void logOut(ITestResult result) {
        //Only sign out after tests that require it
        if (!result.getMethod().getMethodName().equals("testSignInWithValidCredentialsSportPage")) {
            signOutSteps.userIsSignedOut();
            BaseTest.waitForPageToLoadGlobal(driver, 10);
        }
    }

    @Test(description = "User is able to sign in with valid credentials from sign in page", dataProvider = "signInDefaultUser", priority = 1)
    public void testSignInWithValidCredentialsSignIn(String email, String password) {
        signInSuccessSteps.signInSuccessSignIn(email, password);
        ValidationHelper.assertElementDisplayed(signInPage.getUserAccountButton(), "User account button should be visible after successful sign in");
    }

    @Test(description = "User is able to navigate to sign in page from sign up and log in", dataProvider = "signInDefaultUser", priority = 2)
    public void testSignInWithValidCredentialsSignUp(String email, String password) {
        signInSuccessSteps.signInSuccessSignUp(email, password);
        ValidationHelper.assertElementDisplayed(signInPage.getUserAccountButton(), "User account button should be visible after successful sign in");
    }

    @Test(description = "User is able to navigate to sign in page from Sport page and sign in", dataProvider = "signInDefaultUser", priority = 3)
    public void testSignInWithValidCredentialsSportPage(String email, String password) {
        headerNavigationPage.clickSportLink();
        BaseTest.waitForPageToLoadGlobal(driver, 10);
        signInPage.clickButtonOpenSignInSport();
        signInPage.inputEmailRegField(email);
        signInPage.clickButtonSignInSubmit();
        signInPage.inputPasswordRegField(password);
        signInPage.clickButtonSignInSubmit();
        BaseTest.waitForPageToLoadGlobal(driver, 10);
        ValidationHelper.assertElementDisplayed(signInPage.getUserAccountButtonSport(), "User account button should be visible after successful sign in");
    }
}
