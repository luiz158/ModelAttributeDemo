package com.stackroute.modeldemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.stackroute.modeldemo.model.Vendor;

@Controller
public class VendorController2 {
/*
	Vendor vendor = new Vendor();
	
	public VendorController2() {
		
	}

	@RequestMapping("/")
	public String getAllNotes(Vendor vendor) {
		return "index";
	}

		
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addNote(@ModelAttribute("vendor") Vendor vendor, BindingResult result, ModelMap model) {
		
		// Validation
		boolean error = false;
		if (vendor.getVendorCode().isEmpty()) {
			model.addAttribute("errorMessage1", "Vendor Code cannot be empty");
			error = true;
		}
		
		if (vendor.getVendorName().isEmpty()) {
			model.addAttribute("errorMessage2", "Vendor Name cannot be empty");
			error = true;
		}
		
		model.addAttribute("vendors", vendor);

		return "index";

		
	}

	
	
*/	
	
}
