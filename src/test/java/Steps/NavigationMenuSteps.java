package Steps;

import Pages.NavigationMenuPage;
import Utilities.ValidationHelper;

public class NavigationMenuSteps {

    NavigationMenuPage navigationMenuPage = new NavigationMenuPage();

    public void openMenuAndCheckDisplay() {
        navigationMenuPage.clickButtonOpenMenu();
        ValidationHelper.isElementDisplayed(navigationMenuPage.getLeftHandMenu());
    }
}
