package ru.mborisov.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebDriverProvider {
    public WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        // Turn off logging
        System.setProperty("webdriver.chrome.silentOutput", "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        // Turn on headless mode
//        options.setHeadless(true);
        // Turn off image loading
        Map<String, Integer> imagePreferences = new HashMap<>();
        imagePreferences.put("images", 2);
        Map<String, Map<String, Integer>> chromePreferences = new HashMap<>();
        chromePreferences.put("profile.default_content_settings", imagePreferences);
        chromePreferences.put("profile.managed_default_content_settings", imagePreferences);
        options.setExperimentalOption("prefs", chromePreferences);
        return new ChromeDriver(options);
    }

    public WebDriver getFirefoxDriver() {
        return new FirefoxDriver();
    }
}
