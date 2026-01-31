package utils;

import base.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        captureScreenshot(result, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        captureScreenshot(result, "FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        captureScreenshot(result, "SKIP");
    }

    private void captureScreenshot(ITestResult result, String status) {
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            WebDriver driver =
                    ((BaseTest) testClass).getDriver();
            if (driver != null) {
                String testName = result.getName();
                takeScreenshot(
                        driver,
                        status,
                        testName
                );
            }
        }
    }

    @Attachment(
            value = "[{status}] {testName}",
            type = "image/png"
    )
    public byte[] takeScreenshot(
            WebDriver driver,
            String status,
            String testName
    ) {
        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }
}
