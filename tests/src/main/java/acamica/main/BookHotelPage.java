package acamica.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import entities.Hotel;

public class BookHotelPage extends BasePage {

	public BookHotelPage(WebDriver driver) {
		super(driver);
		if (!this.isLoaded()) {
			throw new IllegalStateException("This is not the Secure booking page");
		}
	}

	@Override
	public By getPageLoadLocator() {
		return By.xpath("//*[@class=\"msi-label-container\"]/span[contains(text(),\"Choose your hotel\")]");
	}
}
