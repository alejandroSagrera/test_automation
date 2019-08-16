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

	@FindBy(how = How.XPATH, using = "//*[@class=\"duration-emphasis\"]")
	private WebElement flightDuration;

	public SearchResultPage(WebDriver driver) {
		super(driver);
		if (!this.isLoaded()) {
			throw new IllegalStateException("This is not the search result page");
		}
	}

	public boolean checkResultPage() {
		try {
			BasePage.implicitWaitVel("id", "sortDropdown");
			BasePage.implicitWaitVel("xpath", "//button/span/span[contains(text(),'Select')]");
			BasePage.implicitWaitVel("xpath", "//*[@class=\\\"duration-emphasis\\\"]");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public By getPageLoadLocator() {
		return By.id("sortDropdown");
	}

}
