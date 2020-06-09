package com.anz.anzbanks.service;

import java.util.List;

import com.anz.anzbanks.dto.AccountDTO;
import com.anz.anzbanks.dto.TransactionDTO;
import com.anz.anzbanks.exception.AccountException;

public interface ANZService {

	public List<AccountDTO> viewAccount();
	
	public List<TransactionDTO> viewTransaction(Long accountNo)  throws AccountException;
	
}
