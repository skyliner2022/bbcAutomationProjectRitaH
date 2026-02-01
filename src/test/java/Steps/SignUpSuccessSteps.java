package Steps;

import Pages.SignInPage;
import Pages.SignUpPage;
import lombok.SneakyThrows;

public class SignUpSuccessSteps {

    SignUpPage signUpPage = new SignUpPage();
    SignInPage signInPage = new SignInPage();

    @SneakyThrows
    public void signUpSuccessfullyFromSignUpPage(String email, String password, String day, String month, String year) {
        signUpPage.clickButtonOpenSignUp();
        signUpPage.inputValueIntoEmailFieldOnSignUpPage(email);
        signUpPage.clickButtonSubmitOnSignUpPage();
        signUpPage.inputValueIntoPasswordFieldOnSignUpPage(password);
        signUpPage.clickButtonSubmitOnSignUpPage();
        signUpPage.inputValueIntoDayOfBirthField(day);
        signUpPage.inputValueIntoMonthOfBirthField(month);
        signUpPage.inputValueIntoYearOfBirthField(year);
        signUpPage.clickButtonSubmitOnSignUpPage();
        //Wait for the system to process the page update (No reliable wait condition found)
        Thread.sleep(500); //TODO: Replace with better waiting if possible
        signUpPage.waitForSubmitButtonToAppear();
        signUpPage.clickButtonSubmitOnSignUpPage();
        signUpPage.clickNoThanksOption();
        signUpPage.clickButtonSubmitOnSignUpPage();
        signInPage.waitForAccountButtonToAppear();
    }

    @SneakyThrows
    public void signUpSuccessfullyFromSignInPage(String email, String password, String day, String month, String year) {
        signInPage.clickButtonOpenSignIn();
        signInPage.clickLinkToSignUp();
        signUpPage.inputValueIntoEmailFieldOnSignUpPage(email);
        signUpPage.clickButtonSubmitOnSignUpPage();
        signUpPage.inputValueIntoPasswordFieldOnSignUpPage(password);
        signUpPage.clickButtonSubmitOnSignUpPage();
        signUpPage.inputValueIntoDayOfBirthField(day);
        signUpPage.inputValueIntoMonthOfBirthField(month);
        signUpPage.inputValueIntoYearOfBirthField(year);
        signUpPage.clickButtonSubmitOnSignUpPage();
        //Wait for the system to process the page update (No reliable wait condition found)
        Thread.sleep(500); //TODO: Replace with better waiting if possible
        signUpPage.waitForSubmitButtonToAppear();
        signUpPage.clickButtonSubmitOnSignUpPage();
        signUpPage.clickCloseButton();
        signInPage.waitForAccountButtonToAppear();
    }

    @SneakyThrows
    public void signUpSuccessfullyByForgotEmailLink(String email, String password, String day, String month, String year) {
        signInPage.clickButtonOpenSignIn();
        signInPage.clickLinkForgottenEmail();
        signInPage.waitForButtonRegisterNewAccountToAppear();
        signInPage.clickButtonRegisterNewAccount();
        signUpPage.inputValueIntoEmailFieldOnSignUpPage(email);
        signUpPage.clickButtonSubmitOnSignUpPage();
        signUpPage.inputValueIntoPasswordFieldOnSignUpPage(password);
        signUpPage.clickButtonSubmitOnSignUpPage();
        signUpPage.inputValueIntoDayOfBirthField(day);
        signUpPage.inputValueIntoMonthOfBirthField(month);
        signUpPage.inputValueIntoYearOfBirthField(year);
        signUpPage.clickButtonSubmitOnSignUpPage();
        //Wait for the system to process the page update (No reliable wait condition found)
        Thread.sleep(500); //TODO: Replace with better waiting if possible
        signUpPage.waitForSubmitButtonToAppear();
        signUpPage.clickButtonSubmitOnSignUpPage();
        signUpPage.clickCloseButton();
        signInPage.waitForAccountButtonToAppear();
    }
}
