package acamica.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import acamica.util.CaptureScreenShot;
import acamica.util.DatePickerHandle;

public class HomePage extends BasePage {

	@FindBy(how = How.XPATH, using = "//*[@id=\"wizard-flight-pwa-1\"]/div[3]/div[2]/button")
	private WebElement btnSearch;

	@FindBy(how = How.ID, using = "search-button-hp-package")
	private WebElement btnSearchPackage;

	@FindBy(how = How.XPATH, using = "//*[@id=\"uitk-tabs-button-container\"]/li[2]/a/span[contains(text(),'Flights')]")
	private WebElement flightType;

	@FindBy(how = How.XPATH, using = "//*[@id=\"uitk-tabs-button-container\"]/div[1]/li/a/span[contains(text(),'Roundtrip')]")
	private WebElement roundtripLink;

	@FindBy(how = How.ID, using = "tab-package-tab-hp")
	private WebElement flightVacationPackage;

	@FindBy(how = How.ID, using = "fh-fh-hp-package")
	private WebElement flightAndHotel;

	@FindBy(how = How.XPATH, using = "//*[@id=\"location-field-leg1-origin-menu\"]/div[1]/button")
	private WebElement originBox;

	@FindBy(how = How.ID, using = "location-field-leg1-origin")
	private WebElement originInput;

	@FindBy(how = How.XPATH, using = "//*[@id=\"location-field-leg1-destination-menu\"]/div[1]/button")
	private WebElement destinationBox;

	@FindBy(how = How.ID, using = "location-field-leg1-destination")
	private WebElement destinationInput;

	@FindBy(how = How.ID, using = "package-origin-hp-package")
	private WebElement inputOriginPackage;

	@FindBy(how = How.ID, using = "flight-destination-hp-flight")
	private WebElement inputDestination;

	@FindBy(how = How.ID, using = "package-destination-hp-package")
	private WebElement inputDestinationPackage;

	@FindBy(how = How.ID, using = "d1-btn")
	private WebElement departingBox;

	@FindBy(how = How.ID, using = "flight-departing-hp-flight")
	private WebElement inputDeparting;

	@FindBy(how = How.ID, using = "package-departing-hp-package")
	private WebElement inputDepartingPackage;

	@FindBy(how = How.ID, using = "flight-returning-hp-flight")
	private WebElement inputReturning;

	@FindBy(how = How.ID, using = "package-returning-hp-package")
	private WebElement inputReturningPackage;

	@FindBy(how = How.XPATH, using = "//*[@id=\"adaptive-menu\"]/a")
	private WebElement travelerBox;

	@FindBy(how = How.XPATH, using = "//*[@id=\"adaptive-menu\"]/div/div/section/div[1]/div[1]/div/button[2]")
	private WebElement increaseAdultQty;

	@FindBy(how = How.XPATH, using = "//*[@id=\"adaptive-menu\"]/div/div/section/div[1]/div[1]/div/button[1]")
	private WebElement decreaseAdultQty;

	@FindBy(how = How.XPATH, using = "//*[@id=\"adaptive-menu\"]/div/div/section/div[1]/div[2]/div/button[2]")
	private WebElement increaseChildQty;

	@FindBy(how = How.XPATH, using = "//*[@id=\"adaptive-menu\"]/div/div/section/div[1]/div[2]/div/button[1]")
	private WebElement decreaseChildQty;

	@FindBy(how = How.XPATH, using = "//*[@id=\"adaptive-menu\"]/div/div/section/div[1]/div[3]/div/button[2]")
	private WebElement increaseInfantQty;

	@FindBy(how = How.XPATH, using = "//*[@id=\"adaptive-menu\"]/div/div/section/div[1]/div[3]/div/button[1]")
	private WebElement decreaseInfantQty;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"adaptive-menu\"]/div/div/div[2]/button")
	private WebElement doneBtnTraveler;

	@FindBy(how = How.ID, using = "package-rooms-hp-package")
	private WebElement inputAdultQtyPackage;

	@FindBy(how = How.XPATH, using = "//*[@type=\"button\"]/span[contains(text(),\"Add to Cart\")][1]")
	private WebElement inputAddToCart;

	public SearchResultPage searchFlight(String from, String to, String qty, String startDate, String finishDate) {
		// Exercise 1 - step 1
		try {
			clickFlightType(); // click the flight type
			clickRoundtrip(); // click the roundtrip
			addOrigin(1, from); // sending origin
			addDestination(1, to); // sending destination
			addDeparting(1, startDate);// sending departing
			addReturning(1, finishDate);// sending returning
			addTravelersQty(1, "A", qty); // sending adult qty
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
		// Exercise 2 step 1 and 2
		try {
			CaptureScreenShot.takeAScreenShot(driver);
			clickVacationPackages(); // click Flight and Hotel
			addOrigin(2, from); // sending origin
			addDestination(2, to); // sending destination
			addDeparting(2, startDate);// sending departing
			addReturning(2, finishDate);// sending returning
			addTravelersQty(2, "A", qty); // sending adult qty
			CaptureScreenShot.takeAScreenShot(driver);
			searchClicPackage();// performing the search
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

	public void searchClicPackage() {
		try {
			BasePage.implicitWaitVel("element", "", btnSearchPackage);
			btnSearchPackage.click(); // performing the search

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void addOrigin(int testNumber, String origin) {
		try {
			if (testNumber == 1) {
				BasePage.implicitWaitVel("element", "", originBox);
				originBox.click();
				BasePage.implicitWaitVel("element", "", originInput);
				originInput.sendKeys(origin); // sending origin
				BasePage.implicitWaitVel("xpath",
						"//*[@id=\"location-field-leg1-origin-menu\"]/div[2]/ul/li[1]/button/div/div[1]/span/strong[contains(text(),'"
								+ origin + "')]",
						null);
				driver.findElement(By.xpath(
						"//*[@id=\"location-field-leg1-origin-menu\"]/div[2]/ul/li[1]/button/div/div[1]/span/strong[contains(text(),'"
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
				BasePage.implicitWaitVel("element", "", destinationBox);
				destinationBox.click();
				BasePage.implicitWaitVel("element", "", destinationInput);
				destinationInput.sendKeys(destination); // sending origin
				BasePage.implicitWaitVel("xpath",
						"//*[@id=\"location-field-leg1-destination-menu\"]/div[2]/ul/li[1]/button/div/div[1]/span/strong[contains(text(),'"
								+ destination + "')]",
						null);
				driver.findElement(By.xpath(
						"//*[@id=\"location-field-leg1-destination-menu\"]/div[2]/ul/li[1]/button/div/div[1]/span/strong[contains(text(),'"
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
				DatePickerHandle.SelectDepartingDateFromMultiDateCalendar(testNumber, startDate);
			} else if (testNumber == 2) {
				DatePickerHandle.SelectDepartingDateFromMultiDateCalendar(testNumber, startDate);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void addReturning(int testNumber, String finishDate) {
		try {
			if (testNumber == 1) {
				DatePickerHandle.SelectReturningDateFromMultiDateCalendar(testNumber, finishDate);
			} else {
				DatePickerHandle.SelectReturningDateFromMultiDateCalendar(testNumber, finishDate);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void addTravelersQty(int testNumber, String whoTralves, String qty) {
		try {
			if (testNumber == 1) {
				String quantitySelected = "";
				BasePage.implicitWaitVel("element", "", travelerBox);
				travelerBox.click();
				if (whoTralves.equalsIgnoreCase("A")) {
					BasePage.implicitWaitVel("xpath", "//*[@id=\"adult-input-0\"]", null);
					quantitySelected = driver.findElement(By.xpath("//*[@id=\"adult-input-0\"]")).getText();
					int selectedAdult = Integer.parseInt(quantitySelected);
					if (selectedAdult < Integer.parseInt(qty)) {
						while (Integer.parseInt(quantitySelected) < Integer.parseInt(qty)) {
							increaseAdultQty.click();
							selectedAdult++;
						}
					} else if (selectedAdult > Integer.parseInt(qty)) {
						while (Integer.parseInt(quantitySelected) < Integer.parseInt(qty)) {
							decreaseAdultQty.click();
							selectedAdult--;
						}
					}
				} else if (whoTralves.equalsIgnoreCase("C")) {
					quantitySelected = driver.findElement(By.xpath("//*[@id=\"child-input-0\"]")).getText();
					int selectedChild = Integer.parseInt(quantitySelected);
					if (selectedChild < Integer.parseInt(qty)) {
						while (Integer.parseInt(quantitySelected) < Integer.parseInt(qty)) {
							increaseAdultQty.click();
							selectedChild++;
						}
					} else if (selectedChild > Integer.parseInt(qty)) {
						while (Integer.parseInt(quantitySelected) < Integer.parseInt(qty)) {
							decreaseAdultQty.click();
							selectedChild--;
						}
					}
				} else {
					quantitySelected = driver.findElement(By.xpath("//*[@id=\"infant-input-0\"]")).getText();
					int selectedInfant = Integer.parseInt(quantitySelected);
					if (selectedInfant < Integer.parseInt(qty)) {
						while (Integer.parseInt(quantitySelected) < Integer.parseInt(qty)) {
							increaseAdultQty.click();
							selectedInfant++;
						}
					} else if (selectedInfant > Integer.parseInt(qty)) {
						while (Integer.parseInt(quantitySelected) < Integer.parseInt(qty)) {
							decreaseAdultQty.click();
							selectedInfant--;
						}
					}
				}
			} else {
				BasePage.implicitWaitVel("element", "", inputAdultQtyPackage);
				Select drpAdult = new Select(driver.findElement(By.id("package-rooms-hp-package")));
				drpAdult.selectByValue(qty);
			}
			BasePage.implicitWaitVel("element","",doneBtnTraveler);
			doneBtnTraveler.click();
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
