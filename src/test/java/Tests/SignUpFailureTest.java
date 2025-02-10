package Tests;

import Utilities.ValidationHelper;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpFailureTest extends BaseTest {

    @BeforeMethod
    public void navigateToWebsiteAndOpenSignUpPage() {
        visit("");
        BaseTest.waitForPageToLoadGlobal(driver, 10);
        signUpPage.clickButtonOpenSignUp();
    }

    @Test(description = "User is not able to proceed to password if email is already registered", dataProvider = "signUpDefaultUserEmail")
    public void testCannotGetToPasswordIfEmailIsRegistered(String email) {
        String expectedErrorMessage = "This email is already registered. You can sign in";
        String expectedPageTitle = "Register for a BBC account";
        signUpFailureSteps.submitNewEmail(email);
        ValidationHelper.assertElementDisplayed(signUpPage.getErrorContainerEmailAlreadyExists(), "Error container should be displayed when new email has already registered");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessage()), expectedErrorMessage, "The error message should be 'This email is already registered. You can sign in'");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User should stay on the email page");
    }

    @Test(description = "User is not able to proceed to password if new email has invalid pattern", dataProvider = "signUpSignInInvalidEmail")
    public void testCannotGetToPasswordIfNewEmailIsInvalid(String email) {
        String expectedErrorMessage = "Sorry, that email doesn’t look right.";
        String expectedPageTitle = "Register for a BBC account";
        signUpFailureSteps.submitNewEmail(email);
        ValidationHelper.assertElementDisplayed(signUpPage.getErrorContainerNewEmail(), "Error container should be displayed when new email has invalid pattern");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessage()), expectedErrorMessage, "The error message should be 'Sorry, that email doesn’t look right.'");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User should stay on the email page");
    }

    @Test(description = "User is not able to proceed to password if new email is empty", dataProvider = "signUpSignInEmailEmpty")
    public void testCannotGetToPasswordIfNewEmailIsEmpty(String email) {
        String expectedErrorMessage = "Enter your email";
        String expectedPageTitle = "Register for a BBC account";
        signUpFailureSteps.submitNewEmail(email);
        ValidationHelper.assertElementDisplayed(signUpPage.getErrorContainerNewEmail(), "Error container should be displayed when new email has invalid pattern");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessage()), expectedErrorMessage, "The error message should be 'Enter your email'");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User should stay on the email page");
    }

    @Test(description = "User is not able to sign up if new password is empty", dataProvider = "signUpPasswordEmpty")
    public void testCannotCompleteSignInIfNewPasswordIsEmpty(String email, String password) {
        String expectedErrorMessage = "Enter a password";
        String expectedPageTitle = "Create your account password";
        signUpFailureSteps.submitNewEmail(email);
        signUpFailureSteps.submitNewPassword(password);
        ValidationHelper.assertElementDisplayed(signUpPage.getErrorContainerNewPassword(), "Error container should be displayed when new password is empty");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessage()), expectedErrorMessage, "The error message should be 'Enter a password'");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User should stay on the password page");
    }

    @Test(description = "User is not able to sign up if new password is less than 8", dataProvider = "signUpSevenLengthPassword")
    public void testCannotCompleteSignInIfNewPasswordLengthIsSeven(String email, String password) {
        String expectedErrorMessage = "Sorry, that password is too short";
        String expectedErrorMessageSecondPart = "It needs to be eight characters or more.";
        String expectedPageTitle = "Create your account password";
        signUpFailureSteps.submitNewEmail(email);
        signUpFailureSteps.submitNewPassword(password);
        BaseTest.waitForPageToLoadGlobal(driver, 10);
        ValidationHelper.assertElementDisplayed(signUpPage.getErrorContainerNewPassword(), "Error container should be displayed when new password is less than 8");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessage()), expectedErrorMessage, "The error message should be 'Sorry, that password is too short.'");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessageSecondPart()), expectedErrorMessageSecondPart, "The error message should be 'It needs to be eight characters or more.'");
        ValidationHelper.assertClassOfElementMatches(signUpPage.getLengthRequirement(), signUpPage.getUncheckedClassValue(), "The 'At least eight characters' requirement should be unchecked.");
        ValidationHelper.assertClassOfElementMatches(signUpPage.getLetterRequirement(), signUpPage.getCheckedClassValue(), "The 'At least one letter' requirement should be checked.");
        ValidationHelper.assertClassOfElementMatches(signUpPage.getSymbolNumberRequirement(), signUpPage.getCheckedClassValue(), "The 'At least one number or symbol' requirement should be checked.");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User should stay on the password page");
    }

    @Test(description = "User is not able to sign up if new password has no letter", dataProvider = "signUpNoLetterPassword")
    public void testCannotCompleteSignInIfNewPasswordHasNoLetter(String email, String password) {
        String expectedErrorMessage = "Sorry, that password isn't valid";
        String expectedErrorMessageSecondPart = "Please include a letter.";
        String expectedPageTitle = "Create your account password";
        signUpFailureSteps.submitNewEmail(email);
        signUpFailureSteps.submitNewPassword(password);
        ValidationHelper.assertElementDisplayed(signUpPage.getErrorContainerNewPassword(), "Error container should be displayed when new password has no letter");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessage()), expectedErrorMessage, "The error message should be 'Sorry, that password isn't valid.'");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessageSecondPart()), expectedErrorMessageSecondPart, "The error message should be 'Please include a letter.'");
        ValidationHelper.assertClassOfElementMatches(signUpPage.getLetterRequirement(), signUpPage.getUncheckedClassValue(), "The 'At least one letter' requirement should be unchecked.");
        ValidationHelper.assertClassOfElementMatches(signUpPage.getLengthRequirement(), signUpPage.getCheckedClassValue(), "The 'At least eight characters' requirement should be checked.");
        ValidationHelper.assertClassOfElementMatches(signUpPage.getSymbolNumberRequirement(), signUpPage.getCheckedClassValue(), "The 'At least one number or symbol' requirement should be checked.");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User should stay on the password page");
    }

    @Test(description = "User is not able to sign up if new password has no numbers and specials characters", dataProvider = "signUpNoCharactersAndNumbersPassword")
    public void testCannotCompleteSignInIfNewPasswordHasNoCharactersAndNumbers(String email, String password) {
        String expectedErrorMessage = "Sorry, that password isn't valid";
        String expectedErrorMessageSecondPart = "Please include something that isn't a letter.";
        String expectedPageTitle = "Create your account password";
        signUpFailureSteps.submitNewEmail(email);
        signUpFailureSteps.submitNewPassword(password);
        ValidationHelper.assertElementDisplayed(signUpPage.getErrorContainerNewPassword(), "Error container should be displayed when new password has no special characters and numbers");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessage()), expectedErrorMessage, "The error message should be 'Sorry, that password isn't valid.'");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getErrorMessageSecondPart()), expectedErrorMessageSecondPart, "The error message should be 'Please include something that isn't a letter.'");
        ValidationHelper.assertClassOfElementMatches(signUpPage.getSymbolNumberRequirement(), signUpPage.getUncheckedClassValue(), "The 'At least one number or symbol' requirement should be unchecked.");
        ValidationHelper.assertClassOfElementMatches(signUpPage.getLengthRequirement(), signUpPage.getCheckedClassValue(), "The 'At least eight characters' requirement should be checked.");
        ValidationHelper.assertClassOfElementMatches(signUpPage.getLetterRequirement(), signUpPage.getCheckedClassValue(), "The 'At least one letter' requirement should be checked.");
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getRegisterForAccountAndPasswordTitle()), expectedPageTitle, "User should stay on the password page");
    }

    //In this test was added a step to show user password to override issue related to bypassing the info message
    @SneakyThrows
    @Test(description = "Validation message is displayed when new password is compromised", dataProvider = "signUpCompromisedPassword")
    public void testCannotCompleteSignInIfNewPasswordIsCompromised(String email, String password) {
        String expectedErrorMessage = "That password has been part of a data breach elsewhere on the internet. We suggest you pick something different.";
        signUpFailureSteps.submitNewEmail(email);
        signUpPage.inputPasswordSignUpField(password);
        signUpPage.clickButtonShowPassword();
        //Wait for the system to override the issue with the info message not appearing
        Thread.sleep(500); //TODO: Replace with better waiting if possible
        ValidationHelper.assertTextEquals(ValidationHelper.getText(signUpPage.getPasswordCompromisedErrorMessage()), expectedErrorMessage, "The error message should be 'That password has been part of a data breach elsewhere on the internet'");
    }
}
