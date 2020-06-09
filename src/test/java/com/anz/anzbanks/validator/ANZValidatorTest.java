package com.anz.anzbanks.validator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.anz.anzbanks.exception.AccountException;
import com.anz.anzbanks.validators.AccountValidator;

@RunWith(SpringRunner.class)
public class ANZValidatorTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void invalidAccountNo1() throws AccountException {
		expectedException.expect(AccountException.class);
		expectedException.expectMessage("INVALID_ACCOUNT_NO");
		AccountValidator.validateAccountNo((long) 10000);
	}
	
	@Test
	public void invalidAccountNo2() throws AccountException {
		expectedException.expect(AccountException.class);
		expectedException.expectMessage("INVALID_ACCOUNT_NO");
		AccountValidator.validateAccountNo(20000000000L);
	}
	
	@Test
	public void validAccountNo() throws AccountException {
		AccountValidator.validateAccountNo(2000000000L);
	}
}
