package Steps;

import Pages.SignUpPage;

public class SignUpFailureSteps {

    SignUpPage signUpPage = new SignUpPage();

    public void submitEmail(String email) {
        signUpPage.inputValueIntoEmailFieldOnSignUpPage(email);
        signUpPage.clickButtonSubmitOnSignUpPage();
    }

    public void submitPassword(String password) {
        signUpPage.inputValueIntoPasswordFieldOnSignUpPage(password);
        signUpPage.clickButtonSubmitOnSignUpPage();
    }
}
