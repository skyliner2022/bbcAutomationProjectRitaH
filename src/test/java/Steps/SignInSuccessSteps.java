package Steps;

import Pages.SignInPage;
import Pages.SignUpPage;

public class SignInSuccessSteps {

    SignInPage signInPage = new SignInPage();
    SignUpPage signUpPage = new SignUpPage();

    public void signInSuccessfullyFromSignInPage(String email, String password) {
        signInPage.clickButtonOpenSignIn();
        signInPage.inputValueIntoRegisteredEmailField(email);
        signInPage.clickButtonSignInSubmit();
        signInPage.inputValueIntoRegisteredPasswordField(password);
        signInPage.clickButtonSignInSubmit();
        signInPage.waitForAccountButtonToAppear();
    }

    public void signInSuccessfullyFromSignUpPage(String email, String password) {
        signUpPage.clickButtonOpenSignUp();
        signUpPage.clickLinkToSignInOnSignUpPage();
        signInPage.inputValueIntoRegisteredEmailField(email);
        signInPage.clickButtonSignInSubmit();
        signInPage.inputValueIntoRegisteredPasswordField(password);
        signInPage.clickButtonSignInSubmit();
        signInPage.waitForAccountButtonToAppear();
    }
}
