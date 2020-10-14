package acamica.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import acamica.util.CaptureScreenShot;

public class TripDetailPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//*[@id=\"bookButton\"]")
    private WebElement bookButton;

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

    public FlightCheckoutPage verifyTripDetails() { //Exercise 1 step 6 an 7
        try {
            verifyTotalPrice();
            verifyDepartureInfo();
            verifyReturnInfo();
            if(bookButton.isDisplayed()){
            	CaptureScreenShot.takeAScreenShot(driver);
                bookButton.click();
                return PageFactory.initElements(driver, FlightCheckoutPage.class);
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public void verifyTotalPrice(){
        try{
            BasePage.implicitWaitVel("xpath", "//*[@class=\"tripTotals\"]/*[contains(text(),'$')]",null);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void verifyDepartureInfo(){
        try{
            BasePage.implicitWaitVel("xpath", "//*[@class=\"flex-card flex-tile details OD0\"]",null);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void verifyReturnInfo(){
        try{
            BasePage.implicitWaitVel("xpath", "//*[@class=\"flex-card flex-tile details OD1\"]",null);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}