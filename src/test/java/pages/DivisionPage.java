package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DivisionPage extends BasePage {

    public DivisionPage(WebDriver driver) {
        super(driver);
    }

    private final By employeeMenu =
            By.xpath("//p[normalize-space()='Employee']");
    private final By divisionTab =
            By.xpath("//button[normalize-space()='Division']");
    private final By addDivisionBtn =
            By.id("add-division-button");
    private final By divisionNameField =
            By.id("name");
    private final By divisionDescField =
            By.id("description");
    private final By submitBtn =
            By.id("add-division-confirm-button");
    private final By successToast =
            By.xpath("//*[contains(text(),'Success') or contains(text(),'created')]");
    private final By errorToast =
            By.xpath("//*[contains(text(),'Error') or contains(text(),'duplicate') or contains(text(),'failed')]");

    public void goToDivisionPage() {
        wait.until(ExpectedConditions.elementToBeClickable(employeeMenu))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable(divisionTab))
                .click();
    }

    public void addNewDivision(String name, String description) {
        goToDivisionPage();
        wait.until(ExpectedConditions.elementToBeClickable(addDivisionBtn))
                .click();
        WebElement nameField =
                wait.until(ExpectedConditions.visibilityOfElementLocated(divisionNameField));
        nameField.clear();
        nameField.sendKeys(name);
        WebElement descField =
                wait.until(ExpectedConditions.visibilityOfElementLocated(divisionDescField));
        descField.clear();
        descField.sendKeys(description);
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn))
                .click();
    }

    public boolean isSuccessToastDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(successToast)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorToastDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(errorToast)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
