package acamica.main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
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
			int menor = handleHourMinute(0);
			for (int i = 1; i < duration.size(); i++) {

			}
			// DatePickerHandle.formattingDurations(hourMinute);
			return true;
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
