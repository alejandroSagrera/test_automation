package acamica.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatePickerHandleTest {

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
	public void testValidateDateFail2() {
		String startDate = "15/02/2019";
		assertFalse(DatePickerHandle.validateDate(startDate));
	}

}
