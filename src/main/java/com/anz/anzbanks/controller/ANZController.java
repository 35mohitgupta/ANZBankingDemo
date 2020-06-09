package com.anz.anzbanks.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.anz.anzbanks.dto.AccountDTO;
import com.anz.anzbanks.dto.TransactionDTO;
import com.anz.anzbanks.exception.AccountException;
import com.anz.anzbanks.service.ANZService;

@CrossOrigin
@RestController
public class ANZController {

	@Autowired
	private ANZService accountService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(path = "/view-accounts")
	public ResponseEntity<List<AccountDTO>> viewAccounts(){
		logger.info("In AnzController: viewing accounts");
		List<AccountDTO> accounts = accountService.viewAccount();
		ResponseEntity<List<AccountDTO>> response = new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);
		return response;
	}
	
	@GetMapping(path = "/view-transactions/{accountNo}")
	public ResponseEntity<List<TransactionDTO>> viewTransactions(@PathVariable(value = "accountNo") Long accountNo)  throws AccountException{
		logger.info("In AnzController: viewing transactions");
		List<TransactionDTO> transactions = accountService.viewTransaction(accountNo);
		ResponseEntity<List<TransactionDTO>> response = new ResponseEntity<List<TransactionDTO>>(transactions, HttpStatus.OK);
		return response;
	}
	
}
