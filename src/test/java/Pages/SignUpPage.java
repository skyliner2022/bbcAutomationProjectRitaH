package Pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage extends BasePage {

    @FindBy(css = "button[aria-label='Register']")
    WebElement buttonOpenSignUp;

    @FindBy(id = "username")
    WebElement emailSignUpField;

    @FindBy(id = "password")
    WebElement passwordSignUpField;

    // Note: The same locator is used for both 'Continue' and 'Register' buttons
    @FindBy(id = "submit-button")
    WebElement buttonSignUpSubmit;

    @FindBy(xpath = "//a[contains(@class, 'sb-link') and contains(@class, 'sb-link--secondary')]")
    WebElement linkToSignIn;

    @Getter
    @FindBy(id = "form-message-username")
    WebElement errorContainerNewEmail;

    @Getter
    @FindBy(id = "form-message-email-first-email-form")
    WebElement errorContainerEmailAlreadyExists;

    @Getter
    @FindBy(xpath = "//p[@class='sb-form-message__content__text']//span/span")
    WebElement errorMessage;

    @Getter
    @FindBy(xpath = "//p[@class='sb-form-message__content__text']//span/span[3]")
    WebElement errorMessageSecondPart;

    @Getter
    @FindBy(id = "form-message-password")
    WebElement errorContainerNewPassword;

    @Getter
    @FindBy(xpath = "//li[@aria-label='At least eight characters.']")
    WebElement lengthRequirement;

    @Getter
    @FindBy(xpath = "//li[@aria-label='At least one letter.']")
    WebElement letterRequirement;

    @Getter
    @FindBy(xpath = "//li[@aria-label='At least one number or symbol.']")
    WebElement symbolNumberRequirement;

    @Getter
    @FindBy(css = ".sc-fQpRED.jLxIKE.checklist__item")
    WebElement uncheckedClassValue;

    @Getter
    @FindBy(css = ".sc-fQpRED.jLxIKE.checklist__item.checklist-item--checked")
    WebElement checkedClassValue;

    @Getter
    @FindBy(xpath = "//h1//span")
    WebElement registerForAccountAndPasswordTitle;

    @Getter
    @FindBy(xpath = "//*[@id='form-message-password']/p/span/span")
    WebElement passwordCompromisedErrorMessage;

    @FindBy(css = "button[type='button']")
    WebElement buttonShowPassword;

    @Getter
    @FindBy(xpath = "//h1/span[text()='Enter Your Details']")
    WebElement detailsBirthTitle;

    @FindBy(xpath = "//*[@aria-describedby='form-message-dateOfBirth']")
    WebElement detailsDayBirthInput;

    @FindBy(id = "dateOfBirthMonth")
    WebElement detailsMonthBirthInput;

    @FindBy(id = "dateOfBirthYear")
    WebElement detailsYearBirthInput;

    @FindBy(xpath = "(//div[contains(@class, 'button--radio')])[2]")
    WebElement noThanksOption;

    @FindBy(xpath = "//*[@class='page__close']")
    WebElement closeButton;

    public void clickButtonShowPassword() {
        buttonShowPassword.click();
    }

    public void clickButtonOpenSignUp() {
        buttonOpenSignUp.click();
    }

    public void inputEmailSignUpField(String email) {
        emailSignUpField.sendKeys(email);
    }

    public void inputPasswordSignUpField(String password) {
        passwordSignUpField.sendKeys(password);
    }

    public void clickButtonSignUpSubmit() {
        buttonSignUpSubmit.click();
    }

    public void clickLinkToSignIn() {
        linkToSignIn.click();
    }

    public void inputDetailsDayBirthInput(String day) {
        detailsDayBirthInput.sendKeys(day);
    }

    public void inputDetailsMonthBirthInput(String month) {
        detailsMonthBirthInput.sendKeys(month);
    }

    public void inputDetailsYearBirthInput(String year) {
        detailsYearBirthInput.sendKeys(year);
    }

    public void clickCloseButton() {
        closeButton.click();
    }

    public void clickNoThanksOption() {
        noThanksOption.click();
    }

    public void waitForSubmitButtonToAppear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(buttonSignUpSubmit));
    }
}
