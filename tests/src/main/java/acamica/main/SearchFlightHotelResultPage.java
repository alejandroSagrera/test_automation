package acamica.main;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import acamica.util.CaptureScreenShot;
import entities.Hotel;

public class SearchFlightHotelResultPage extends BasePage {

	@FindBy(how = How.XPATH, using = "//*[@id=\"primary-header-package\"][contains(text(),'Vacation Packages')]")
	private WebElement packageHeader;

	@FindBy(how = How.XPATH, using = "//*[@id=\"sortContainer\"]/div/div/div[2]/div/fieldset/ul/li[3]/button")
	private WebElement orderByPriceBtn;

	@FindBy(how = How.XPATH, using = "//*[@class=\"price \"]/a")
	private List<WebElement> prices;

	@FindBy(how = How.XPATH, using = "//*[@class=\"flex-content info-and-price CITY \"]/div[2]/ul/li[4]/h4")
	private List<WebElement> hotelsToSelect;

	@FindBy(how = How.XPATH, using = "//*[@class=\"starRatingLabel\"]/strong/span[2][@title=\"3.0\"]")
	private WebElement hotelStarSelect;

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

	public boolean checkResultPage() { // Exercise 2 - step 3
		try {
			BasePage.implicitWaitVel("xpath", "//*[@id=\"primary-header-package\"][contains(text(),'Vacation')]", null);
			BasePage.implicitWaitVel("id", "hotelResultTitle", null);
			BasePage.implicitWaitVel("xpath", "//*[@class=\"responsive-sortbar \"]", null);
			BasePage.implicitWaitVel("id", "cancellable", null);
			BasePage.implicitWaitVel("id", "lodgingType", null);
			CaptureScreenShot.takeAScreenShot(driver);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean areTheResultsSorted() throws InterruptedException { // Exercise 2 step 4
		BasePage.implicitWaitVel("element", "", orderByPriceBtn);
		orderByPriceBtn.click();
		Thread.sleep(3000);
		if (prices.size() > 2) {
			CaptureScreenShot.takeAScreenShot(driver);
			return (Integer.parseInt(prices.get(0).getText().replaceAll("\\D+", "")) < Integer
					.parseInt(prices.get(1).getText().replaceAll("\\D+", ""))) ? true : false;
		} else {
			return false;
		}
	}

	public BookHotelPage selectingHotel() {
		try {
			BasePage.implicitWaitVel("element", "", hotelStarSelect);
			hotelStarSelect.click();
			String hotelName = hotelsToSelect.get(0).getText();
			Hotel selectedOne = new Hotel(hotelName, Integer.parseInt(prices.get(0).getText().replaceAll("\\D+", "")));
			BasePage.implicitWaitVel("element", "", hotelsToSelect.get(0));
			driver.findElements(By.xpath("//*[@class=\"hotel-price\"]/li[4]")).get(0).click();
			return PageFactory.initElements(driver, BookHotelPage.class); // need to add a click
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
