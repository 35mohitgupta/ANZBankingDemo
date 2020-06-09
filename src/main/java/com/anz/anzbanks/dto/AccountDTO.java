package com.anz.anzbanks.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.anz.anzbanks.entity.Account;
import com.anz.anzbanks.entity.Transaction;

public class AccountDTO {

	private Long accountNo;
	
	private String accountName;
	
	private AccountType accountType;
	
	private LocalDate balanceDate;
	
	private String currency;

	private Double openingAvailableBalance;
	
	private List<TransactionDTO> transactions;

	public AccountDTO() {}
	
	public AccountDTO(Long accountNo, String accountName, AccountType accountType, LocalDate balanceDate,
			String currency, Double openingAvailableBalance, List<TransactionDTO> transactions) {
		super();
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.accountType = accountType;
		this.balanceDate = balanceDate;
		this.currency = currency;
		this.openingAvailableBalance = openingAvailableBalance;
		this.transactions = transactions;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public LocalDate getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(LocalDate balanceDate) {
		this.balanceDate = balanceDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getOpeningAvailableBalance() {
		return openingAvailableBalance;
	}

	public void setOpeningAvailableBalance(Double openingAvailableBalance) {
		this.openingAvailableBalance = openingAvailableBalance;
	}

	public List<TransactionDTO> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionDTO> transactions) {
		this.transactions = transactions;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + ((balanceDate == null) ? 0 : balanceDate.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((openingAvailableBalance == null) ? 0 : openingAvailableBalance.hashCode());
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountDTO other = (AccountDTO) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (accountType != other.accountType)
			return false;
		if (balanceDate == null) {
			if (other.balanceDate != null)
				return false;
		} else if (!balanceDate.equals(other.balanceDate))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (openingAvailableBalance == null) {
			if (other.openingAvailableBalance != null)
				return false;
		} else if (!openingAvailableBalance.equals(other.openingAvailableBalance))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		return true;
	}

	public Account toEntity() {
		Account accountEntity = new Account();
		accountEntity.setAccountNo(accountNo);
		accountEntity.setAccountName(accountName);
		accountEntity.setAccountType(accountType);
		accountEntity.setBalanceDate(balanceDate);
		accountEntity.setCurrency(currency);
		accountEntity.setOpeningAvailableBalance(openingAvailableBalance);
		List<Transaction> transactionEntities = null;
		if(transactions!=null && !transactions.isEmpty()) {
			transactionEntities = new ArrayList<Transaction>();
			for(TransactionDTO transactionDTO : transactions) {
				transactionEntities.add(transactionDTO.toEntity());
			}
		}
		accountEntity.setTransactions(transactionEntities);
		return accountEntity;
	}
	
	public static AccountDTO toDTO(Account account) {
		if(account==null)
			return null;
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.accountNo = account.getAccountNo();
		accountDTO.accountName = account.getAccountName();
		accountDTO.accountType = account.getAccountType();
		accountDTO.balanceDate = account.getBalanceDate();
		accountDTO.currency = account.getCurrency();
		accountDTO.openingAvailableBalance = account.getOpeningAvailableBalance();
		List<TransactionDTO> transactionDTOList = null;
		List<Transaction> transactionList = account.getTransactions();
		if(transactionList != null && !transactionList.isEmpty()) {
			transactionDTOList = new ArrayList<TransactionDTO>();
			for(Transaction transactionEntity: transactionList) {
				transactionDTOList.add(TransactionDTO.toDTO(transactionEntity));
			}
		}
		accountDTO.transactions = transactionDTOList;
		return accountDTO;
	}

	@Override
	public String toString() {
		return "AccountDTO [accountNo=" + accountNo + ", accountName=" + accountName + ", accountType=" + accountType
				+ ", balanceDate=" + balanceDate + ", currency=" + currency + ", openingAvailableBalance="
				+ openingAvailableBalance + ", transactions=" + transactions + "]";
	}
}
