package acamicaTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import acamica.main.HomePage;
import acamica.util.DatePickerHandle;
import utilities.BrowserFactory;

import org.openqa.selenium.WebDriver;

public class BaseTest {

	protected WebDriver driver;
	protected String base_url = "https://www.travelocity.com";
	protected HomePage homePage;
	protected DatePickerHandle datePicker;


	@BeforeMethod
	public void setUp() {
		driver=BrowserFactory.starBrowser("Chrome", base_url);
		homePage=new HomePage(driver);
		datePicker = new DatePickerHandle(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
