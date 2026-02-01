package utils;

import base.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureScreenshotListener implements ITestListener {

    @Attachment(value = "Screenshot - {0}", type = "image/png")
    public byte[] attachScreenshot(String testName, WebDriver driver) {

        if (driver == null) {
            return null;
        }

        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }

    private void capture(ITestResult result) {

        Object testClass = result.getInstance();

        if (testClass instanceof BaseTest) {

            WebDriver driver =
                    ((BaseTest) testClass).getDriver();

            attachScreenshot(result.getName(), driver);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        capture(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        capture(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        capture(result);
    }
}
