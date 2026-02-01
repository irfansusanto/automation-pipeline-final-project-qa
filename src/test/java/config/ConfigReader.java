package config;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigReader {

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    public static String getValue(String key) {

        String envValue = System.getenv(key);
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }

        if (dotenv != null) {
            return dotenv.get(key);
        }

        return null;
    }

    public static String getBaseUrl() {
        return getValue("BASE_URL");
    }

    public static String getEmail() {
        return getValue("LOGIN_EMAIL");
    }

    public static String getPassword() {
        return getValue("LOGIN_PASSWORD");
    }

    public static String getBrowser() {
        String browser = getValue("BROWSER");
        if (browser == null || browser.isEmpty()) {
            return "chrome";
        }
        return browser;
    }


    public static boolean isHeadless() {
        String val = getValue("HEADLESS");
        String ci = System.getenv("CI");
        if ("true".equalsIgnoreCase(ci)) {
            return true;
        }
        return val != null && val.equalsIgnoreCase("true");
    }

}
