package acamica.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchResultPage extends BasePage {

	@FindBy(how = How.XPATH, using = "//*[@id=\"content\"]/div[3]/div[1]/div/div[2]/div[1]/h4/a")
	private WebElement lbnProductName;

	@FindBy(how = How.XPATH, using = "//*[@id=\"content\"]//span[@class=\"price-tax\"]")
	private WebElement lbnProductPrice;

	public SearchResultPage(WebDriver driver) {
		super(driver);
		if (!this.isLoaded()) {
			throw new IllegalStateException("No es la web resultado");
		}
	}

	public String getProductPrice() {
		try {
			BasePage.implicitWaitXpath("//*[@id=\"content\"]//span[@class=\"price-tax\"]");
			return lbnProductPrice.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "";
		}
	}

	public String getProductName() {
		try {
			BasePage.implicitWaitXpath("//*[@id=\"content\"]/div[3]/div[1]/div/div[2]/div[1]/h4/a");
			return lbnProductName.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "";
		}
	}

	@Override
	public By getPageLoadLocator() {
		return By.xpath("//*[@id=\"content\"]/h2[contains(text(),\"Products meeting the search criteria\")]");
	}

}
