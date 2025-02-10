package Steps;

import Pages.SignInPage;
import Pages.SignUpPage;
import lombok.SneakyThrows;

public class SignUpSuccessSteps {

    SignUpPage signUpPage = new SignUpPage();
    SignInPage signInPage = new SignInPage();

    @SneakyThrows
    public void signUpSuccessSignUp(String email, String password, String day, String month, String year) {
        signUpPage.clickButtonOpenSignUp();
        signUpPage.inputEmailSignUpField(email);
        signUpPage.clickButtonSignUpSubmit();
        signUpPage.inputPasswordSignUpField(password);
        signUpPage.clickButtonSignUpSubmit();
        signUpPage.inputDetailsDayBirthInput(day);
        signUpPage.inputDetailsMonthBirthInput(month);
        signUpPage.inputDetailsYearBirthInput(year);
        signUpPage.clickButtonSignUpSubmit();
        //Wait for the system to process the page update (No reliable wait condition found)
        Thread.sleep(500); //TODO: Replace with better waiting if possible
        signUpPage.waitForSubmitButtonToAppear();
        signUpPage.clickButtonSignUpSubmit();
        signUpPage.clickNoThanksOption();
        signUpPage.clickButtonSignUpSubmit();
        signInPage.waitForAccountButtonToAppear();
    }

    @SneakyThrows
    public void signUpSuccessSignIn(String email, String password, String day, String month, String year) {
        signInPage.clickButtonOpenSignIn();
        signInPage.clickLinkToSignUp();
        signUpPage.inputEmailSignUpField(email);
        signUpPage.clickButtonSignUpSubmit();
        signUpPage.inputPasswordSignUpField(password);
        signUpPage.clickButtonSignUpSubmit();
        signUpPage.inputDetailsDayBirthInput(day);
        signUpPage.inputDetailsMonthBirthInput(month);
        signUpPage.inputDetailsYearBirthInput(year);
        signUpPage.clickButtonSignUpSubmit();
        //Wait for the system to process the page update (No reliable wait condition found)
        Thread.sleep(500); //TODO: Replace with better waiting if possible
        signUpPage.waitForSubmitButtonToAppear();
        signUpPage.clickButtonSignUpSubmit();
        signUpPage.clickCloseButton();
        signInPage.waitForAccountButtonToAppear();
    }

    @SneakyThrows
    public void signUpSuccessForgotEmail(String email, String password, String day, String month, String year) {
        signInPage.clickButtonOpenSignIn();
        signInPage.clickLinkForgottenEmail();
        signInPage.waitForButtonRegisterNewAccountToAppear();
        signInPage.clickButtonRegisterNewAccount();
        signUpPage.inputEmailSignUpField(email);
        signUpPage.clickButtonSignUpSubmit();
        signUpPage.inputPasswordSignUpField(password);
        signUpPage.clickButtonSignUpSubmit();
        signUpPage.inputDetailsDayBirthInput(day);
        signUpPage.inputDetailsMonthBirthInput(month);
        signUpPage.inputDetailsYearBirthInput(year);
        signUpPage.clickButtonSignUpSubmit();
        //Wait for the system to process the page update (No reliable wait condition found)
        Thread.sleep(500); //TODO: Replace with better waiting if possible
        signUpPage.waitForSubmitButtonToAppear();
        signUpPage.clickButtonSignUpSubmit();
        signUpPage.clickCloseButton();
        signInPage.waitForAccountButtonToAppear();
    }
}
