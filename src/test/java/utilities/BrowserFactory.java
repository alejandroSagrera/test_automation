package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

    public static WebDriver driver;
    public final int seconds = 30;
    public String base_url;

    public static WebDriver starBrowser(String name, String base_url) {
        if (name.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    "Drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (name.equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    "Drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (name.equals("Edge")) {
            System.setProperty("webdriver.edge.driver",
                    "Drivers\\MicrosoftWebDriver.exe");
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(base_url);
        return driver;
    }
}