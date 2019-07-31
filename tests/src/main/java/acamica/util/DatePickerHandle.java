package acamica.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import acamica.main.BasePage;

public class DatePickerHandle extends BasePage {

	public DatePickerHandle(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static WebDriverWait wait;

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-hp-flight\"]")
	private static WebElement departingDatePicker; // departing date box

	@FindBy(how = How.ID, using = "flight-departing-hp-flight")
	private static WebElement returningDatePicker; // returning date box

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"icon icon-pagenext\"]")
	private static WebElement btnNextCalendar;

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"icon icon-pageprev\"]")
	private static WebElement btnPrevCalendar;

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"datepicker-dropdown\"]")
	private static WebElement dateWidgetFrom;

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]/div/div/div[2]/table/caption")
	private static WebElement dateStartHeader;

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]/div/div/div[3]/table/caption")
	private static WebElement dateFinishHeader;

	public static String getStartDateHeader() {
		try {
			return dateStartHeader.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "";
		}
	}

	public static String getFinishDateHeader() {
		try {
			return dateFinishHeader.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "";
		}
	}

	public static void departingClick() {
		try {
			departingDatePicker.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void returningClick() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flight-departing-hp-flight")));
		departingDatePicker.click();
	}

	public static void moveNextCalendar() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"icon icon-pagenext\"]")));
		btnNextCalendar.click();
	}

	public void movePrevCalendar() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"icon icon-pageprev\"]")));
		btnPrevCalendar.click();
	}

	public static String[] splittingHeaderInfo(String header) {
		try {
			String headerParts[] = header.split(" ");
			return headerParts;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public HashMap<String, String> settingMonthsHash() {
		HashMap<String, String> map = new HashMap<String, String>();
		return map;
	}

	public static boolean validateDate(String date) {
		final String DATE_FORMAT = "MM/dd/yyyy";
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static void SelectStartDateFromMultiDateCalendar(String date) throws InterruptedException {
		try {
			if (validateDate(date)) {
				String dateParts[] = date.split("/");
				int month = Integer.parseInt(dateParts[0])-1;
				String day = dateParts[1];
				String year = dateParts[2];
				departingClick();
				waitForWidget();
				selectStartDate(Integer.toString(month), day, year);
			} else {
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void selectStartDate(String month, String day, String year) {
		try {
			driver.findElement(By.xpath(
					"//*[@id='flight-departing-wrapper-hp-flight']//*[@class='datepicker-dropdown']//tr//td//button[@data-year='"
							+ year + "']" + "[@data-month='" + month + "'][@data-day='" + day + "']"))
					.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void waitForWidget() {
		try {
			BasePage.implicitWaitXpath("//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"datepicker-dropdown\"]");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public By getPageLoadLocator() {
		// TODO Auto-generated method stub
		return null;
	}

}
