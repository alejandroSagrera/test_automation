import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import main.*;
import util.DatePickerHandle;
import utilities.BrowserFactory;

import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver;
    protected String base_url = "https://www.travelocity.com";
    protected HomePage homePage;
    protected SearchResultPage resultPage;
    protected DatePickerHandle datePicker;
    protected TripDetailPage tripDetailPage;
    protected FlightCheckoutPage flightCheckout;


    @BeforeMethod
    public void setUp() {
        driver=BrowserFactory.starBrowser("Chrome", base_url);
        homePage=new HomePage(driver);
        datePicker = new DatePickerHandle(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}