package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TripDetailPage extends BasePage {


    @FindBy(how = How.XPATH, using = "//*[@id=\"fisHeader\"]/h1[contains(text(),'trip')]")
    private WebElement headerTripDetail;

    @FindBy(how = How.XPATH, using = "//*[@class=\"totalContainer\"]//*[@class=\"trip-total\"]")
    private WebElement totalTrip;

    public TripDetailPage(WebDriver driver) {
        super(driver);
        if (!this.isLoaded()) {
            throw new IllegalStateException("This is not the Trip Detail page");
        }
    }
    @Override
    public By getPageLoadLocator() {
        return By.xpath("//*[@id=\"fisHeader\"]/h1[contains(text(),'trip')]");
    }
    public boolean verifyTripDetails() {
        try {
            boolean verified=false;
            return verified;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}