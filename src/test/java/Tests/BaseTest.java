package Tests;

import Utilities.*;
import Steps.*;
import Pages.*;

import Utilities.SearchWordsHelper;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public abstract class BaseTest {

    @Getter
    static WebDriver driver;

    static Properties properties;

    WebDriverWait wait;

    SignInPage signInPage;

    SignInSuccessSteps signInSuccessSteps;

    SignUpPage signUpPage;

    SignUpSuccessSteps signUpSuccessSteps;

    SignInFailureSteps signInFailureSteps;

    SignUpFailureSteps signUpFailureSteps;

    NavigationMenuPage navigationMenuPage;

    NavigationMenuSteps navigationMenuSteps;

    HeaderNavigationPage headerNavigationPage;

    ArticlePage articlePage;

    ArticleSteps articleSteps;

    SignOutPage signOutPage;

    SignOutSteps signOutSteps;

    @SneakyThrows
    @BeforeClass(alwaysRun = true)
    public void setUp() {
        properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader("config.properties"))) {
            properties.load(reader);
            File file = new File(properties.getProperty("path"));
            String driverName = properties.getProperty("driver", "").toLowerCase();
            String browserName = driverName.contains("chrome") ? "chrome" :
                    driverName.contains("firefox") ? "firefox" : "";

            if (browserName.isEmpty()) {
                throw new IllegalArgumentException("Unsupported driver: " + driverName);
            }

            System.setProperty(driverName, file.getAbsolutePath());

            switch (browserName) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported driver: " + browserName);
            }

            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        }

        signInPage = new SignInPage();
        signInSuccessSteps = new SignInSuccessSteps();
        signUpPage = new SignUpPage();
        signUpSuccessSteps = new SignUpSuccessSteps();
        signInFailureSteps = new SignInFailureSteps();
        signUpFailureSteps = new SignUpFailureSteps();
        navigationMenuPage = new NavigationMenuPage();
        navigationMenuSteps = new NavigationMenuSteps();
        headerNavigationPage = new HeaderNavigationPage();
        articlePage = new ArticlePage();
        articleSteps = new ArticleSteps();
        signOutPage = new SignOutPage();
        signOutSteps = new SignOutSteps();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        clearBrowserData();
        if (driver != null) {
            driver.quit();
        }
    }

    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    private static ITestContext testContext;

    @BeforeTest(alwaysRun = true)
    public void setTestContext(ITestContext context) {
        testContext = context;
        logger.info("====== Starting Test Execution ======");
    }

    @AfterTest(alwaysRun = true)
    public void logTestResults(ITestContext context) {
        IResultMap passedTests = testContext.getPassedTests();
        IResultMap failedTests = testContext.getFailedTests();
        IResultMap skippedTests = testContext.getSkippedTests();

        int totalPassed = passedTests.size();
        int totalFailed = failedTests.size();
        int totalSkipped = skippedTests.size();
        int totalTests = totalPassed + totalFailed + totalSkipped;

        logger.info("========== Test Suite Summary ==========");
        logger.info("Total tests run: {}", totalTests);
        logger.info("Passed tests: {}", totalPassed);
        logger.info("Failed tests: {}", totalFailed);
        logger.info("Skipped tests: {}", totalSkipped);
        logger.info("========================================");
    }

    //Global method to navigate to the target website under test
    public static void visit(String path) {
        driver.navigate().to(properties.getProperty("host") + path);
    }

    //Global method to wait for a page to load to fix issues with DOM elements not loading
    public static void waitForPageToLoadGlobal(WebDriver driver, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(webDriver -> Objects.equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState"), "complete"));
    }

    //Global method to remove adds div overlaying elements
    public void removeAds() {
        ((JavascriptExecutor) driver).executeScript("document.querySelectorAll(\"div[data-component='ad-slot']\").forEach(el => el.remove());");
    }

    //Global method to clear storage and cookies
    public void clearBrowserData() {
        driver.manage().deleteAllCookies(); // Clears cookies
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();"); // Clears localStorage
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();"); // Clears sessionStorage
    }

    @DataProvider(name = "signUpDataValid")
    public Object[][] signUpDataValid() {
        return new Object[][]{{RandomDataGenerator.randomEmail(), RandomDataGenerator.generateRandomPassword(8, true, true, true), "1", "12", "1988"}};
    }

    @DataProvider(name = "signUpPasswordEmpty")
    public Object[][] signUpPasswordEmpty() {
        return new Object[][]{{RandomDataGenerator.randomEmail(), ""}};
    }

    @DataProvider(name = "signUpSignInEmailEmpty")
    public Object[][] signUpEmailEmpty() {
        return new Object[][]{{""}};
    }

    @DataProvider(name = "signUpSignInInvalidEmail")
    public Object[][] signInvalidEmail() {
        return new Object[][]{{RandomDataGenerator.generateRandomEmailMissingDomain()}};
    }

    @DataProvider(name = "signUpInvalidPasswordLength")
    public Object[][] signUpInvalidPasswordLength() {
        return new Object[][]{{RandomDataGenerator.randomEmail(), "Zzz111@"}};
    }

    @DataProvider(name = "signUpCompromisedPassword")
    public Object[][] signUpCompromisedPassword() {
        return new Object[][]{{RandomDataGenerator.randomEmail(), CompromisedPasswordHelper.getRandomCompromisedPassword()}};
    }

    @DataProvider(name = "signInDefaultUser")
    public Object[][] signInDefaultUser() {
        return new Object[][]{{"test244@gmail.com", "Zzzzzz111111@"}};
    }

    @DataProvider(name = "signInRandomEmail")
    public Object[][] signInRandomEmail() {
        return new Object[][]{{RandomDataGenerator.randomEmail()}};
    }

    @DataProvider(name = "signInDefaultUserInvalidPassword")
    public Object[][] signInDefaultUserInvalidPassword() {
        return new Object[][]{{"test244@gmail.com", RandomDataGenerator.generateRandomPassword(8, true, true, true)}};
    }

    @DataProvider(name = "signInDefaultUserInvalidSixthPassword")
    public Object[][] signInDefaultUserInvalidSixthPassword() {
        return new Object[][]{{"forblocktest@gmail.com", RandomDataGenerator.generateRandomPassword(8, true, true, true)}};
    }

    @DataProvider(name = "signInDefaultUserEmptyPassword")
    public Object[][] signInDefaultUserEmptyPassword() {
        return new Object[][]{{"test244@gmail.com", ""}};
    }

    @DataProvider(name = "signUpDefaultUserEmail")
    public Object[][] signUpDefaultUserEmail() {
        return new Object[][]{{"test244@gmail.com"}};
    }

    @DataProvider(name = "signUpSevenLengthPassword")
    public Object[][] signUpSevenLengthPassword() {
        return new Object[][]{{RandomDataGenerator.randomEmail(), RandomDataGenerator.generateRandomPassword(7, true, false, false)}};
    }

    @DataProvider(name = "signUpNoLetterPassword")
    public Object[][] signUpNoLetterPassword() {
        return new Object[][]{{RandomDataGenerator.randomEmail(), RandomDataGenerator.generateRandomPassword(8, false, true, true)}};
    }

    @DataProvider(name = "signUpNoSpecialCharactersAndNumbersPassword")
    public Object[][] signUpNoSpecialCharactersAndNumbersPassword() {
        return new Object[][]{{RandomDataGenerator.randomEmail(), RandomDataGenerator.generateRandomPassword(8, true, false, false)}};
    }

    @DataProvider(name = "randomSearchWord")
    public Object[][] randomSearchWord() {
        return new Object[][]{{SearchWordsHelper.getRandomSearchWord()}};
    }
}