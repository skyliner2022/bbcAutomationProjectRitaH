package Steps;

import Pages.SignInPage;
import Pages.SignOutPage;
import Utilities.ValidationHelper;

public class SignOutSteps {

    static SignInSuccessSteps signInSuccessSteps = new SignInSuccessSteps();
    static SignInPage signInPage = new SignInPage();
    SignOutPage signOutPage = new SignOutPage();

    public void verifyUserIsLoggedIn(String email, String password) {
        signInSuccessSteps.signInSuccessSignIn(email, password);
    }

    public void accountMenuIsClicked() {
        ValidationHelper.clickElement(signInPage.getUserAccountButton());
        ValidationHelper.assertElementDisplayed(signOutPage.getButtonSignOut(), "Sign out button should be visible");
    }

    public void userIsSignedOut() {
        ValidationHelper.clickElement(signInPage.getUserAccountButton());
        ValidationHelper.assertElementDisplayed(signOutPage.getButtonSignOut(), "Sign out button should be visible");
        signOutPage.clickButtonSignOut();
    }
}
