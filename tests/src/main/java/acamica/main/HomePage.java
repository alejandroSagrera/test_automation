package acamica.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	@FindBy(how = How.XPATH, using = "//*[@id=\"search\"]//button")
	private WebElement btnSearch;

	@FindBy(how = How.XPATH, using = "//*[@id=\"search\"]//input")
	private WebElement inputSearch;

	@FindBy(how = How.XPATH, using = "//*[@type=\"button\"]/span[contains(text(),\"Add to Cart\")][1]")
	private WebElement inputAddToCart;


	public void searchProduct(String product) {
		try {
			BasePage.implicitWait("//*[@id=\"search\"]//input");
			inputSearch.sendKeys(product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public SearchResultPage clickSearchButton() {
		try {
			BasePage.implicitWait("//*[@id=\"search\"]//button");
			btnSearch.click();
			return PageFactory.initElements(driver, SearchResultPage.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public By getPageLoadLocator() {
		return By.id("slideshow0");
	}

	public HomePage(WebDriver driver) {
		super(driver);
		if (!this.isLoaded()) {
			throw new IllegalStateException("No es la web inicial");
		}
	}

}