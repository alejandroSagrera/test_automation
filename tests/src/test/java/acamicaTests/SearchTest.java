package acamicaTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import acamicaTests.BaseTest;
import utilities.ExcelConfig;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class SearchTest extends BaseTest {

	@Test(dataProvider = "testdataSearch")
	public void searchF(String from, String to, String qty, String startDate, String finishDate) {
		homePage.searchFlight(from, to, qty, startDate, finishDate, driver);
	}

	@DataProvider
	public Object[][] testdataSearch() {
		ExcelConfig ex = new ExcelConfig("src/searchFData.xlsx");
		Object data[][] = ex.readExcel();
		return data;
	}

}
