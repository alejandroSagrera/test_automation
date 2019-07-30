package acamica.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatePickerHandleTest {

	@Test
	public void testDepartingClick() {
		fail("Not yet implemented");
	}

	@Test
	public void testReturningClick() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveNextCalendar() {
		fail("Not yet implemented");
	}

	@Test
	public void testMovePrevCalendar() {
		fail("Not yet implemented");
	}

	@Test
	public void testSettingMonthsHash() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateDateOK() {
		String startDate = "10/25/2019";
		assertTrue(DatePickerHandle.validateDate(startDate));
	}
	@Test
	public void testValidateDateFail() {
		String startDate = "";
		assertFalse(DatePickerHandle.validateDate(startDate));
	}

	@Test
	public void testSelectStartDateFromMultiDateCalendar() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectStartDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitForWidget() {
		fail("Not yet implemented");
	}

}
