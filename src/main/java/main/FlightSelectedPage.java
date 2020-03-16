package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlightSelectedPage extends BasePage {

    public FlightSelectedPage(WebDriver driver) {
        super(driver);
        if (!this.isLoaded()) {
            throw new IllegalStateException("This is not the flight selected page");
        }
    }

    @Override
    public By getPageLoadLocator() {
        return null;
    }

}