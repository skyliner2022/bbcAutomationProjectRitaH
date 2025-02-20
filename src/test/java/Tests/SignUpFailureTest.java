package Tests;

import Utilities.ValidationHelper;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Authentication")
@Feature("Sign Up")
@Story("User cannot sign up with invalid credentials")
public class SignUpFailureTest extends BaseTest {

    @BeforeMethod
    public void navigateToWebsiteAndOpenSignUpPage() {
        visit("");
        waitForPageToLoadGlobal(driver, 10);
        signUpPage.clickButtonOpenSignUp();
    }

    @Test(description = "User is not able to proceed to password if email is already registered", dataProvider = "signUpDefaultUserEmail")
    @Severity(SeverityLevel.MINOR)
    @Description("This test validates that user is not able to proceed to the password input page with the registered email")
    public void testUserCannotProceedToPasswordWithRegisteredEmailOnSignUpPage(String email) {
        String expectedErrorMessage = "This email is already registered. You can sign in";
        String expectedPageTitle = "Register for a BBC account";

        Allure.step("Submit the registered email", () -> {
            signUpFailureSteps.submitEmail(email);
        });

        Allure.step("Verify that the error container is displayed", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signUpPage.getErrorContainerEmailAlreadyExists(), "Error container is not displayed when an email is registered");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the error message is displayed", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessage()), expectedErrorMessage, "The error message is not displayed: " + expectedErrorMessage);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that user stays on the same page", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User is not on the page: " + expectedPageTitle);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is not able to proceed to password if email has invalid format", dataProvider = "signUpSignInInvalidEmail")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is not able to proceed to the password input page with an email having invalid format")
    public void testUserCannotProceedToPasswordIfEmailIsInvalidFormat(String email) {
        String expectedErrorMessage = "Sorry, that email doesn’t look right.";
        String expectedPageTitle = "Register for a BBC account";

        Allure.step("Submit an email having invalid format", () -> {
            signUpFailureSteps.submitEmail(email);
        });

        Allure.step("Verify that the error container is displayed", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signUpPage.getErrorContainerNewEmail(), "Error container is not be displayed when email has invalid pattern");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the error message is displayed", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessage()), expectedErrorMessage, "The error message is not displayed: " + expectedErrorMessage);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that user stays on the same page", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User is not on the page: " + expectedPageTitle);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is not able to proceed to password if email field is empty", dataProvider = "signUpSignInEmailEmpty")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is not able to proceed to the password input page with an empty email field")
    public void testUserCannotProceedToPasswordWithEmptyEmailField(String email) {
        String expectedErrorMessage = "Enter your email";
        String expectedPageTitle = "Register for a BBC account";

        Allure.step("Submit the empty email field", () -> {
            signUpFailureSteps.submitEmail(email);
        });

        Allure.step("Verify that the error container is displayed", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signUpPage.getErrorContainerNewEmail(), "Error container is not displayed when new email has invalid pattern");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the error message is displayed", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessage()), expectedErrorMessage, "The error message is not displayed: " + expectedErrorMessage);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that user stays on the same page", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User is not on the page: " + expectedPageTitle);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is not able to sign up if password is empty", dataProvider = "signUpPasswordEmpty")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates that user is not able to complete sign-up with the empty password field")
    public void testUserCannotCompleteSignUpWithEmptyPasswordField(String email, String password) {
        String expectedErrorMessage = "Enter a password";
        String expectedPageTitle = "Create your account password";

        Allure.step("Submit an email", () -> {
            signUpFailureSteps.submitEmail(email);
        });

        Allure.step("Submit the empty password field", () -> {
            signUpFailureSteps.submitPassword(password);
        });

        Allure.step("Verify that the error container is displayed", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signUpPage.getErrorContainerNewPassword(), "Error container is not displayed when new password is empty");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the error message is displayed", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessage()), expectedErrorMessage, "The error message is not displayed: " + expectedErrorMessage);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that user stays on the same page", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User is not on the page: " + expectedPageTitle);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is not able to sign up if password length is less than 8", dataProvider = "signUpSevenLengthPassword")
    @Severity(SeverityLevel.MINOR)
    @Description("This test validates that user is not able to complete sign-up with the password having less than 8 characters")
    public void testUserCannotCompleteSignInIfPasswordLengthIsSeven(String email, String password) {
        String expectedErrorMessage = "Sorry, that password is too short";
        String expectedErrorMessageSecondPart = "It needs to be eight characters or more.";
        String expectedPageTitle = "Create your account password";

        Allure.step("Submit an email", () -> {
            signUpFailureSteps.submitEmail(email);
        });

        Allure.step("Submit a password having length less than 8", () -> {
            signUpFailureSteps.submitPassword(password);
        });

        waitForPageToLoadGlobal(driver, 10);

        Allure.step("Verify that the error container is displayed", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signUpPage.getErrorContainerNewPassword(), "Error container is not displayed when new password is less than 8");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the error message is displayed", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessage()), expectedErrorMessage, "The error message is not displayed: " + expectedErrorMessage);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the second part of the error message is displayed", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessageSecondPart()), expectedErrorMessageSecondPart, "The error message is not displayed: " + expectedErrorMessageSecondPart);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the 8 length password requirement is not checked", () -> {
            try {
                ValidationHelper.assertClassOfElementMatches(signUpPage.getLengthRequirement(), signUpPage.getUncheckedClassValue(), "The 'At least eight characters' requirement is not unchecked.");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the at least one letter requirement is checked", () -> {
            try {
                ValidationHelper.assertClassOfElementMatches(signUpPage.getLetterRequirement(), signUpPage.getCheckedClassValue(), "The 'At least one letter' requirement is not checked.");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the at least one number of symbol requirement is checked", () -> {
            try {
                ValidationHelper.assertClassOfElementMatches(signUpPage.getSymbolNumberRequirement(), signUpPage.getCheckedClassValue(), "The 'At least one number or symbol' requirement is not checked.");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that user stays on the same page", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User is not on the page: " + expectedPageTitle);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is not able to sign up if password has no letter", dataProvider = "signUpNoLetterPassword")
    @Severity(SeverityLevel.MINOR)
    @Description("This test validates that user is not able to complete sign-up with the password having no letter")
    public void testUserCannotCompleteSignInIfPasswordHasNoLetter(String email, String password) {
        String expectedErrorMessage = "Sorry, that password isn't valid";
        String expectedErrorMessageSecondPart = "Please include a letter.";
        String expectedPageTitle = "Create your account password";

        Allure.step("Submit an email", () -> {
            signUpFailureSteps.submitEmail(email);
        });

        Allure.step("Submit a password having no letter", () -> {
            signUpFailureSteps.submitPassword(password);
        });

        Allure.step("Verify that the error container is displayed", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signUpPage.getErrorContainerNewPassword(), "Error container is not displayed when new password has no letter");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the error message is displayed", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessage()), expectedErrorMessage, "The error message is not displayed: " + expectedErrorMessage);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the second part of the error message is displayed", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessageSecondPart()), expectedErrorMessageSecondPart, "The error message is not displayed: " + expectedErrorMessageSecondPart);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the at least one letter requirement is not checked", () -> {
            try {
                ValidationHelper.assertClassOfElementMatches(signUpPage.getLetterRequirement(), signUpPage.getUncheckedClassValue(), "The 'At least one letter' requirement is not unchecked.");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the 8 length password requirement is checked", () -> {
            try {
                ValidationHelper.assertClassOfElementMatches(signUpPage.getLengthRequirement(), signUpPage.getCheckedClassValue(), "The 'At least eight characters' requirement is not checked.");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the at least one number of symbol requirement is checked", () -> {
            try {
                ValidationHelper.assertClassOfElementMatches(signUpPage.getSymbolNumberRequirement(), signUpPage.getCheckedClassValue(), "The 'At least one number or symbol' requirement is not checked.");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that user stays on the same page", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User is not on the page: " + expectedPageTitle);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    @Test(description = "User is not able to sign up if password has no numbers and specials characters", dataProvider = "signUpNoSpecialCharactersAndNumbersPassword")
    @Severity(SeverityLevel.MINOR)
    @Description("This test validates that user is not able to complete sign-up with the password having no number or special character")
    public void testUserCannotCompleteSignInIfPasswordHasNoCharactersAndNumbers(String email, String password) {
        String expectedErrorMessage = "Sorry, that password isn't valid";
        String expectedErrorMessageSecondPart = "Please include something that isn't a letter.";
        String expectedPageTitle = "Create your account password";

        Allure.step("Submit an email", () -> {
            signUpFailureSteps.submitEmail(email);
        });

        Allure.step("Submit password having no numbers or special characters", () -> {
            signUpFailureSteps.submitPassword(password);
        });

        Allure.step("Verify that the error container is displayed", () -> {
            try {
                ValidationHelper.assertElementDisplayed(signUpPage.getErrorContainerNewPassword(), "Error container is not displayed when new password has no special characters and numbers");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the error message is displayed", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessage()), expectedErrorMessage, "The error message is not displayed: " + expectedErrorMessage);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the second part of the error message is displayed", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessageSecondPart()), expectedErrorMessageSecondPart, "The error message is not displayed: " + expectedErrorMessageSecondPart);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the at least one number of symbol requirement is not checked", () -> {
            try {
                ValidationHelper.assertClassOfElementMatches(signUpPage.getSymbolNumberRequirement(), signUpPage.getUncheckedClassValue(), "The 'At least one number or symbol' requirement is not unchecked.");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the 8 length password requirement is checked", () -> {
            try {
                ValidationHelper.assertClassOfElementMatches(signUpPage.getLengthRequirement(), signUpPage.getCheckedClassValue(), "The 'At least eight characters' requirement is not checked.");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that the at least one letter requirement is checked", () -> {
            try {
                ValidationHelper.assertClassOfElementMatches(signUpPage.getLetterRequirement(), signUpPage.getCheckedClassValue(), "The 'At least one letter' requirement is not checked.");
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });

        Allure.step("Verify that user stays on the same page", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User is not on the page: " + expectedPageTitle);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }

    //[JIRA: KAN-2], [JIRA: KAN-5]
    @Test(description = "Validation message is displayed when password is compromised", dataProvider = "signUpCompromisedPassword")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("This test validates that the compromised password info message is showing up if a password is in the list of the compromised passwords")
    public void testValidationMessageIfPasswordIsCompromised(String email, String password) {
        String expectedErrorMessage = "That password has been part of a data breach elsewhere on the internet. We suggest you pick something different.";

        Allure.step("Submit an email", () -> {
            signUpFailureSteps.submitEmail(email);
        });

        Allure.step("Submit a compromised password", () -> {
            signUpPage.inputValueIntoPasswordFieldOnSignUpPage(password);
        });

        Allure.step("Verify that the info message informing about the compromised password is displayed", () -> {
            try {
                ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getPasswordCompromisedErrorMessage()), expectedErrorMessage, "The error message is not displayed: " + expectedErrorMessage);
            } catch (AssertionError e) {
                Allure.step("Assertion failed with message: " + e.getMessage());
                throw e;
            }
        });
    }
}
