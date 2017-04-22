package com.anthonini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anthonini.model.BankSlip;

public interface BankSlipRepository extends JpaRepository<BankSlip, Long>{

}
