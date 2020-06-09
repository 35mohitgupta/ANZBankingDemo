package com.anz.anzbanks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anz.anzbanks.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	
}
