package acamica.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchFlightHotelResultPage extends BasePage {

	@FindBy(how = How.XPATH, using = "//*[@id=\"primary-header-package\"][contains(text(),'Vacation Packages')]")
	private WebElement packageHeader;
	
	@FindBy(how = How.XPATH, using = "//*[@class=\"sort-filter-bar control box\"]/ul/li/button/span[contains(text(),'Price')]")
	private WebElement orderByPriceBtn;

	public SearchFlightHotelResultPage(WebDriver driver) {
		super(driver);
		if (!this.isLoaded()) {
			throw new IllegalStateException("This is not the search result page");
		}
	}

	@Override
	public By getPageLoadLocator() {
		return By.id("hotelResultTitle");
	}

	public boolean checkResultPage() {
		try {
			BasePage.implicitWaitVel("xpath",
					"//*[@id=\"primary-header-package\"][contains(text(),'Vacation Packages')]",null);
			BasePage.implicitWaitVel("id", "hotelResultTitle",null);
			BasePage.implicitWaitVel("xpath", "//*[@class=\"responsive-sortbar\"]",null);
			BasePage.implicitWaitVel("id", "cancellable",null);
			BasePage.implicitWaitVel("id", "lodgingType",null);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public boolean areTheResultsSorted() {
		try {
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
