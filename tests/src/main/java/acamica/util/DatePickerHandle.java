package acamica.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import acamica.main.BasePage;

public class DatePickerHandle extends BasePage {

	public DatePickerHandle(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static WebDriverWait wait;

	@FindBy(how = How.ID, using = "flight-departing-hp-flight")
	private static WebElement departingDatePicker; // departing date box

	@FindBy(how = How.ID, using = "package-departing-hp-package")
	private static WebElement departingDatePickerPackage;

	@FindBy(how = How.ID, using = "flight-returning-hp-flight")
	private static WebElement returningDatePicker; // returning date box

	@FindBy(how = How.ID, using = "package-returning-hp-package")
	private static WebElement returningDatePickerPackage;

	@FindBy(how = How.XPATH, using = "//*[@class=\"datepicker-paging datepicker-next btn-paging btn-secondary next\"]")
	private static WebElement btnNextCalendar;

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"icon icon-pageprev\"]")
	private static WebElement btnPrevCalendarFlight;

	@FindBy(how = How.XPATH, using = "//*[@id=\"package-departing-wrapper-hp-package\"]//*[@class=\"icon icon-pageprev\"]")
	private static WebElement btnPrevCalendarPackage;

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"datepicker-dropdown\"]")
	private static WebElement dateWidgetFrom;

	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]/div/div/div[2]/table/caption")
	private static WebElement dateStartHeader;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"flight-returning-wrapper-hp-flight\"]/div/div/div[3]/table/caption")
	private static WebElement dateFinishHeader;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"package-departing-wrapper-hp-package\"]/div/div/div[2]/table/caption")
	private static WebElement dateStartHeaderPackage;

	
	@FindBy(how = How.XPATH, using = "//*[@id=\"package-returning-wrapper-hp-package\"]/div/div/div[2]/table/caption")
	private static WebElement dateFinishHeaderPackage;

	private static JavascriptExecutor jse = (JavascriptExecutor) driver;

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
			BasePage.implicitWaitVel("element", "", departingDatePicker);
			departingDatePicker.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void returningClick() {
		try {
			BasePage.implicitWaitVel("xpath", "", returningDatePicker);
			returningDatePicker.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void moveNextCalendar() {
		try {
			BasePage.implicitWaitVel("xpath", "", btnNextCalendar);
			btnNextCalendar.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void movePrevCalendar(int testNumber) {
		try {
			if (testNumber == 1) {
				BasePage.implicitWaitVel("xpath", "", btnPrevCalendarFlight);
				btnPrevCalendarFlight.click();
			} else if (testNumber == 2) {
				BasePage.implicitWaitVel("element", "", btnPrevCalendarPackage);
				btnPrevCalendarPackage.click();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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

	public static Integer settingMonthsHash(String month) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("Jan", 0);
		map.put("Feb", 1);
		map.put("Mar", 2);
		map.put("Apr", 3);
		map.put("May", 4);
		map.put("Jun", 5);
		map.put("Jul", 6);
		map.put("Aug", 7);
		map.put("Sep", 8);
		map.put("Oct", 9);
		map.put("Nov", 10);
		map.put("Dec", 11);
		Integer monthRet = map.get(month);
		return monthRet;
	}

	public static boolean isAValidDate(String date) {
		final String DATE_FORMAT = "MM/dd/yyyy";
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			if (isACorrectDate(df, date)) {
				df.setLenient(false);
				df.parse(date);
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean isACorrectDate(DateFormat df, String date) {
		try {
			Date dateForm = df.parse(date);
			Date currentDate = removeTime(java.util.Calendar.getInstance().getTime());
			return (dateForm.compareTo(currentDate) >= 0) ? true : false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static Date removeTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static String[] formattingDate(String date) {
		try {
			String dateParts[] = date.split("/");
			int month = Integer.parseInt(dateParts[0]) - 1;
			dateParts[0] = Integer.toString(month);
			if (Integer.parseInt(dateParts[1]) < 10) {
				String[] formattedDay = {};
				formattedDay = dateParts[1].split("0");
				dateParts[1] = formattedDay[1];
			}
			return dateParts;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static String[] formattingDurations(String hour) {
		try {
			String newHour[] = hour.split(" ");
			String extractH[] = newHour[0].split("h");
			String extractM[] = newHour[1].split("m");
			String newRet[] = new String[2];
			newRet[0] = extractH[0];
			newRet[1] = extractM[0];
			return newRet;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static int transformIntoMinutes(String[] myTime) {
		try {
			int hour = Integer.parseInt(myTime[0]);
			int minutes = Integer.parseInt(myTime[1]);
			return (hour > 0) ? ((hour * 60) + minutes) : minutes;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	public static void SelectDepartingDateFromMultiDateCalendar(int testNumber, String date)
			throws InterruptedException {
		try {
			if (isAValidDate(date)) {
				String[] formattedDate = formattingDate(date);
				waitForWidget(testNumber, "depar");
				selectTheMonth(testNumber, "depart", Integer.parseInt(formattedDate[0]));
				selectDate(testNumber, "depart", formattedDate[0], formattedDate[1], formattedDate[2]);
			} else {
				System.out.println("Date format is incorrect.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void SelectReturningDateFromMultiDateCalendar(int testNumber, String date)
			throws InterruptedException {
		try {
			if (isAValidDate(date)) {
				waitForWidget(testNumber, "retu");
				String[] formattedDate = formattingDate(date);
				selectTheMonth(testNumber, "ret", Integer.parseInt(formattedDate[0]));
				selectDate(testNumber, "return", formattedDate[0], formattedDate[1], formattedDate[2]);
			} else {
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void selectTheMonth(int testNumber, String dateType, Integer month) {
		try {
			String[] headerText = new String[2];
			if (testNumber == 1) {
				if (dateType.compareTo("depart") == 0) {
					BasePage.implicitWaitVel("xpath",
							"//*[@id=\"flight-departing-wrapper-hp-flight\"]/div/div/div[2]/table/caption", null);
					headerText = splittingHeaderInfo(dateStartHeader.getText());
				} else {
					BasePage.implicitWaitVel("xpath",
							"//*[@id=\"flight-returning-wrapper-hp-flight\"]/div/div/div[3]/table/caption", null);
					headerText = splittingHeaderInfo(dateFinishHeader.getText());
				}
			} else if (testNumber == 2) {
				if (dateType.compareTo("depart") == 0) {
					BasePage.implicitWaitVel("xpath",
							"//*[@id=\"package-departing-wrapper-hp-package\"]/div/div/div[2]/table/caption", null);
					headerText = splittingHeaderInfo(dateStartHeaderPackage.getText());
				} else {
					BasePage.implicitWaitVel("xpath",
							"//*[@id=\"package-returning-wrapper-hp-package\"]/div/div/div[2]/table/caption", null);
					headerText = splittingHeaderInfo(dateFinishHeaderPackage.getText());
				}
			}
			int actualMonth = settingMonthsHash(headerText[0]);
			if (month > actualMonth) {
				int i = month - actualMonth; // here
				while (i > 1) {
					moveNextCalendar();
					i -= 2;
				}
			} else if (month < actualMonth) {
				int j = 12 - actualMonth;
				while (j > 1) {
					moveNextCalendar();
					j -= 2;
				}
				actualMonth = 0; // next year
				j = month - actualMonth;
				while (j > 1) {
					moveNextCalendar();
					j -= 2;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void selectDate(int testNumber, String departOrReturn, String month, String day, String year) {
		try {
			if (testNumber == 1) {
				switch (departOrReturn) {
				case "depart":
					Thread.sleep(3000);
					driver.findElement(By.xpath(
							"//*[@data-year='" + year + "']" + "[@data-month='" + month + "'][@data-day='" + day + "']"))
							.click();
					break;
				case "return":
					jse.executeScript("window.scrollBy(0,350)", "");
					Thread.sleep(3000);
					driver.findElement(By.xpath(
							"//*[@data-year='" + year + "']" + "[@data-month='" + month + "'][@data-day='" + day + "']"))
							.click();
					break;
				}
			} else if (testNumber == 2) {
				switch (departOrReturn) {
				case "depart":
					Thread.sleep(3000);
					driver.findElement(
							By.xpath("//*[@data-year='" + year + "']" + "[@data-month='" + month + "'][@data-day='" + day + "']"))
							.click();
					break;
				case "return":
					jse.executeScript("window.scrollBy(0,350)", "");
					Thread.sleep(3000);
					driver.findElement(By.xpath("//*[@data-year='" + year + "']" + "[@data-month='" + month + "'][@data-day='" + day + "']"))
					.click();
					//driver.findElement(By.xpath("//*[@id=\"travel-advisory-close-button\"]")).click();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void waitForWidget(int testNumber, String dateType) {
		try {
			if (testNumber == 1) {
				if (dateType.compareTo("depar") == 0) {
					BasePage.implicitWaitVel("element", "", departingDatePicker);
					departingDatePicker.click();
					BasePage.implicitWaitVel("xpath",
							"//*[@id=\"flight-departing-wrapper-hp-flight\"]/div/div[@class=\"datepicker-cal\"]",
							null);
				} else {
					BasePage.implicitWaitVel("xpath", "", returningDatePicker);
					returningDatePicker.click();
					BasePage.implicitWaitVel("xpath",
							"//*[@id=\"flight-returning-wrapper-hp-flight\"]/div/div[@class=\"datepicker-cal\"]",
							null);
				}
			} else if (testNumber == 2) {
				if (dateType.compareTo("depar") == 0) {
					BasePage.implicitWaitVel("element", "", departingDatePickerPackage);
					departingDatePickerPackage.click();
					BasePage.implicitWaitVel("xpath",
							"//*[@id=\"package-departing-wrapper-hp-package\"]//*[@class=\"datepicker-dropdown\"]",
							null);
				} else {
					BasePage.implicitWaitVel("element", "", returningDatePickerPackage);
					returningDatePickerPackage.click();
					BasePage.implicitWaitVel("xpath",
							"//*[@id=\"package-returning-wrapper-hp-package\"]//*[@class=\"datepicker-dropdown\"]",
							null);
				}
			}

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
