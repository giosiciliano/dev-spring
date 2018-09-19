package com.gio.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/showMyLoginPage") // maps to config
	public String showMyLoginPage() {
		return "plain-login"; // maps to view (jsp)
	}

}
