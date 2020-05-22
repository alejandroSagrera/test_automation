package acamicaTests;

import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;

import utilities.ExcelConfig;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class Test2 extends BaseTest {

	@Test(dataProvider = "testData")
	public void Exercise1(String from, String to, String qty, String startDate, String finishDate) {
		try {
			boolean areTheySorted = false;
			flightHotelPage = homePage.searchFlightAndHotel(from, to, qty, startDate, finishDate);
			AssertJUnit.assertTrue(flightHotelPage.checkResultPage());
			areTheySorted = flightHotelPage.areTheResultsSorted(); //TBD
			AssertJUnit.assertTrue(areTheySorted); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@DataProvider
	public Object[][] testData() {
		try {
			ExcelConfig ex = new ExcelConfig("src/testData_2.xlsx");
			Object[][] data = ex.readExcel();
			return data;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
