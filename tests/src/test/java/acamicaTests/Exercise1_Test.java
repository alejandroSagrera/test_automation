package acamicaTests;

import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;

import acamicaTests.BaseTest;
import utilities.ExcelConfig;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class Exercise1_Test extends BaseTest {

	@Test(dataProvider = "testData", priority = 1)
	public void Exercise1(String from, String to, String qty, String startDate, String finishDate, String sortBy) {
		try {
			boolean areTheySorted = false;
			resultPage = homePage.searchFlight(from, to, qty, startDate, finishDate);
			AssertJUnit.assertTrue(resultPage.checkResultPage());
			areTheySorted = resultPage.areTheResultsSorted(sortBy);
			AssertJUnit.assertTrue(areTheySorted);
			if (areTheySorted) {
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@DataProvider
	public Object[][] testData() {
		try {
			ExcelConfig ex = new ExcelConfig("src/testData.xlsx");
			Object data[][] = ex.readExcel();
			return data;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
