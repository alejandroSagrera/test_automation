package acamica.util;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

public class DatePickerHandleTest {

	@Test
	public void testValidateDateOK() {
		String startDate = "10/25/2019";
		AssertJUnit.assertTrue(DatePickerHandle.isAValidDate(startDate));
	}

	@Test
	public void testValidateDateFail() {
		String startDate = "";
		AssertJUnit.assertFalse(DatePickerHandle.isAValidDate(startDate));
	}

	@Test
	public void testValidateDateFail2() {
		String startDate = "15/02/2019";
		AssertJUnit.assertFalse(DatePickerHandle.isAValidDate(startDate));
	}

	@Test
	public void formattingHoursTest() {
		String hourMinute = "1h 20m";
		AssertJUnit.assertNotNull(DatePickerHandle.formattingDurations(hourMinute));
	}

	@Test
	public void transformIntoMinutes() {
		String newRet[] = new String[2];
		newRet[0] = "1";
		newRet[1] = "20";
		AssertJUnit.assertEquals(50, DatePickerHandle.transformIntoMinutes(newRet));
	}

}
