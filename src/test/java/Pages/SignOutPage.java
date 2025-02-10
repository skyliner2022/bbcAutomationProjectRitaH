package Pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignOutPage extends BasePage {

    @Getter
    @FindBy(xpath = "//a[contains(@href, 'signout')]")
    WebElement buttonSignOut;

    public void clickButtonSignOut() {
        buttonSignOut.click();
    }
}
