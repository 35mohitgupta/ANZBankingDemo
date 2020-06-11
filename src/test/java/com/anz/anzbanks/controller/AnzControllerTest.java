package com.anz.anzbanks.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.anz.anzbanks.dto.AccountDTO;
import com.anz.anzbanks.dto.AccountType;
import com.anz.anzbanks.dto.TransactionDTO;
import com.anz.anzbanks.dto.TransactionType;
import com.anz.anzbanks.exception.AccountException;
import com.anz.anzbanks.service.ANZService;

@RunWith(SpringRunner.class)
@WebMvcTest(ANZController.class)
public class AnzControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	

	@MockBean
	private ANZService anzService;
	
	@Autowired
	private ANZController aNZController;
	
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
	
	

	@Test
	public void notFoundTransaction() throws Exception {
		
		when(anzService.viewTransaction(anyLong())).thenThrow(new AccountException("NO_SUCH_ACCOUNT"));
		
		mockMvc.perform(get("/transactions/view")
			      .contentType(MediaType.APPLICATION_JSON)
			      .content("1000000000"))
			      .andExpect(status().isNotFound())
			      .andExpect(jsonPath("$", is("NO ACCOUNT EXISITS WITH THIS ACCOUNT NO")));
		
	}
	
	@Test
	public void InvalidAccountNo() throws Exception {
		
		when(anzService.viewTransaction(anyLong())).thenThrow(new AccountException("INVALID_ACCOUNT_NO"));
		
		mockMvc.perform(get("/transactions/view")
			      .contentType(MediaType.APPLICATION_JSON)
			      .content("100000"))
			      .andExpect(status().isBadRequest())
			      .andExpect(jsonPath("$", is("INVALID ACCOUNT NUMBER")));
		
	}

	
}
