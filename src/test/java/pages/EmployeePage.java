package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class EmployeePage extends BasePage {

    private WebDriverWait wait;

    public EmployeePage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private By employeeMenu =
            By.xpath("//p[normalize-space()='Employee']");
    private By addEmployeeBtn =
            By.id("button-add-employee");
    private By nameField =
            By.id("name");
    private By employeeIdField =
            By.id("employeeId");
    private By emailField =
            By.id("email");
    private By phoneField =
            By.id("phoneNumber");
    private By divisionDropdown =
            By.id("division");
    private By divisionTechOption =
            By.xpath("//option[normalize-space()='Tech']");
    private By roleField =
            By.id("employeeRole");
    private By submitBtn =
            By.id("button-add-employee-submit");
    private By successToast =
            By.xpath("//*[contains(text(),'Success')]");
    private By errorToast =
            By.xpath("//*[contains(text(),'Error') or contains(text(),'failed') or contains(text(),'invalid') or contains(text(),'duplicate')]");
    private By searchInput =
            By.xpath("//input[@placeholder='Search name, e-mail, phone...']");
    private By tableBody =
            By.xpath("//table//tbody");

    public void openEmployeeMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(employeeMenu))
                .click();
        waitUntilTableLoaded();
    }

    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeBtn))
                .click();
    }

    public void fillEmployeeForm(
            String name,
            String employeeId,
            String email,
            String phone,
            String role
    ) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField))
                .clear();
        driver.findElement(nameField)
                .sendKeys(name);
        driver.findElement(employeeIdField)
                .clear();
        driver.findElement(employeeIdField)
                .sendKeys(employeeId);
        driver.findElement(emailField)
                .clear();
        driver.findElement(emailField)
                .sendKeys(email);
        driver.findElement(phoneField)
                .clear();
        driver.findElement(phoneField)
                .sendKeys(phone);
        driver.findElement(divisionDropdown).click();
        wait.until(ExpectedConditions.elementToBeClickable(divisionTechOption))
                .click();
        driver.findElement(roleField)
                .clear();
        driver.findElement(roleField)
                .sendKeys(role);
    }

    public void submitEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn))
                .click();
    }

    public void addNewEmployee(
            String name,
            String employeeId,
            String email,
            String phone,
            String role
    ) {
        openEmployeeMenu();
        clickAddEmployee();
        fillEmployeeForm(
                name,
                employeeId,
                email,
                phone,
                role
        );
        submitEmployee();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
        waitUntilTableLoaded();
    }

    public void searchEmployee(String keyword){
        WebElement input =
                wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(keyword);
        input.sendKeys(Keys.ENTER);
        waitUntilTableLoaded();
    }

    public void verifyEmployeeExist(String keyword){
        WebElement table =
                wait.until(ExpectedConditions.visibilityOfElementLocated(tableBody));
        String tableText = table.getText();
        assertTrue(
                tableText.contains(keyword),
                "Employee NOT FOUND: " + keyword
        );
    }

    public void verifyEmployeeNotExist(String keyword){
        WebElement table =
                wait.until(ExpectedConditions.visibilityOfElementLocated(tableBody));
        String tableText = table.getText();
        assertTrue(
                !tableText.contains(keyword),
                "Employee SHOULD NOT EXIST: " + keyword
        );
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

    public boolean isErrorEmployeeToastDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(errorToast)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void waitUntilTableLoaded(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
