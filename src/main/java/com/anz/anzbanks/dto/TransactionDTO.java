package com.anz.anzbanks.dto;

import java.time.LocalDate;

import com.anz.anzbanks.entity.Transaction;

public class TransactionDTO {

	private Integer transactionId;
	
	private LocalDate valueDate;
	
	private String currency;
	
	private Double debitAmount;
	
	private Double creditAmount;
	
	private TransactionType transactionType;

	private String transactionNarrative;
	
	public TransactionDTO(Integer transactionId, LocalDate valueDate, String currency, Double debitAmount,
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

	public TransactionDTO() {}
	
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
		TransactionDTO other = (TransactionDTO) obj;
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

	public Transaction toEntity() {
		Transaction transactionEntity = new Transaction();
		transactionEntity.setTransactionId(transactionId);
		transactionEntity.setValueDate(valueDate);
		transactionEntity.setCurrency(currency);
		transactionEntity.setDebitAmount(debitAmount);
		transactionEntity.setCreditAmount(creditAmount);
		transactionEntity.setTransactionType(transactionType);
		transactionEntity.setTransactionNarrative(transactionNarrative);
		return transactionEntity;
	}
	
	public static TransactionDTO toDTO(Transaction transaction) {
		if(transaction == null)
			return null;
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.transactionId = transaction.getTransactionId();
		transactionDTO.valueDate = transaction.getValueDate();
		transactionDTO.currency = transaction.getCurrency();
		transactionDTO.debitAmount = transaction.getDebitAmount();
		transactionDTO.creditAmount = transaction.getCreditAmount();
		transactionDTO.transactionType = transaction.getTransactionType();
		transactionDTO.transactionNarrative = transaction.getTransactionNarrative();
		return transactionDTO;
	}

	@Override
	public String toString() {
		return "TransactionDTO [transactionId=" + transactionId + ", valueDate=" + valueDate + ", currency=" + currency
				+ ", debitAmount=" + debitAmount + ", creditAmount=" + creditAmount + ", transactionType="
				+ transactionType + ", transactionNarrative=" + transactionNarrative + "]";
	}
	
}
