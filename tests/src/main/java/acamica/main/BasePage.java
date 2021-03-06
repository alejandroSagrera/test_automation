package acamica.main;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

	public static WebDriver driver;
	public final int seconds = 30;
	public static WebDriverWait wait;

	public BasePage(WebDriver driver) {
		BasePage.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 5);
	}

	public static void implicitWaitVel(String locator, String elemntLocation, WebElement elem) {
		try {
			switch (locator) {
			case "id":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elemntLocation)));
				break;
			case "xpath":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elemntLocation)));
				break;
			case "css":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elemntLocation)));
				break;
			case "element":
				wait.until(ExpectedConditions.visibilityOf(elem));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean isElementPresent(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public final boolean isLoaded() {
		return isElementPresent(this.getPageLoadLocator());
	}

	public abstract By getPageLoadLocator();
}
