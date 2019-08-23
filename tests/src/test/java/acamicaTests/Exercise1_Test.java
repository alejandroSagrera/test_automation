package acamicaTests;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import acamicaTests.BaseTest;
import utilities.ExcelConfig;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class Exercise1_Test extends BaseTest {

	@Test(dataProvider = "testDataSearch", priority = 1)
	public void searchF(String from, String to, String qty, String startDate, String finishDate) {
		try {
			resultPage = homePage.searchFlight(from, to, qty, startDate, finishDate);
			AssertJUnit.assertTrue(resultPage.checkResultPage());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(dataProvider = "testDataSort", priority = 2)
	public void sortFlightResults(String sortBy) {
		try {
			AssertJUnit.assertTrue(resultPage.areTheResultsSorted(sortBy));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@DataProvider
	public Object[][] testDataSort() {
		try {
			ExcelConfig ex = new ExcelConfig("src/searchSortData.xlsx");
			Object data[][] = ex.readExcel();
			return data;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
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
