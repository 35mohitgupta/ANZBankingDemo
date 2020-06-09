package com.anz.anzbanks.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.anz.anzbanks.dto.TransactionType;

@Entity
@Table(name="transaction")
public class Transaction {

	@Id
	@Column(name="transaction_id")
	private Integer transactionId;
	
	@Column(name="value_date")
	private LocalDate valueDate;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="debit_amount")
	private Double debitAmount;
	
	@Column(name="credit_amount")
	private Double creditAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(name="transaction_type")
	private TransactionType transactionType;
	
	@Column(name="tran_narrative")
	private String transactionNarrative;

	public Transaction() {}
	
	public Transaction(Integer transactionId, LocalDate valueDate, String currency, Double debitAmount,
			Double creditAmount, TransactionType transactionType, String transactionNarrative) {
		super();
		this.transactionId = transactionId;
		this.valueDate = valueDate;
		this.currency = currency;
		this.debitAmount = debitAmount;
		this.creditAmount = creditAmount;
		this.transactionType = transactionType;
		this.transactionNarrative = transactionNarrative;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDate getValueDate() {
		return valueDate;
	}

	public void setValueDate(LocalDate valueDate) {
		this.valueDate = valueDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(Double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public Double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionNarrative() {
		return transactionNarrative;
	}

	public void setTransactionNarrative(String transactionNarrative) {
		this.transactionNarrative = transactionNarrative;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditAmount == null) ? 0 : creditAmount.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((debitAmount == null) ? 0 : debitAmount.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
		result = prime * result + ((transactionNarrative == null) ? 0 : transactionNarrative.hashCode());
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
		result = prime * result + ((valueDate == null) ? 0 : valueDate.hashCode());
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
		Transaction other = (Transaction) obj;
		if (creditAmount == null) {
			if (other.creditAmount != null)
				return false;
		} else if (!creditAmount.equals(other.creditAmount))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (debitAmount == null) {
			if (other.debitAmount != null)
				return false;
		} else if (!debitAmount.equals(other.debitAmount))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		if (transactionNarrative == null) {
			if (other.transactionNarrative != null)
				return false;
		} else if (!transactionNarrative.equals(other.transactionNarrative))
			return false;
		if (transactionType != other.transactionType)
			return false;
		if (valueDate == null) {
			if (other.valueDate != null)
				return false;
		} else if (!valueDate.equals(other.valueDate))
			return false;
		return true;
	}
	
}
