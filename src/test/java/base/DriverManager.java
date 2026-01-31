package base;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    public static WebDriver createDriver() {

        ChromeOptions options = new ChromeOptions();

        // WAJIB untuk GitHub Actions / Linux
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        // Headless mode (CI)
        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
        }

        // Auto download driver
        WebDriverManager.chromedriver().setup();

        return new ChromeDriver(options);
    }
}
