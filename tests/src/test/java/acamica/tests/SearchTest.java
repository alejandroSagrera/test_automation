package acamica.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import acamica.tests.BaseTest;
import utilities.ExcelConfig;
import org.junit.runner.RunWith;
import org.testng.AssertJUnit;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class SearchTest extends BaseTest {

	@Test(dataProvider = "testdataSearch")
	public void searchF(String from, String to, int adult, String startDate, String finishDate) {


	}
	@DataProvider
	public Object[][] testdataSearch() {
		ExcelConfig ex = new ExcelConfig("src/productList.xlsx");
		Object data[][] = ex.readExcel();
		return data;
	}

}
