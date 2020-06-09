package com.anz.anzbanks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.anz.anzbanks.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	@Query("select a.transactions from Account a where a.accountNo = ?1")
	List<Transaction> findByAccountNo(Long accountNo);
	
}
