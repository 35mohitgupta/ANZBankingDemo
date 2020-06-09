package com.anz.anzbanks.repository;

import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.anz.anzbanks.dto.AccountType;
import com.anz.anzbanks.dto.TransactionType;
import com.anz.anzbanks.entity.Account;
import com.anz.anzbanks.entity.Transaction;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionRepositoryTest {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Test
	public void findByAccountNoTest() {
		
		List<Transaction> transactions = new ArrayList<Transaction >();
		Transaction transaction = new Transaction(11, LocalDate.of(2010, 5, 22), "IND", null, 1000000.55, TransactionType.CREDIT, "credited 10000000");
		Transaction transaction2 = new Transaction(154, LocalDate.of(2013, 5, 22), "EUR", null, 50000.0, TransactionType.DEBIT, "debited 5000000");
		transactions.add(transaction);
		transactions.add(transaction2);
		Account account = new Account(50000000000L, "John", AccountType.CURRENT, LocalDate.of(2010, Month.APRIL, 22), "IND", 22222.22, transactions);
		accountRepository.saveAndFlush(account);
		
		List<Transaction> actualTransactions = transactionRepository.findByAccountNo(50000000000L);
		Assert.assertThat(actualTransactions, is(transactions));
	}
	
}
