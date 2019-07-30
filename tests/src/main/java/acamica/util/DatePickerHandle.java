package acamica.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DatePickerHandle {

	public static WebDriverWait wait;

	@FindBy(how = How.ID, using = "flight-departing-hp-flight")
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

	public static void departingClick() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flight-departing-hp-flight")));
		departingDatePicker.click();
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
			return false;
		}
	}

	public static void SelectStartDateFromMultiDateCalendar(String date, WebDriver driver) throws InterruptedException {
		try {
			if (validateDate(date)) {
				String dateParts[] = date.split("/");
				String month = dateParts[0];
				String day = dateParts[1];
				String year = dateParts[2];
				departingClick();
				waitForWidget();
				moveNextCalendar();
				selectStartDate(month, day, year, driver);
			} else {
			}
		} catch (Exception e) {
			throw e;
		}

	}

	public static void selectStartDate(String month, String day, String year, WebDriver driver) {
		try {
			driver.findElement(By.xpath(
					"//*[@id='flight-departing-wrapper-hp-flight']//*[@class='datepicker-dropdown']//tr//td//button[@data-year='"
							+ year + "']" + "[@data-month='" + month + "'][@data-day='" + day + "']")).click();
		} catch (Exception e) {
			throw e;
		}
	}

	public static void waitForWidget() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"datepicker-dropdown\"]")));
		} catch (Exception e) {
			throw e;
		}
	}

}
