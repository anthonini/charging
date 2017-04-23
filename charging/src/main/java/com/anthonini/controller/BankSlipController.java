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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anthonini.model.BankSlip;
import com.anthonini.model.StatusBankSlip;
import com.anthonini.repository.filter.BankSlipFilter;
import com.anthonini.service.BankSlipService;

@Controller
@RequestMapping("/bankslip")
public class BankSlipController {
	
	@Autowired
	BankSlipService bankSlipService;
	
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
		
		try{
			boolean creating = bankSlip.getId() == null;
			
			bankSlipService.save(bankSlip);
		
			String message, redirect;
			if(creating){
				message = "Bank slip successfully created!";
				redirect = "redirect:/bankslip/new";
			}else{
				message = "Bank slip successfully updated!";
				redirect = "redirect:/bankslip";
			}
			
			attributes.addFlashAttribute("message", message);			
			return redirect;
		}catch (IllegalArgumentException e) {
			errors.rejectValue("date", null, e.getMessage());
			return "Form";
		}
	}
	
	@RequestMapping
	public ModelAndView search(@ModelAttribute("filter") BankSlipFilter bankSlipFilter){
		ModelAndView modelAndView = new ModelAndView("List");
		modelAndView.addObject("bankSlips", bankSlipService.filter(bankSlipFilter));
		
		return modelAndView;
	}
	
	@RequestMapping("{id}")
	public ModelAndView updating(@PathVariable("id") BankSlip bankSlip){		
		ModelAndView modelAndView = new ModelAndView("Form");
		modelAndView.addObject(bankSlip);
		return modelAndView;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
		bankSlipService.delete(id);
		attributes.addFlashAttribute("message", "Bank slip successfully removed!");
		
		return "redirect:/bankslip";
	}
	
	@RequestMapping(value="/{id}/receive", method = RequestMethod.PUT)
	public @ResponseBody String receive(@PathVariable("id") Long id){
		return bankSlipService.recieve(id);
	}
	
	@ModelAttribute("allStatusBankSlip")
	public List<StatusBankSlip> allStatusBankSlip(){
		return Arrays.asList(StatusBankSlip.values());
	}
}
