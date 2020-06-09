package com.anz.anzbanks.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.anz.anzbanks.dto.AccountDTO;
import com.anz.anzbanks.dto.AccountType;
import com.anz.anzbanks.dto.TransactionDTO;
import com.anz.anzbanks.dto.TransactionType;
import com.anz.anzbanks.exception.AccountException;
import com.anz.anzbanks.service.ANZService;

@RunWith(SpringRunner.class)
public class AnzControllerTest {

	@Mock
	private ANZService anzService;

	@InjectMocks
	private ANZController aNZController = new ANZController();
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void viewAccount() throws AccountException {
		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		TransactionDTO transactionDTO = new TransactionDTO(11, LocalDate.of(2010, 5, 22), "IND", null, 1000000.55, TransactionType.CREDIT, "credited 10000000");
		TransactionDTO transactionDTO2 = new TransactionDTO(154, LocalDate.of(2013, 5, 22), "EUR", null, 50000.0, TransactionType.DEBIT, "debited 5000000");
		transactionDTOs.add(transactionDTO);
		transactionDTOs.add(transactionDTO2);
		List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();
		AccountDTO accountDTO = new AccountDTO(50000000000L, "John", AccountType.CURRENT, LocalDate.of(2010, Month.APRIL, 22), "IND", 22222.22, transactionDTOs);
		accountDTOList.add(accountDTO);

		ResponseEntity<List<AccountDTO>> expectedAccounts = new ResponseEntity<List<AccountDTO>>(accountDTOList, HttpStatus.OK);
		
		when(anzService.viewAccount()).thenReturn(accountDTOList);
		
		ResponseEntity<List<AccountDTO>> actualAccounts = aNZController.viewAccounts();
		Assert.assertThat(actualAccounts, is(expectedAccounts));
	}
	
	@Test
	public void viewTransactions() throws AccountException {
		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		TransactionDTO transactionDTO = new TransactionDTO(11, LocalDate.of(2010, 5, 22), "IND", null, 1000000.55, TransactionType.CREDIT, "credited 10000000");
		TransactionDTO transactionDTO2 = new TransactionDTO(154, LocalDate.of(2013, 5, 22), "EUR", null, 50000.0, TransactionType.DEBIT, "debited 5000000");
		transactionDTOs.add(transactionDTO);
		transactionDTOs.add(transactionDTO2);
		
		ResponseEntity<List<TransactionDTO>> expecetedResponse = new ResponseEntity<List<TransactionDTO>>(transactionDTOs, HttpStatus.OK);
		
		when(anzService.viewTransaction(anyLong())).thenReturn(transactionDTOs);
	
		ResponseEntity<List<TransactionDTO>> actualtransactionDTOs = aNZController.viewTransactions(1111111111L);
		Assert.assertThat(actualtransactionDTOs, is(expecetedResponse));
	}
	
	

	
}
