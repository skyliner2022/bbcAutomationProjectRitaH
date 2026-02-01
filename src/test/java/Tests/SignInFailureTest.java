package Tests;

import Utilities.RandomDataGenerator;
import Utilities.ValidationHelper;
import io.qameta.allure.*;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import static org.testng.Assert.fail;

@Epic("Authentication")
@Feature("Sign In")
@Story("User cannot sign in with invalid credentials")
public class SignInFailureTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(SignInFailureTest.class);

    @BeforeMethod
    public void navigateToWebsiteAndOpenSignInPage() {
        visit("");
        waitForPageToLoadGlobal(driver, 10);
        signInPage.clickButtonOpenSignIn();
    }

    @Test(description = "User is not able to proceed to password input if email is not registered", dataProvider = "signInRandomEmail")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is not able to proceed to the password input page with an unregistered email")
    public void testUserCannotProceedToPasswordWithUnregisteredEmail(String email) {
        String expectedErrorMessage = "We don't recognise that email";
        String expectedPageSubTitle = "Enter your email";

        Allure.step("Submit a not registered email", () -> {
            signInFailureSteps.submitRegistrationEmail(email);
        });

        Allure.step("Verify that the error container is displayed", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signInPage.getErrorContainerGeneral(), "Error container is not displayed when non-registered email is input");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the error validation messages is " + expectedErrorMessage, () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getErrorMessage()), expectedErrorMessage, "The error message is not displayed: " + expectedErrorMessage);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the user stays on the email input page", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getEnterYourEmailTitle()), expectedPageSubTitle, "User is not on the email page after submit failure");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is not able to proceed to password input if email is empty", dataProvider = "signUpSignInEmailEmpty")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is not able to proceed to the next password input page with an empty email field")
    public void testUserCannotProceedToPasswordEmptyEmailField(String email) {
        String expectedErrorMessage = "Enter your email";
        String expectedPageSubTitle = "Enter your email";

        Allure.step("Submit empty email field", () -> {
            signInFailureSteps.submitRegistrationEmail(email);
        });

        Allure.step("Verify that the error container is displayed", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signInPage.getErrorContainerEmail(), "Error container is not displayed when email field is left empty");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the error validation messages is " + expectedErrorMessage, () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getErrorMessage()), expectedErrorMessage, "The error message is not displayed: " + expectedErrorMessage);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the user stays on the email input page", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getEnterYourEmailTitle()), expectedPageSubTitle, "User is not on the email page after submit failure");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is not able to proceed to password input if email has invalid format", dataProvider = "signUpSignInInvalidEmail")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is not able to proceed to the next password input page with an email having invalid format")
    public void testUserCannotProceedToPasswordIfEmailIsInvalidFormat(String email) {
        String expectedErrorMessage = "Sorry, that email doesn’t look right.";
        String expectedPageSubTitle = "Enter your email";

        Allure.step("Submit an email without domain", () -> {
            signInFailureSteps.submitRegistrationEmail(email);
        });

        Allure.step("Verify that the error container is displayed", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signInPage.getErrorContainerEmail(), "Error container is not displayed when email has invalid format");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the error validation messages is " + expectedErrorMessage, () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getErrorMessage()), expectedErrorMessage, "The error message is not displayed: " + expectedErrorMessage);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the user stays on the email input page", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getEnterYourEmailTitle()), expectedPageSubTitle, "User is not on the email page after submit failure");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is not able to complete sign-in if password input is invalid", dataProvider = "signInDefaultUserInvalidPassword")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is not able to complete sign-in with invalid registered password")
    public void testUserCannotSignInWithInvalidRegisteredPassword(String email, String password) {
        String expectedErrorMessage = "That password isn’t right. You can get an email with a link to sign in";
        String expectedPageSubTitle = "Enter your password";

        Allure.step("Submit the valid registered email", () -> {
            signInFailureSteps.submitRegistrationEmail(email);
        });

        Allure.step("Submit an invalid registered password", () -> {
            signInFailureSteps.submitRegistrationPassword(password);
        });

        Allure.step("Verify that the error container is displayed", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signInPage.getErrorContainerGeneral(), "Error container is not displayed when password is invalid");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the error validation messages is " + expectedErrorMessage, () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getErrorMessage()), expectedErrorMessage, "The error message is not displayed: " + expectedErrorMessage);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the user stays on the email input page", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getEnterYourPasswordTitle()), expectedPageSubTitle, "User is not on the password page after submit failure");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is not able to complete sign-in if password input is empty", dataProvider = "signInDefaultUserEmptyPassword")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is not able to complete sign-in with the empty password field")
    public void testUserCannotCompleteSignInWithEmptyPasswordField(String email, String password) {
        String expectedErrorMessage = "Enter a password";
        String expectedPageSubTitle = "Enter your password";

        Allure.step("Submit the valid registered email", () -> {
            signInFailureSteps.submitRegistrationEmail(email);
        });

        Allure.step("Submit empty password field", () -> {
            signInFailureSteps.submitRegistrationPassword(password);
        });

        Allure.step("Verify that the error container is displayed", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signInPage.getErrorContainerPassword(), "Error container is not displayed when password field is left empty");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the error validation messages is " + expectedErrorMessage, () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getErrorMessage()), expectedErrorMessage, "The error message is not displayed: " + expectedErrorMessage);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the user stays on the email input page", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getEnterYourPasswordTitle()), expectedPageSubTitle, "User is not on the password page after submit failure");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    //[JIRA: KAN-4]
    @Test(description = "User account is locked after the sixth failed attempt to submit password", dataProvider = "signInDefaultUserInvalidSixthPassword")
    @Severity(SeverityLevel.MINOR)
    @Description("This test validates that user's account is locked after the 6th unsuccessful attempt to submit the password")
    public void testAccountLocksAfterSixFailedAttempts(String email, String password) {
        String expectedPageTitle = "Sorry, your account is locked";

        Allure.step("Submit the valid registered email", () -> {
            signInFailureSteps.submitRegistrationEmail(email);
        });

        //Method contains logging of the count of tries to submit a password to track the reason of the test failure
        for (int i = 0; i < 6; i++) {
            int attempt = i + 1;

            Allure.step("Attempt #" + attempt + " of 6 to submit an invalid password", () -> {
                logger.info("Attempt #{} of 6", attempt);

                boolean isAccountLocked = false; //Flag to track if the account is locked

                try {
                    if (signInPage.getLockedAccountTitle().isDisplayed()) {
                        isAccountLocked = true; //Flag to track if element is found
                    }
                } catch (NoSuchElementException e) {
                    logger.warn("Locked account title not found on attempt #{}", attempt);
                }

                //Fail the test if the account is locked
                if (isAccountLocked) {
                    fail("The 'Sorry, your account is locked' page appeared too early on attempt #" + attempt);
                }

                //Continue the test with submitting the password if the account is not locked
                signInFailureSteps.submitRegistrationPassword(password);
            });
        }

        waitForPageToLoadGlobal(driver, 10);

        Allure.step("Verify that the user is redirected to the locked account page after 6 failed retries to submit the password", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getLockedAccountTitle()), expectedPageTitle, "User is not on the: " + expectedPageTitle + " page");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the locked account page has a link to recover the email", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signInPage.getMagicLinkRecoverAccount(), "Link to recover email is not displayed");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is not able to sign in if registration is not complete")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is not able to sign in if sign-up is not complete")
    public void testUserCannotLogInIfRegistrationIsNotComplete() {
        String email = RandomDataGenerator.randomEmail();
        String password = RandomDataGenerator.generateRandomPassword(8, true, true, true);
        String expectedErrorMessage = "We don't recognise that email";

        Allure.step("Close the sign-in form", () -> {
            signInPage.clickCloseButtonSignIn();
        });

        Allure.step("Open the sign-up form", () -> {
            signUpPage.clickButtonOpenSignUp();
        });

        Allure.step("Submit a valid email" + email, () -> {
            signUpFailureSteps.submitEmail(email);
        });

        Allure.step("Submit a valid password", () -> {
            signUpFailureSteps.submitPassword(password);
        });

        Allure.step("Navigate to the home page", () -> {
            visit("");
        });

        Allure.step("Open the sign-in form", () -> {
            signInPage.clickButtonOpenSignIn();
        });

        Allure.step("Submit the email " + email, () -> {
            signInFailureSteps.submitRegistrationEmail(email);
        });

        Allure.step("Verify that the error message about the unrecognised email is displayed", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signInPage.getErrorMessage()), expectedErrorMessage, "The error message is not displayed: " + expectedErrorMessage);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }
}
