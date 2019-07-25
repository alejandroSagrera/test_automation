package acamica.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchResultPage extends BasePage {

	@FindBy(how = How.ID, using = "sortDropdown")
	private WebElement ddboxOrder;

	@FindBy(how = How.XPATH, using = "//button/span/span[contains(text(),'Select')]")
	private WebElement btnSelect;

	public SearchResultPage(WebDriver driver) {
		super(driver);
		if (!this.isLoaded()) {
			throw new IllegalStateException("This is not the search result page");
		}
	}

	@Override
	public By getPageLoadLocator() {
		return By.id("sortDropdown");
	}

}
