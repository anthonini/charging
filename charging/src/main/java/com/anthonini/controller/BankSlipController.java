package com.anthonini.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.anthonini.model.BankSlip;
import com.anthonini.model.StatusBankSlip;
import com.anthonini.repository.BankSlipRepository;

@Controller
@RequestMapping("/bankslip")
public class BankSlipController {

	@Autowired
	BankSlipRepository repository;
	
	@RequestMapping("/new")
	public ModelAndView form(){
		ModelAndView modelAndView = new ModelAndView("Form");
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView save(BankSlip bankSlip){
		ModelAndView modelAndView = new ModelAndView("Form");
		repository.save(bankSlip);
		modelAndView.addObject("mensagem", "Bank slip successfully created!");
		
		return modelAndView;
	}
	
	@ModelAttribute("allStatusBankSlip")
	public List<StatusBankSlip> allStatusBankSlip(){
		return Arrays.asList(StatusBankSlip.values());
	}
}
