package ru.mborisov;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.CommandLine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.mborisov.cli.InstacommentsCommandLineParser;
import ru.mborisov.driver.WebDriverProvider;
import ru.mborisov.entity.Comment;
import ru.mborisov.parser.InstagramCommentsParser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class App {
    private static WebElement findElementBy(WebDriver driver, By by, long timeoutInSeconds) {
        return new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void main(String[] args) {
        InstacommentsCommandLineParser cliParser = new InstacommentsCommandLineParser();
        CommandLine cmd = cliParser.parse(args);
        String url = cmd.getOptionValue("u");
        String filename = cmd.getOptionValue("o");

        WebDriverProvider provider = new WebDriverProvider();
        WebDriver driver = provider.getChromeDriver();
        driver.get(url);
        while (true) {
            try {
                WebElement loadMoreButton = findElementBy(driver,
                                                          By.xpath("//main//article//li/button[@type=\"button\"]"),
                                                          5);
                loadMoreButton.click();
            } catch (Exception ignored) {
                break;
            }
        }
        String html = driver.getPageSource();
        InstagramCommentsParser parser = new InstagramCommentsParser();
        List<Comment> comments = parser.parse(html);
        System.out.println(comments.size() + "comments have been saved to " + filename + ".json");
        try (FileWriter writer = new FileWriter(filename + ".json")) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, comments);
        } catch (IOException e) {
            System.out.println("Could not open a file for writing");
        }
        driver.quit();
    }
}
