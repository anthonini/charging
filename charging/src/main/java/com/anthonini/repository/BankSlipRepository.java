package com.anthonini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anthonini.model.BankSlip;

public interface BankSlipRepository extends JpaRepository<BankSlip, Long>{
	
	public List<BankSlip> findByDescriptionContaining(String description);
}
