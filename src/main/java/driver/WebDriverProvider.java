package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class WebDriverProvider {
    public WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        // Turn on headless mode
        options.setHeadless(true);
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
