package acamicaTests;

import org.junit.runner.RunWith;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junitparams.JUnitParamsRunner;
import utilities.ExcelConfig;

@RunWith(JUnitParamsRunner.class)
public class SortFlightResultsTest extends BaseTest {

	@Test(dataProvider = "testDataSort")
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
}
