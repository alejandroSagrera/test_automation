package acamica.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import acamica.util.CaptureScreenShot;

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

	public boolean isPageOpen() { //Exercise 1 step 7 and 8
		try {
			CaptureScreenShot.takeAScreenShot(driver);
			return verifyMainSection() && verifyPaymentInfo() && verifyConfirmationInfo() && verifyTripDetails()
					&& verifyCompleteBookingButton();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verifyMainSection() {
		try {
			BasePage.implicitWaitVel("xpath", "//*[@class=\"faceoff-module-title\"][contains(text(),'traveling')]",
					null);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verifyPaymentInfo() {
		try {
			BasePage.implicitWaitVel("xpath", "//*[@id=\"payments\"]", null);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verifyConfirmationInfo() {
		try {
			BasePage.implicitWaitVel("xpath", "//*[@class=\"faceoff-module-title\"][contains(text(),'send')]", null);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verifyTripDetails() {
		try {
			BasePage.implicitWaitVel("xpath", "//*[@id=\"complete\"]/h2[contains(text(),'your trip')]", null);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verifyCompleteBookingButton() {
		try {
			BasePage.implicitWaitVel("id", "complete-booking", null);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
