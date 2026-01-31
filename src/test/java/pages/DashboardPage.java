package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    private final By dashboardTitle =
            By.xpath("//p[text()='Dashboard']");

    public boolean isOnDashboard() {
        try {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(dashboardTitle)
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
