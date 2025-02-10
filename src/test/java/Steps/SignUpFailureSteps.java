package Steps;

import Pages.SignUpPage;

public class SignUpFailureSteps {

    SignUpPage signUpPage = new SignUpPage();

    public void submitNewEmail(String email) {
        signUpPage.inputEmailSignUpField(email);
        signUpPage.clickButtonSignUpSubmit();
    }

    public void submitNewPassword(String password) {
        signUpPage.inputPasswordSignUpField(password);
        signUpPage.clickButtonSignUpSubmit();
    }
}
