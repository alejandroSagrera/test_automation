package acamicaTests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import acamica.main.HomePage;
import utilities.BrowserFactory;

import org.openqa.selenium.WebDriver;

public class BaseTest {

	protected WebDriver driver;
	protected String base_url = "https://www.travelocity.com";
	protected HomePage homePage;


	@BeforeMethod
	public void setUp() {
		driver=BrowserFactory.starBrowser("Chrome", base_url);
		homePage=new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
