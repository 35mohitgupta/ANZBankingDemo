package com.anz.anzbanks.validators;

import com.anz.anzbanks.exception.AccountException;

public class AccountValidator {

	
	
	public static void validateAccountNo(Long accountNo) throws AccountException {
		if(accountNo<1000000000 || accountNo >9999999999L) {
			throw new AccountException("INVALID_ACCOUNT_NO");
		}
	}
	
}
