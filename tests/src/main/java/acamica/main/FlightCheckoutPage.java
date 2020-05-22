package acamica.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlightCheckoutPage extends BasePage {

    public FlightCheckoutPage(WebDriver driver) {
        super(driver);
        if (!this.isLoaded()) {
            throw new IllegalStateException("This is not the Secure booking page");
        }
    }
    @Override
    public By getPageLoadLocator() {
        return By.xpath("//*[@class=\"faceoff-module-title\"][contains(text(),'traveling')]");
    }
    public boolean isPageOpen(){
        try{
            return verifyMainSection() && verifyPaymentInfo() && verifyConfirmationInfo() &&
                    verifyTripDetails() && verifyCompleteBookingButton();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean verifyMainSection(){
        try{
            BasePage.implicitWaitVel("xpath", "//*[@class=\"faceoff-module-title\"][contains(text(),'traveling')]");
            return  true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean verifyPaymentInfo(){
        try{
            BasePage.implicitWaitVel("xpath", "//*[@id=\"payments\"]");
            return  true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean verifyConfirmationInfo(){
        try{
            BasePage.implicitWaitVel("xpath", "//*[@class=\"faceoff-module-title\"][contains(text(),'send')]");
            return  true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean verifyTripDetails(){
        try{
            BasePage.implicitWaitVel("xpath", "//*[@id=\"complete\"]/h2[contains(text(),'your trip')]");
            return  true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean verifyCompleteBookingButton(){
        try{
            BasePage.implicitWaitVel("id", "complete-booking");
            return  true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
