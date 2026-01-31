package base;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    public static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }
}
