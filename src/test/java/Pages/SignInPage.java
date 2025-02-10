package Pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage extends BasePage {

    @FindBy(css = "button[aria-label='Sign In']")
    WebElement buttonOpenSignIn;

    @FindBy(xpath = "//*[contains(@class, 'NavigationLink-AccountLink')]")
    WebElement buttonOpenSignInSport;

    @FindBy(id = "user-identifier-input")
    WebElement emailRegField;

    @FindBy(id = "password-input")
    WebElement passwordRegField;

    // The same locator is used for both 'Continue' and 'Sign In' buttons
    @FindBy(id = "submit-button")
    WebElement buttonSignInSubmit;

    @Getter
    @FindBy(xpath = "//button[contains(@class, 'sc-5b3467da-7') and contains(@class, 'cWmBDI')]")
    WebElement userAccountButton;

    @Getter
    @FindBy(xpath = "//span[contains(@class, 'e1jj8uav2') and text()='Your account']")
    WebElement userAccountButtonSport;

    @Getter
    @FindBy(id = "form-message-general")
    WebElement errorContainerGeneral;

    @Getter
    @FindBy(id = "form-message-email")
    WebElement errorContainerEmail;

    @Getter
    @FindBy(xpath = "//p[@class='sb-form-message__content__text']//span/span")
    WebElement errorMessage;

    @Getter
    @FindBy(id = "form-message-password")
    WebElement errorContainerPassword;

    @Getter
    @FindBy(xpath = "//h1//span[2]/span")
    WebElement enterYourEmailTitle;

    @Getter
    @FindBy(xpath = "//main/h1/span")
    WebElement enterYourPasswordTitle;

    @Getter
    @FindBy(xpath = "//h1/span[contains(text(), 'your account is locked')]")
    WebElement lockedAccountTitle;

    @Getter
    @FindBy(xpath = "//*[contains(@href, 'magicLink')]")
    WebElement magicLinkRecoverAccount;

    @FindBy(xpath = "//a[contains(@data-bbc-result, '/register')]")
    WebElement linkToSignUp;

    @FindBy(xpath = "//a[contains(@data-bbc-result, '/signin/forgotten/credentials')]")
    WebElement linkForgottenEmail;

    @FindBy(xpath = "//a[contains(@data-bbc-result, '/session/signout')]")
    WebElement buttonRegisterNewAccount;

    @FindBy(xpath = "//*[@class='page__close']")
    WebElement closeButtonSignIn;

    public void clickButtonOpenSignIn() {
        buttonOpenSignIn.click();
    }

    public void clickButtonOpenSignInSport() {
        buttonOpenSignInSport.click();
    }

    public void inputEmailRegField(String email) {
        emailRegField.sendKeys(email);
    }

    public void inputPasswordRegField(String password) {
        passwordRegField.sendKeys(password);
    }

    public void clickButtonSignInSubmit() {
        buttonSignInSubmit.click();
    }

    public void clickLinkToSignUp() {
        linkToSignUp.click();
    }

    public void clickLinkForgottenEmail() {
        linkForgottenEmail.click();
    }

    public void clickButtonRegisterNewAccount() {
        buttonRegisterNewAccount.click();
    }

    public void clickCloseButtonSignIn() {
        closeButtonSignIn.click();
    }

    public void waitForAccountButtonToAppear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(userAccountButton));
    }

    public void waitForButtonRegisterNewAccountToAppear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(buttonRegisterNewAccount));
    }
}
