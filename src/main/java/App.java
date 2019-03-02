import driver.WebDriverProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;

public class App {
    public static void main(String[] args) {
        WebDriverProvider provider = new WebDriverProvider();
        WebDriver driver = provider.getChromeDriver();
        String url = "https://www.instagram.com/p/BuefjW3l6RM/";
        driver.get(url);
        String html = driver.getPageSource();
        Document jsoup = Jsoup.parse(html);
        System.out.println(jsoup);
        driver.quit();
    }
}
