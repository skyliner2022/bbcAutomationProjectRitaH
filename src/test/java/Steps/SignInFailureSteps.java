package Steps;

import Pages.SignInPage;

public class SignInFailureSteps {

    SignInPage signInPage = new SignInPage();

    public void submitRegistrationEmail(String email) {
        signInPage.clearEmailField();
        signInPage.inputValueIntoRegisteredEmailField(email);
        signInPage.clickButtonSignInSubmit();
    }

    public void submitRegistrationPassword(String password) {
        signInPage.clearPasswordField();
        signInPage.inputValueIntoRegisteredPasswordField(password);
        signInPage.clickButtonSignInSubmit();
    }
}
