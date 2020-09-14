package acamica.main;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import acamica.util.CaptureScreenShot;
import acamica.util.DatePickerHandle;

public class HomePage extends BasePage {

	@FindBy(how = How.XPATH, using = "//form[@id=\"gcw-flights-form-hp-flight\"]//*[@type=\"submit\"]")
	private WebElement btnSearch;

	@FindBy(how = How.ID, using = "tab-flight-tab-hp")
	private WebElement flightType;

	@FindBy(how = How.ID, using = "flight-type-roundtrip-label-hp-flight")
	private WebElement roundtripLink;

	@FindBy(how = How.ID, using = "tab-package-tab-hp")
	private WebElement flightVacationPackage;

	@FindBy(how = How.ID, using = "fh-fh-hp-package")
	private WebElement flightAndHotel;

	@FindBy(how = How.ID, using = "flight-origin-hp-flight")
	private WebElement inputOrigin;

	@FindBy(how = How.ID, using = "package-origin-hp-package")
	private WebElement inputOriginPackage;

	@FindBy(how = How.ID, using = "flight-destination-hp-flight")
	private WebElement inputDestination;

	@FindBy(how = How.ID, using = "package-destination-hp-package")
	private WebElement inputDestinationPackage;

	@FindBy(how = How.ID, using = "flight-departing-hp-flight")
	private WebElement inputDeparting;

	@FindBy(how = How.ID, using = "package-departing-hp-package")
	private WebElement inputDepartingPackage;

	@FindBy(how = How.ID, using = "flight-returning-hp-flight")
	private WebElement inputReturning;

	@FindBy(how = How.ID, using = "flight-adults-hp-flight")
	private WebElement inputAdultQty;

	@FindBy(how = How.XPATH, using = "//*[@type=\"button\"]/span[contains(text(),\"Add to Cart\")][1]")
	private WebElement inputAddToCart;

	public SearchResultPage searchFlight(String from, String to, String qty, String startDate, String finishDate) {
		try {
			clickFlightType(); // click the flight type
			clickRoundtrip(); // click the roundtrip
			addOrigin(1, from); // sending origin
			addDestination(1, to); // sending destination
			addDeparting(1, startDate);// sending departing
			addReturning(1, finishDate);// sending returning
			addAdultQty(qty); // sending adult qty
			CaptureScreenShot.takeAScreenShot(driver);
			searchClic();// performing the search
			return PageFactory.initElements(driver, SearchResultPage.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public SearchFlightHotelResultPage searchFlightAndHotel(String from, String to, String qty, String startDate,
			String finishDate) {
		try {

			clickVacationPackages(); // click Flight and Hotel
			addOrigin(2, from); // sending origin
			addDestination(2, to); // sending destination
			addDeparting(2, startDate);// sending departing
			addReturning(2, finishDate);// sending returning
			addAdultQty(qty); // sending adult qty
			searchClic();// performing the search
			return PageFactory.initElements(driver, SearchFlightHotelResultPage.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void searchClic() {
		try {
			BasePage.implicitWaitVel("element", "", btnSearch);
			btnSearch.click(); // performing the search

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void addOrigin(int testNumber, String origin) {
		try {
			if (testNumber == 1) {
				BasePage.implicitWaitVel("id", "", inputOrigin);
				inputOrigin.clear();
				inputOrigin.sendKeys(origin); // sending origin
				BasePage.implicitWaitVel("xpath",
						"//*[@id=\"autocomplete-dropdown-flight-origin-hp-flight\"]/div/li/a/div/strong[contains(text(),'"
								+ origin + "')]",
						null);
				driver.findElement(By.xpath(
						"//*[@id=\"autocomplete-dropdown-flight-origin-hp-flight\"]/div/li/a/div/strong[contains(text(),'"
								+ origin + "')]"))
						.click();
			} else if (testNumber == 2) {
				BasePage.implicitWaitVel("element", "", inputOriginPackage);
				inputOriginPackage.clear();
				inputOriginPackage.sendKeys(origin); // sending origin
				BasePage.implicitWaitVel("xpath",
						"//*[@id=\"autocomplete-dropdown-package-origin-hp-package\"]/div/li/a/div/strong[contains(text(),'"
								+ origin + "')]",
						null);
				driver.findElement(By.xpath(
						"//*[@id=\"autocomplete-dropdown-package-origin-hp-package\"]/div/li/a/div/strong[contains(text(),'"
								+ origin + "')]"))
						.click();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void addDestination(int testNumber, String destination) {
		try {
			if (testNumber == 1) {
				BasePage.implicitWaitVel("element", "", inputDestination);
				inputDestination.clear();
				inputDestination.sendKeys(destination); // sending destination
				BasePage.implicitWaitVel("xpath",
						"//*[@id=\"autocomplete-dropdown-flight-destination-hp-flight\"]/div/li/a/div/strong[contains(text(),'"
								+ destination + "')]",
						null);
				driver.findElement(By.xpath(
						"//*[@id=\"autocomplete-dropdown-flight-destination-hp-flight\"]/div/li/a/div/strong[contains(text(),'"
								+ destination + "')]"))
						.click();
			} else if (testNumber == 2) {
				BasePage.implicitWaitVel("element", "", inputDestinationPackage);
				inputDestinationPackage.clear();
				inputDestinationPackage.sendKeys(destination); // sending origin
				BasePage.implicitWaitVel("xpath",
						"//*[@id=\"autocomplete-dropdown-package-destination-hp-package\"]/div/li/a/div/strong[contains(text(),'"
								+ destination + "')]",
						null);
				driver.findElements(By.xpath(
						"//*[@id=\"autocomplete-dropdown-package-destination-hp-package\"]/div/li/a/div/strong[contains(text(),'"
								+ destination + "')]"))
						.get(0).click();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void addDeparting(int testNumber, String startDate) {
		try {
			if (testNumber == 1) {
				BasePage.implicitWaitVel("element", "", inputDeparting);
				inputDeparting.clear();
				DatePickerHandle.SelectDepartingDateFromMultiDateCalendar(1, startDate);
			} else if (testNumber == 2) {
				BasePage.implicitWaitVel("element", "", inputDepartingPackage);
				inputDepartingPackage.clear();
				DatePickerHandle.SelectDepartingDateFromMultiDateCalendar(2, startDate);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void addReturning(int testNumber, String finishDate) {
		try {
			BasePage.implicitWaitVel("element", "", inputReturning);
			inputReturning.clear();
			DatePickerHandle.SelectReturningDateFromMultiDateCalendar(testNumber, finishDate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void addAdultQty(String qty) {
		try {
			BasePage.implicitWaitVel("element", "", inputAdultQty);
			inputAdultQty.sendKeys(qty); // sending adults qty
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void clickFlightType() {
		try {
			BasePage.implicitWaitVel("element", "", flightType);
			flightType.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void clickRoundtrip() {
		try {
			BasePage.implicitWaitVel("element", "", roundtripLink);
			roundtripLink.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void clickVacationPackages() {
		try {
			BasePage.implicitWaitVel("element", "", flightVacationPackage);
			flightVacationPackage.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void clickFlightAndHotel() {
		try {
			BasePage.implicitWaitVel("element", "", flightAndHotel);
			flightAndHotel.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
