package com.anz.anzbanks.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anz.anzbanks.dto.AccountDTO;
import com.anz.anzbanks.dto.TransactionDTO;
import com.anz.anzbanks.entity.Account;
import com.anz.anzbanks.entity.Transaction;
import com.anz.anzbanks.exception.AccountException;
import com.anz.anzbanks.repository.AccountRepository;
import com.anz.anzbanks.repository.TransactionRepository;
import com.anz.anzbanks.validators.AccountValidator;

@Transactional
@Service(value = "AccountService")
public class ANZServiceImpl implements ANZService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<AccountDTO> viewAccount() {
		logger.info("In AnzService: viewing account");
		List<Account> accountList = accountRepository.findAll();
		List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();
		for(Account account: accountList) {
			accountDTOList.add(AccountDTO.toDTO(account));
		}
		return accountDTOList;
	}

	@Override
	public List<TransactionDTO> viewTransaction(Long accountNo) throws AccountException {
		logger.info("In AnzService: viewing transactions");
		AccountValidator.validateAccountNo(accountNo);
		List<Transaction> transactionList = transactionRepository.findByAccountNo(accountNo);
		if(transactionList==null || transactionList.isEmpty())
			throw new AccountException("NO_SUCH_ACCOUNT");
		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		for(Transaction transaction: transactionList) {
			transactionDTOs.add(TransactionDTO.toDTO(transaction));
		}
		return transactionDTOs;
	}
	
	
}
