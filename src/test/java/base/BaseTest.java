package base;

import config.ConfigReader;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverManager.createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(3));
        driver.get(ConfigReader.getBaseUrl());
        doLogin();
    }

    private void doLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.getEmail(),
                ConfigReader.getPassword()
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver != null) {
            captureScreenshot(result);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    private void captureScreenshot(ITestResult result) {
        String status;
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                status = "PASS";
                break;
            case ITestResult.FAILURE:
                status = "FAIL";
                break;
            case ITestResult.SKIP:
                status = "SKIP";
                break;
            default:
                status = "UNKNOWN";
        }

        attachScreenshot(
                result.getMethod().getMethodName(),
                status
        );
    }

    @Attachment(
            value = "Screenshot - {testName} [{status}]",
            type = "image/png"
    )
    public byte[] attachScreenshot(String testName, String status) {

        if (driver == null) {
            return new byte[0];
        }
        try {
            return ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return new byte[0];
        }
    }


    public WebDriver getDriver() {
        return driver;
    }
}
