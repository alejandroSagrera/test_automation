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

import acamica.util.CaptureScreenShot;
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

	@FindBy(how = How.XPATH, using = "//*[@id=\"forcedChoiceNoThanks\"]")
	private WebElement noThanksLink;

	public SearchResultPage(WebDriver driver) {
		super(driver);
		if (!this.isLoaded()) {
			throw new IllegalStateException("This is not the search result page");
		}
	}

	public boolean checkResultPage() { //Exercise 1 step 2
		try {
			BasePage.implicitWaitVel("id", "sortDropdown", null);
			BasePage.implicitWaitVel("xpath", "//button/span/span[contains(text(),'Select')]", null);
			BasePage.implicitWaitVel("xpath", "//*[@class=\"duration-emphasis\"]", null);
			BasePage.implicitWaitVel("xpath", "//*[@class=\"flight-details-link toggle-trigger\"]", null);
			CaptureScreenShot.takeAScreenShot(driver);
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
			CaptureScreenShot.takeAScreenShot(driver);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean areTheResultsSorted(String value) { //Exercise 1 step 3
		try {
			sortResults(value);
			return areTheySorted();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public TripDetailPage selectAFlight() { //Exercise 1 step 4 and 5
		try {
			BasePage.implicitWaitVel("xpath", "//*[@class=\"btn-secondary btn-action t-select-btn\"]", null);
			flightList.get(0).click();
			BasePage.implicitWaitVel("xpath",
					"//*[@id=\"basic-economy-tray-content-1\"]//span[contains(text(),'fare')]", null);
			flightList.get(0).click();
			BasePage.implicitWaitVel("xpath", "//*[@id=\"titleBar\"]/h1/div/span[1][contains(text(),'return')]", null);
			BasePage.implicitWaitVel("xpath", "//*[@class=\"btn-secondary btn-action t-select-btn\"]", null);
			flightList.get(4).click();
			BasePage.implicitWaitVel("xpath",
					"//*[@id=\"basic-economy-tray-content-1\"]//span[contains(text(),'fare')]", null);
			flightList.get(4).click();
			CaptureScreenShot.takeAScreenShot(driver);
			boolean submitbuttonPresence = driver.findElement(By.xpath("//*[@id=\"forcedChoiceNoThanks\"]"))
					.isDisplayed();
			if (submitbuttonPresence) {
				CaptureScreenShot.takeAScreenShot(driver);
				noThanksLink.click();
			}
			ArrayList<String> tabHandles = new ArrayList<String>(driver.getWindowHandles());
			String pageTitle = "Trip Detail | Travelocity";
			boolean found = false;
			int i = 0;
			while (i < tabHandles.size() && !found) {
				driver.switchTo().window(tabHandles.get(i));
				if (driver.getTitle().equalsIgnoreCase(pageTitle)) {
					found = true;
				}
				i++;
			}
			CaptureScreenShot.takeAScreenShot(driver);
			return PageFactory.initElements(driver, TripDetailPage.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public int handleHourMinute(int i) {
		try {
			String primerRegistro = duration.get(i).getText();
			String[] firstTime = DatePickerHandle.formattingDurations(primerRegistro);
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
			CaptureScreenShot.takeAScreenShot(driver);
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
