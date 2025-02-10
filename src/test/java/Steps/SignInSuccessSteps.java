package Steps;

import Pages.SignInPage;
import Pages.SignUpPage;

public class SignInSuccessSteps {

    SignInPage signInPage = new SignInPage();
    SignUpPage signUpPage = new SignUpPage();

    public void signInSuccessSignIn(String email, String password) {
        signInPage.clickButtonOpenSignIn();
        signInPage.inputEmailRegField(email);
        signInPage.clickButtonSignInSubmit();
        signInPage.inputPasswordRegField(password);
        signInPage.clickButtonSignInSubmit();
        signInPage.waitForAccountButtonToAppear();
    }

    public void signInSuccessSignUp(String email, String password) {
        signUpPage.clickButtonOpenSignUp();
        signUpPage.clickLinkToSignIn();
        signInPage.inputEmailRegField(email);
        signInPage.clickButtonSignInSubmit();
        signInPage.inputPasswordRegField(password);
        signInPage.clickButtonSignInSubmit();
        signInPage.waitForAccountButtonToAppear();
    }
}
