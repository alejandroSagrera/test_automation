package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlightCheckoutPage extends BasePage {

    public FlightCheckoutPage(WebDriver driver) {
        super(driver);
        if (!this.isLoaded()) {
            throw new IllegalStateException("This is not the Trip Detail page");
        }
    }
    @Override
    public By getPageLoadLocator() {
        return By.xpath("//*[@id=\"fisHeader\"]/h1[contains(text(),'trip')]");
    }
}
