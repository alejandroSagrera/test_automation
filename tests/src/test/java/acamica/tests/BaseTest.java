package acamica.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseTest {

	protected WebDriver driver;
	protected String base_url = "https://www.travelocity.com/";
	protected HomePage homePage;
	protected LoginPage loginPage;

	@BeforeMethod
	public void setUp() {
		driver = BrowserFactory.starBrowser("Chrome", base_url);
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}