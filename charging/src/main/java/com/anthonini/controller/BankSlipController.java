package com.anthonini.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		String redirect, message;
		if(bankSlip.getId() == null){
			message = "Bank slip successfully created!";
			redirect = "redirect:/bankslip/new";
		}else{
			message = "Bank slip successfully updated!";
			redirect = "redirect:/bankslip";
		}
		
		bankSlipRepository.save(bankSlip);
		
		attributes.addFlashAttribute("message", message);
		
		return redirect;
	}
	
	@RequestMapping
	public ModelAndView search(){
		List<BankSlip> bankSlips = bankSlipRepository.findAll();
		ModelAndView modelAndView = new ModelAndView("List");
		modelAndView.addObject("bankSlips", bankSlips);
		
		return modelAndView;
	}
	
	@RequestMapping("{id}")
	public ModelAndView updating(@PathVariable("id") BankSlip bankSlip){		
		ModelAndView modelAndView = new ModelAndView("Form");
		modelAndView.addObject(bankSlip);
		return modelAndView;
	}
	
	@ModelAttribute("allStatusBankSlip")
	public List<StatusBankSlip> allStatusBankSlip(){
		return Arrays.asList(StatusBankSlip.values());
	}
}
