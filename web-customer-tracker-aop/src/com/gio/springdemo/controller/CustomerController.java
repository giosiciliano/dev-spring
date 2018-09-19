package com.gio.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gio.springdemo.entity.Customer;
import com.gio.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// inject customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers (Model theModel) {
		
		// get customers
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		//save customer using service
		customerService.saveCustomer(theCustomer);
		
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
			Model theModel) {

		// get customer from service
		Customer theCustomer = customerService.getCustomer(theId);
				
		// set customer as model attribute to prepop form
		theModel.addAttribute("customer", theCustomer);
		
		// send to customer form
		return "customer-form";

	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId,
			Model theModel) {
		
		// delete customer from service
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";	
	}
	
	@PostMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
			Model theModel) {
		
		// search customers from the service
		List<Customer> customers = customerService.search(theSearchName);
		
		//add the customers to the mode
		theModel.addAttribute("customers", customers);
		
		return "list-customers";	
	}
	
}
