package config;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigReader {

    private static Dotenv dotenv = Dotenv.load();

    public static String getBaseUrl() {
        return dotenv.get("BASE_URL");
    }

    public static String getEmail() {
        return dotenv.get("LOGIN_EMAIL");
    }

    public static String getPassword() {
        return dotenv.get("LOGIN_PASSWORD");
    }

    public static String getBrowser() {
        return dotenv.get("BROWSER");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(dotenv.get("HEADLESS"));
    }
}
