package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private By emailField = By.id("input-username-or-email");
    private By passwordField = By.id("input-password");
    private By loginButton = By.id("button-sign-in");

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField))
                .clear();
        driver.findElement(emailField).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField))
                .clear();
        driver.findElement(passwordField).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
        wait.until(ExpectedConditions.urlContains("/dashboard"));
    }
}
