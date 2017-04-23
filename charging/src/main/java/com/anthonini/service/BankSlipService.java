package com.anthonini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.anthonini.model.BankSlip;
import com.anthonini.model.StatusBankSlip;
import com.anthonini.repository.BankSlipRepository;
import com.anthonini.repository.filter.BankSlipFilter;

@Service
public class BankSlipService {
	@Autowired
	BankSlipRepository bankSlipRepository;
	
	public void save(BankSlip bankSlip){
		try{			
			bankSlipRepository.save(bankSlip);
		
		}catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Invalid Date Format");
		}
	}

	public void delete(Long id) {
		bankSlipRepository.delete(id);
	}

	public String recieve(Long id) {
		BankSlip bankSlip = bankSlipRepository.findOne(id);
		bankSlip.setStatusBankSlip(StatusBankSlip.RECEIVED);
		bankSlipRepository.save(bankSlip);
		
		return StatusBankSlip.RECEIVED.getDescription();
	}
	
	public List<BankSlip> filter(BankSlipFilter bankSlipFilter){
		String description = bankSlipFilter.getDescription() == null ? "%" : bankSlipFilter.getDescription();		
		return bankSlipRepository.findByDescriptionContaining(description);
	}
}
