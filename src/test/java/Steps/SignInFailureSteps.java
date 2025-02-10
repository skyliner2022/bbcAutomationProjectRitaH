package Steps;

import Pages.SignInPage;

public class SignInFailureSteps {

    SignInPage signInPage = new SignInPage();

    public void submitRegEmail(String email) {
        signInPage.inputEmailRegField(email);
        signInPage.clickButtonSignInSubmit();
    }

    public void submitRegPassword(String password) {
        signInPage.inputPasswordRegField(password);
        signInPage.clickButtonSignInSubmit();
    }
}
