package Pages;

import Tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage() {
        driver = BaseTest.getDriver();
        PageFactory.initElements(driver, this);
    }
}

