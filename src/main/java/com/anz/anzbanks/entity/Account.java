package com.anz.anzbanks.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.anz.anzbanks.dto.AccountType;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@Column(name = "account_no")
	private Long accountNo;
	
	@Column(name = "account_name")
	private String accountName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="account_type")
	private AccountType accountType;
	
	@Column(name="balance_date")
	private LocalDate balanceDate;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name = "opening_available_bal")
	private Double openingAvailableBalance;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="account_no")
	private List<Transaction> transactions;

	public Account() {}
	
	public Account(Long accountNo, String accountName, AccountType accountType, LocalDate balanceDate, String currency,
			Double openingAvailableBalance, List<Transaction> transactions) {
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

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
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
		Account other = (Account) obj;
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

	
}
