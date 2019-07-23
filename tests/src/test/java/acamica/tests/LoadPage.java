package acamica.tests;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class LoadPage extends BaseTest {

	@Test(priority=1) 
	public void loadOK() {
		AssertJUnit.assertEquals("Wander Wisely with Cheap Hotels, Flights, Vacations & Travel Deals | Travelocity", 
				homePage.getTitle());
		}
}
