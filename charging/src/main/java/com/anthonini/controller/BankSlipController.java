package com.anthonini.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anthonini.model.BankSlip;
import com.anthonini.model.StatusBankSlip;
import com.anthonini.repository.BankSlipRepository;

@Controller
@RequestMapping("/bankslip")
public class BankSlipController {

	@Autowired
	BankSlipRepository bankSlipRepository;
	
	@RequestMapping("/new")
	public ModelAndView form(){
		ModelAndView modelAndView = new ModelAndView("Form");
		modelAndView.addObject(new BankSlip());
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(@Validated BankSlip bankSlip, Errors errors, RedirectAttributes attributes){
		if(errors.hasErrors()){
			return "Form";
		}
		
		bankSlipRepository.save(bankSlip);
		attributes.addFlashAttribute("message", "Bank slip successfully created!");
		return "redirect:/bankslip/new";
	}
	
	@RequestMapping
	public ModelAndView search(){
		List<BankSlip> bankSlips = bankSlipRepository.findAll();
		ModelAndView modelAndView = new ModelAndView("List");
		modelAndView.addObject("bankSlips", bankSlips);
		
		return modelAndView;
	}
	
	@ModelAttribute("allStatusBankSlip")
	public List<StatusBankSlip> allStatusBankSlip(){
		return Arrays.asList(StatusBankSlip.values());
	}
}
