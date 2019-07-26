package acamica.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DatePickerHandle {

	public static WebDriverWait wait;

	@FindBy(how = How.ID, using = "flight-departing-hp-flight")
	private WebElement departingDatePicker; // departing date box

	@FindBy(how = How.ID, using = "flight-departing-hp-flight")
	private WebElement returningDatePicker; // returning date box

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"icon icon-pagenext\"]")
	private WebElement btnNextCalendar;

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"icon icon-pageprev\"]")
	private WebElement btnPrevCalendar;

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"datepicker-dropdown\"]")
	private WebElement dateWidgetFrom;

	public void departingClick() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flight-departing-hp-flight")));
		departingDatePicker.click();
	}

	public void returningClick() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flight-departing-hp-flight")));
		departingDatePicker.click();
	}

	public void moveNextCalendar() {
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

	public boolean validateDate(String date) {
		final String DATE_FORMAT = "dd-MM-yyyy";
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public void SelectDayFromMultiDateCalendar(String date) throws InterruptedException {
		if (validateDate(date)) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flight-departing-hp-flight")));
			departingClick();
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"datepicker-dropdown\"]")));
			HashMap<String, String> map = settingMonthsHash();
		} else {

		}

	}

}
