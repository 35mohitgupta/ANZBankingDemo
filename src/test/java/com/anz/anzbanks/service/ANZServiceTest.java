package com.anz.anzbanks.service;


import static org.hamcrest.CoreMatchers.is;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyLong;

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
import org.springframework.test.context.junit4.SpringRunner;

import com.anz.anzbanks.dto.AccountDTO;
import com.anz.anzbanks.dto.AccountType;
import com.anz.anzbanks.dto.TransactionDTO;
import com.anz.anzbanks.dto.TransactionType;
import com.anz.anzbanks.entity.Account;
import com.anz.anzbanks.entity.Transaction;
import com.anz.anzbanks.exception.AccountException;
import com.anz.anzbanks.repository.AccountRepository;
import com.anz.anzbanks.repository.TransactionRepository;

@RunWith(SpringRunner.class)
public class ANZServiceTest {

	@Mock
	private AccountRepository accountRepository;
	
	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private ANZService anzService = new ANZServiceImpl();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void invalidAccountNo() throws AccountException {
		expectedException.expect(AccountException.class);
		expectedException.expectMessage("INVALID_ACCOUNT_NO");
		anzService.viewTransaction(10000000L);
	}
	
	@Test
	public void viewAccounts() {
		List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();
		AccountDTO accountDTO = new AccountDTO(50000000000L, "John", AccountType.CURRENT, LocalDate.of(2010, Month.APRIL, 22), "IND", 22222.22, null);
		AccountDTO accountDTO2 = new AccountDTO(50000000001L, "Erick", AccountType.SAVINGS, LocalDate.of(2012, Month.APRIL, 22), "USD", 8888888888.22, null);
		accountDTOList.add(accountDTO);
		accountDTOList.add(accountDTO2);
		
		List<Account> accountList = new ArrayList<Account>();
		Account account = new Account(50000000000L, "John", AccountType.CURRENT, LocalDate.of(2010, Month.APRIL, 22), "IND", 22222.22, null);
		Account account2 = new Account(50000000001L, "Erick", AccountType.SAVINGS, LocalDate.of(2012, Month.APRIL, 22), "USD", 8888888888.22, null);
		accountList.add(account);
		accountList.add(account2);
		
		when(accountRepository.findAll()).thenReturn(accountList);
	
		List<AccountDTO> actualAccountDTO = anzService.viewAccount();
		Assert.assertThat(actualAccountDTO, is(accountDTOList));
	}
	
	@Test
	public void viewTransactionEmpty() throws AccountException {
		expectedException.expect(AccountException.class);
		expectedException.expectMessage("NO_SUCH_ACCOUNT");
		when(transactionRepository.findByAccountNo(anyLong())).thenReturn(null);
		anzService.viewTransaction(3400000000L);
	}
	
	@Test
	public void viewTransaction() throws AccountException {
		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		TransactionDTO transactionDTO = new TransactionDTO(11, LocalDate.of(2010, 5, 22), "IND", null, 1000000.55, TransactionType.CREDIT, "credited 10000000");
		TransactionDTO transactionDTO2 = new TransactionDTO(154, LocalDate.of(2013, 5, 22), "EUR", null, 50000.0, TransactionType.DEBIT, "debited 5000000");
		transactionDTOs.add(transactionDTO);
		transactionDTOs.add(transactionDTO2);
		
		List<Transaction> transactions = new ArrayList<Transaction >();
		Transaction transaction = new Transaction(11, LocalDate.of(2010, 5, 22), "IND", null, 1000000.55, TransactionType.CREDIT, "credited 10000000");
		Transaction transaction2 = new Transaction(154, LocalDate.of(2013, 5, 22), "EUR", null, 50000.0, TransactionType.DEBIT, "debited 5000000");
		transactions.add(transaction);
		transactions.add(transaction2);
		
		when(transactionRepository.findByAccountNo(anyLong())).thenReturn(transactions);
		List<TransactionDTO> actualTransactionDTOs = anzService.viewTransaction(3400000000L);
		Assert.assertThat(actualTransactionDTOs, is(transactionDTOs));
	}
	
	
}
