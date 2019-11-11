package acamica.main;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import acamica.util.DatePickerHandle;

public class SearchResultPage extends BasePage {

	@FindBy(how = How.ID, using = "sortDropdown")
	private WebElement ddboxOrder;

	@FindBy(how = How.XPATH, using = "//button/span/span[contains(text(),'Select')]")
	private WebElement btnSelect;

	@FindBy(how = How.XPATH, using = "//*[@class=\"duration-emphasis\"]")
	private WebElement flightDuration;

	@FindBy(how = How.XPATH, using = "//*[@class=\"flight-details-link toggle-trigger\"]")
	private WebElement flightBaggageInfo;

	@FindBy(how = How.ID, using = "sortDropdown")
	private WebElement selectSort;

	@FindBy(how = How.XPATH, using = "//span[@class=\"duration-emphasis\"]")
	private List<WebElement> duration;

	@FindBy(how = How.XPATH, using = "//*[@class=\"btn-secondary btn-action t-select-btn\"]")
	private List<WebElement> flightList;

	public SearchResultPage(WebDriver driver) {
		super(driver);
		if (!this.isLoaded()) {
			throw new IllegalStateException("This is not the search result page");
		}
	}

	public boolean checkResultPage() {
		try {
			BasePage.implicitWaitVel("id", "sortDropdown");
			BasePage.implicitWaitVel("xpath", "//button/span/span[contains(text(),'Select')]");
			BasePage.implicitWaitVel("xpath", "//*[@class=\"duration-emphasis\"]");
			BasePage.implicitWaitVel("xpath", "//*[@class=\"flight-details-link toggle-trigger\"]");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void sortResults(String value) {
		try {
			Select dropdown = new Select(selectSort);
			dropdown.selectByValue(value);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean areTheResultsSorted(String value) {
		try {
			sortResults(value);
			return areTheySorted() ? true : false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public FlightSelectedPage selectAFlight() {
		try {
			BasePage.implicitWaitVel("xpath", "//*[@class=\"btn-secondary btn-action t-select-btn\"]");
			flightList.get(0).click();
			BasePage.implicitWaitVel("xpath", "//*[@class=\"btn-secondary btn-action t-select-btn\"]");
			flightList.get(1).click();
			return PageFactory.initElements(driver, FlightSelectedPage.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public int handleHourMinute(int i) {
		try {
			String primerRegistro = duration.get(i).getText();
			String firstTime[] = DatePickerHandle.formattingDurations(primerRegistro);
			return DatePickerHandle.transformIntoMinutes(firstTime);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	public boolean areTheySorted() {
		try {
			int n = duration.size();
			ArrayList<Integer> times = new ArrayList<Integer>();
			for (int i = 0; i < n; i++) {
				times.add(handleHourMinute(i));
			}
			int nTimes = times.size();
			boolean sorted = false;
			int j = 0;
			while (j < nTimes && !sorted) {
				if (times.get(j) < times.get(j + 1)) {
					j++;
				} else {
					sorted = true;
				}
			}
			return sorted;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public By getPageLoadLocator() {
		return By.id("sortDropdown");
	}

}
