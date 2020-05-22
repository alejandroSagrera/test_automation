package acamicaTests;
import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;

import utilities.ExcelConfig;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class Test1 extends BaseTest {
    @Test(dataProvider = "testData")
    public void Exercise1(String from, String to, String qty, String startDate, String finishDate, String sortBy) {
        try {
            boolean areTheySorted = false;
            resultPage = homePage.searchFlight(from, to, qty, startDate, finishDate);
            AssertJUnit.assertTrue(resultPage.checkResultPage());
            areTheySorted = resultPage.areTheResultsSorted(sortBy);
            AssertJUnit.assertTrue(areTheySorted);
            if (areTheySorted) {
                tripDetailPage = resultPage.selectAFlight();
                if(tripDetailPage!=null){
                    flightCheckout = tripDetailPage.verifyTripDetails();
                    AssertJUnit.assertTrue(flightCheckout.isPageOpen());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DataProvider
    public Object[][] testData() {
        try {
            ExcelConfig ex = new ExcelConfig("src/testData_1.xlsx");
            Object[][] data = ex.readExcel();
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
