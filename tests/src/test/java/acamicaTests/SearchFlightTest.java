package acamicaTests;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import acamicaTests.BaseTest;
import utilities.ExcelConfig;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class SearchFlightTest extends BaseTest {

	@Test(dataProvider = "testDataSearch")
	public void searchF(String from, String to, String qty, String startDate, String finishDate) {
		try {
			resultPage = homePage.searchFlight(from, to, qty, startDate, finishDate);
			AssertJUnit.assertTrue(resultPage.checkResultPage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@DataProvider
	public Object[][] testDataSearch() {
		try {
			ExcelConfig ex = new ExcelConfig("src/searchFData.xlsx");
			Object data[][] = ex.readExcel();
			return data;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
