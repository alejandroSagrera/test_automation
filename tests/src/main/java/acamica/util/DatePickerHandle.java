package acamica.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DatePickerHandle {

	public static WebDriverWait wait;
	@FindBy(how = How.ID, using = "flight-departing-hp-flight")
	private WebElement StartDatePicker;

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"icon icon-pagenext\"]")
	private WebElement btnNextCalendar;

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"icon icon-pageprev\"]")
	private WebElement btnPrevCalendar;

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"datepicker-cal-month\"]/table/tbody")
	private WebElement dateWidgetFrom;

	public void startDPClick() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flight-departing-hp-flight")));
		StartDatePicker.click();
	}

	public void moveNextCalendar() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\\\"flight-departing-wrapper-hp-flight\\\"]//*[@class=\\\"icon icon-pagenext\\\"]")));
		btnNextCalendar.click();
	}

	public void movePrevCalendar() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"icon icon-pageprev\"]")));
		btnPrevCalendar.click();
	}

	public void SelectDayFromMultiDateCalendar(String month, String day, String year) throws InterruptedException {
		startDPClick();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"datepicker-cal-month\"]/table/tbody")));
	}

}
