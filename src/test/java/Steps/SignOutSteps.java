package Steps;

import Pages.SignInPage;
import Pages.SignOutPage;
import Utilities.ValidationHelper;

public class SignOutSteps {

    static SignInSuccessSteps signInSuccessSteps = new SignInSuccessSteps();
    static SignInPage signInPage = new SignInPage();
    SignOutPage signOutPage = new SignOutPage();

    public void logInWithValidCredentials(String email, String password) {
        signInSuccessSteps.signInSuccessfullyFromSignInPage(email, password);
    }

    public void openAccountMenu() {
        ValidationHelper.clickElement(signInPage.getUserAccountButton());
        ValidationHelper.assertElementDisplayed(signOutPage.getButtonSignOut(), "Sign out button should be visible");
    }

    public void signOutFromAccount() {
        ValidationHelper.clickElement(signInPage.getUserAccountButton());
        ValidationHelper.assertElementDisplayed(signOutPage.getButtonSignOut(), "Sign out button should be visible");
        signOutPage.clickButtonSignOut();
    }
}
