package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import main.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DatePickerHandle extends BasePage {

    public DatePickerHandle(WebDriver driver) {
        super(driver);
    }

    public static WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-hp-flight\"]")
    private static WebElement departingDatePicker; // departing date box

    @FindBy(how = How.XPATH, using = "//*[@id=\"flight-returning-hp-flight\"]")
    private static WebElement returningDatePicker; // returning date box

    @FindBy(how = How.XPATH, using = "//*[@class=\"datepicker-paging datepicker-next btn-paging btn-secondary next\"]")
    private static WebElement btnNextCalendar;

    @FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"icon icon-pageprev\"]")
    private static WebElement btnPrevCalendar;

    @FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"datepicker-dropdown\"]")
    private static WebElement dateWidgetFrom;

    @FindBy(how = How.XPATH, using = "//*[@id=\"flight-departing-wrapper-hp-flight\"]/div/div/div[2]/table/caption")
    private static WebElement dateStartHeader;

    @FindBy(how = How.XPATH, using = "//*[@id=\"flight-returning-wrapper-hp-flight\"]/div/div/div[3]/table/caption")
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
            BasePage.implicitWaitVel("xpath", "//*[@id=\"flight-departing-hp-flight\"]");
            departingDatePicker.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void returningClick() {
        try {
            BasePage.implicitWaitVel("xpath", "//*[@id=\"flight-returning-hp-flight\"]");
            returningDatePicker.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void moveNextCalendar() {
        try {
            BasePage.implicitWaitVel("xpath",
                    "//*[@class=\"datepicker-paging datepicker-next btn-paging btn-secondary next\"]");
            btnNextCalendar.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void movePrevCalendar() {
        try {
            BasePage.implicitWaitVel("xpath",
                    "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"icon icon-pageprev\"]");
            btnPrevCalendar.click();
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

    public static void SelectDepartingDateFromMultiDateCalendar(String date) throws InterruptedException {
        try {
            if (isAValidDate(date)) {
                waitForWidget("depar");
                String[] formattedDate = formattingDate(date);
                selectTheMonth("depart", Integer.parseInt(formattedDate[0]));
                selectDate("depart", formattedDate[0], formattedDate[1], formattedDate[2]);
            } else {
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void SelectReturningDateFromMultiDateCalendar(String date) throws InterruptedException {
        try {
            if (isAValidDate(date)) {
                waitForWidget("retu");
                String[] formattedDate = formattingDate(date);
                selectTheMonth("ret", Integer.parseInt(formattedDate[0]));
                selectDate("return", formattedDate[0], formattedDate[1], formattedDate[2]);
            } else {
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void selectTheMonth(String dateType, Integer month) {
        try {
            String[] headerText = new String[2];
            if (dateType.compareTo("depart") == 0) {
                BasePage.implicitWaitVel("xpath",
                        "//*[@id=\"flight-departing-wrapper-hp-flight\"]/div/div/div[2]/table/caption");
                headerText = splittingHeaderInfo(dateStartHeader.getText());
            } else {
                BasePage.implicitWaitVel("xpath",
                        "//*[@id=\"flight-returning-wrapper-hp-flight\"]/div/div/div[3]/table/caption");
                headerText = splittingHeaderInfo(dateFinishHeader.getText());
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

    public static void selectDate(String departOrReturn, String month, String day, String year) {
        try {
            switch (departOrReturn) {
                case "depart":
                    driver.findElement(By.xpath(
                            "//*[@id='flight-departing-wrapper-hp-flight']//*[@class='datepicker-dropdown']//tr//td//button[@data-year='"
                                    + year + "']" + "[@data-month='" + month + "'][@data-day='" + day + "']"))
                            .click();
                    break;
                case "return":
                    driver.findElement(By.xpath(
                            "//*[@id='flight-returning-wrapper-hp-flight']//*[@class='datepicker-dropdown']//tr//td//button[@data-year='"
                                    + year + "']" + "[@data-month='" + month + "'][@data-day='" + day + "']"))
                            .click();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void waitForWidget(String dateType) {
        try {
            if (dateType.compareTo("depar") == 0) {
                BasePage.implicitWaitVel("xpath", "//*[@id=\"flight-departing-hp-flight\"]");
                departingDatePicker.click();
                BasePage.implicitWaitVel("xpath",
                        "//*[@id=\"flight-departing-wrapper-hp-flight\"]//*[@class=\"datepicker-dropdown\"]");
            } else {
                BasePage.implicitWaitVel("xpath", "//*[@id=\"flight-departing-hp-flight\"]");
                returningDatePicker.click();
                BasePage.implicitWaitVel("xpath",
                        "//*[@id=\"flight-returning-wrapper-hp-flight\"]//*[@class=\"datepicker-dropdown\"]");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public By getPageLoadLocator() {
        return null;
    }

}
