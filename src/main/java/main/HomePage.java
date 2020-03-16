package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import util.DatePickerHandle;

public class HomePage extends BasePage {

    @FindBy(how = How.XPATH, using = "//form[@id=\"gcw-flights-form-hp-flight\"]//*[@type=\"submit\"]")
    private WebElement btnSearch;

    @FindBy(how = How.XPATH, using = "//*[@id=\"tab-flight-tab-hp\"]/span[contains(text(),'Flights')]")
    private WebElement flightType;

    @FindBy(how = How.ID, using = "flight-type-roundtrip-label-hp-flight")
    private WebElement roundtripLink;

    @FindBy(how = How.ID, using = "flight-origin-hp-flight")
    private WebElement inputOrigin;

    @FindBy(how = How.ID, using = "flight-destination-hp-flight")
    private WebElement inputDestination;

    @FindBy(how = How.XPATH, using = "//*[@id=\"typeaheadDataPlain\"]/div/li[1]")
    private WebElement inputCity;

    @FindBy(how = How.ID, using = "flight-departing-hp-flight")
    private WebElement inputDeparting;

    @FindBy(how = How.ID, using = "flight-returning-hp-flight")
    private WebElement inputReturning;

    @FindBy(how = How.ID, using = "flight-adults-hp-flight")
    private WebElement inputAdultQty;

    @FindBy(how = How.XPATH, using = "//*[@type=\"button\"]/span[contains(text(),\"Add to Cart\")][1]")
    private WebElement inputAddToCart;

    public SearchResultPage searchFlight(String from, String to, String qty, String startDate, String finishDate) {
        try {
            clickFlightType(); // click on the flight type
            clickRoundtrip(); // then click on the roundtrip
            addOrigin(from); // sending origin
            addDestination(to); // sending destination
            addDeparting(startDate);// sending departing
            addReturning(finishDate);// sending returning
            addAdultQty(qty); // sending adult qty
            searchClic();// performing the search
            return PageFactory.initElements(driver, SearchResultPage.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void searchClic() {
        try {
            BasePage.implicitWaitVel("xpath", "//form[@id=\"gcw-flights-form-hp-flight\"]//*[@type=\"submit\"]");
            btnSearch.click(); // performing the search

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addOrigin(String origin) {
        try {
            BasePage.implicitWaitVel("id", "flight-origin-hp-flight");
            inputOrigin.clear();
            inputOrigin.sendKeys(origin); // sending origin
            BasePage.implicitWaitVel("xpath", "//*[@id=\"typeaheadDataPlain\"]/div/li[1]");
            inputCity.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDestination(String destination) {
        try {
            BasePage.implicitWaitVel("id", "flight-destination-hp-flight");
            inputDestination.clear();
            inputDestination.sendKeys(destination); // sending destination
            BasePage.implicitWaitVel("xpath", "//*[@id=\"typeaheadDataPlain\"]/div/li[1]");
            inputCity.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDeparting(String startDate) {
        try {
            BasePage.implicitWaitVel("xpath", "//*[@id=\"flight-departing-hp-flight\"]");
            inputDeparting.clear();
            DatePickerHandle.SelectDepartingDateFromMultiDateCalendar(startDate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addReturning(String finishDate) {
        try {
            BasePage.implicitWaitVel("xpath", "//*[@id=\"flight-returning-hp-flight\"]");
            inputReturning.clear();
            DatePickerHandle.SelectReturningDateFromMultiDateCalendar(finishDate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addAdultQty(String qty) {
        try {
            BasePage.implicitWaitVel("id", "flight-adults-hp-flight");
            inputAdultQty.sendKeys(qty); // sending adults qty
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clickFlightType() {
        /* perform click at the flight type */
        try {
            BasePage.implicitWaitVel("xpath", "//*[@id=\"tab-flight-tab-hp\"]/span[contains(text(),'Flights')]");
            flightType.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clickRoundtrip() {
        /* perform click at the roundtrip section */
        try {
            BasePage.implicitWaitVel("id", "flight-type-roundtrip-label-hp-flight");
            flightType.click();
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
