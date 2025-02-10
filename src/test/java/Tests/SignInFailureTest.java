package Tests;

import Utilities.RandomDataGenerator;
import Utilities.ValidationHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SignInFailureTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(SignInFailureTest.class);

    @BeforeMethod
    public void navigateToWebsiteAndOpenSignInPage() {
        visit("");
        BaseTest.waitForPageToLoadGlobal(driver, 10);
        signInPage.clickButtonOpenSignIn();
    }

    @Test(description = "User is not able to proceed to password input if email is not registered", dataProvider = "signInRandomEmail")
    public void testCannotGetToPasswordIfRegEmailNotRegistered(String email) {
        String expectedErrorMessage = "We don't recognise that email";
        String expectedPageSubTitle = "Enter your email";
        signInFailureSteps.submitRegEmail(email);
        ValidationHelper.assertElementDisplayed(signInPage.getErrorContainerGeneral(), "Error container should be displayed when non-registered email is input");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getErrorMessage()), expectedErrorMessage, "The error message should be 'We don't recognise that email'");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getEnterYourEmailTitle()), expectedPageSubTitle, "User should stay on the email page");
    }

    @Test(description = "User is not able to proceed to password input if email is empty", dataProvider = "signUpSignInEmailEmpty")
    public void testCannotGetToPasswordIfRegEmailIsEmpty(String email) {
        String expectedErrorMessage = "Enter your email";
        String expectedPageSubTitle = "Enter your email";
        signInFailureSteps.submitRegEmail(email);
        ValidationHelper.assertElementDisplayed(signInPage.getErrorContainerEmail(), "Error container should be displayed when email field is left empty");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getErrorMessage()), expectedErrorMessage, "The error message should be 'Enter your email'");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getEnterYourEmailTitle()), expectedPageSubTitle, "User should stay on the email page");
    }

    @Test(description = "User is not able to proceed to password input if email has invalid pattern", dataProvider = "signUpSignInInvalidEmail")
    public void testCannotGetToPasswordIfRegEmailIsInvalid(String email) {
        String expectedErrorMessage = "Sorry, that email doesn’t look right.";
        String expectedPageSubTitle = "Enter your email";
        signInFailureSteps.submitRegEmail(email);
        ValidationHelper.assertElementDisplayed(signInPage.getErrorContainerEmail(), "Error container should be displayed when email has invalid pattern");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getErrorMessage()), expectedErrorMessage, "The error message should be 'Sorry, that email doesn’t look right.'");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getEnterYourEmailTitle()), expectedPageSubTitle, "User should stay on the email page");
    }

    @Test(description = "User is not able to complete sign in if password input is invalid", dataProvider = "signInDefaultUserInvalidPassword")
    public void testCannotCompleteSignInIfRegPasswordIsInvalid(String email, String password) {
        String expectedErrorMessage = "That password isn’t right. You can get an email with a link to sign in";
        String expectedPageSubTitle = "Enter your password";
        signInFailureSteps.submitRegEmail(email);
        signInFailureSteps.submitRegPassword(password);
        ValidationHelper.assertElementDisplayed(signInPage.getErrorContainerGeneral(), "Error container should be displayed when password is invalid");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getErrorMessage()), expectedErrorMessage, "The error message should be 'That password isn’t right. You can get an email with a link to sign in'");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getEnterYourPasswordTitle()), expectedPageSubTitle, "User should stay on the password page");
    }

    @Test(description = "User is not able to complete sign in if password input is empty", dataProvider = "signInDefaultUserEmptyPassword")
    public void testCannotCompleteSignInIfRegPasswordIsEmpty(String email, String password) {
        String expectedErrorMessage = "Enter a password";
        String expectedPageSubTitle = "Enter your password";
        signInFailureSteps.submitRegEmail(email);
        signInFailureSteps.submitRegPassword(password);
        ValidationHelper.assertElementDisplayed(signInPage.getErrorContainerPassword(), "Error container should be displayed when password field is left empty");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getErrorMessage()), expectedErrorMessage, "The error message should be 'Enter a password'");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getEnterYourPasswordTitle()), expectedPageSubTitle, "User should stay on the password page");
    }

    @Test(description = "User account is locked after the sixth failed attempt", dataProvider = "signInDefaultUserInvalidSixthPassword")
    public void testAccountIsLockedOnSixthAttempt(String email, String password) {
        String expectedPageTitle = "Sorry, your account is locked";
        signInFailureSteps.submitRegEmail(email);
        //Method contains logging of the count of tries to submit a password to track the reason of the test failure
        for (int i = 0; i < 6; i++) {
            logger.info("Attempt #{} of 6", i + 1);
            signInFailureSteps.submitRegPassword(password);
        }
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getLockedAccountTitle()), expectedPageTitle, "User should be on the account is locked page");
        ValidationHelper.assertElementDisplayed(signInPage.getMagicLinkRecoverAccount(), "Link to sent recovery email should be displayed");
    }

    @Test(description = "User is not able to log in if registration is not complete")
    public void testCannotLogInIfRegistrationNotComplete() {
        String email = RandomDataGenerator.randomEmail();
        String password = RandomDataGenerator.randomPassword();
        String expectedErrorMessage = "We don't recognise that email";
        signInPage.clickCloseButtonSignIn();
        signUpPage.clickButtonOpenSignUp();
        signUpFailureSteps.submitNewEmail(email);
        signUpFailureSteps.submitNewPassword(password);
        visit("");
        signInPage.clickButtonOpenSignIn();
        signInFailureSteps.submitRegEmail(email);
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getErrorMessage()), expectedErrorMessage, "The error message should be 'We don't recognise that email' when registration is not completed");
    }
}
