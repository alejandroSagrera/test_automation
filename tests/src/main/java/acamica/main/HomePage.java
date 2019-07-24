package acamica.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	@FindBy(how = How.XPATH, using = "//form[@id=\"gcw-flights-form-hp-flight\"]//*[@type=\"submit\"]//*[contains(text(),'Search')]")
	private WebElement btnSearch;

	@FindBy(how = How.XPATH, using = "//*[@id=\"tab-flight-tab-hp\"]/span[contains(text(),'Flights')]")
	private WebElement flightType;

	@FindBy(how = How.ID, using = "flight-type-roundtrip-label-hp-flight")
	private WebElement roundtripLink;

	@FindBy(how = How.ID, using = "flight-origin-hp-flight")
	private WebElement inputOrigin;

	@FindBy(how = How.ID, using = "flight-destination-hp-flight")
	private WebElement inputDestination;

	@FindBy(how = How.XPATH, using = "//*[@type=\"button\"]/span[contains(text(),\"Add to Cart\")][1]")
	private WebElement inputAddToCart;

	public void searchProduct(String from, String to, int adult, String startDate, String finishDate) {
		try {
			clickFlightType();
			clickRoundtrip();
			BasePage.implicitWaitId("flight-origin-hp-flight");
			inputOrigin.sendKeys("LAS");
			BasePage.implicitWaitId("flight-destination-hp-flight");
			inputDestination.sendKeys("LAX");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void clickFlightType() {
		/* perform click at the flight type */
		try {
			BasePage.implicitWaitXpath("//*[@id=\\\"tab-flight-tab-hp\\\"]/span[contains(text(),'Flights')]");
			flightType.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void clickRoundtrip() {
		/* perform click at the roundtrip section */
		try {
			BasePage.implicitWaitId("flight-type-roundtrip-label-hp-flight");
			flightType.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public SearchResultPage clickSearchButton() {
		try {
			BasePage.implicitWaitXpath("//*[@id=\"search\"]//button");
			btnSearch.click();
			return PageFactory.initElements(driver, SearchResultPage.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public By getPageLoadLocator() {
		return By.xpath(
				"/html/head/title[contains(text(),'Wander Wisely with Cheap Hotels, Flights, Vacations & Travel Deals | Travelocity')]");
	}

	public HomePage(WebDriver driver) {
		super(driver);
		if (!this.isLoaded()) {
			throw new IllegalStateException("This is not the Home Page");
		}
	}

}
