package com.gio.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gio.aopdemo.dao.AccountDAO;

public class AfterThrowingDemoApp {
	
	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// call method to find accounts
		List<Account> accounts = null;
		
		try {
			// add boolean flag to simulate exception
			boolean tripWire = true;
			accounts = accountDAO.findAccounts(tripWire);
		} catch (Exception e) {
			System.out.println("\n\nMain program ... caught exception: " + e);
		}
		
		
		// display accounts
		System.out.println("\n\nMain Program: AfterThrowingDemoApp");
		System.out.println("----");
		System.out.println(accounts + "\n");
		
		// close context
		context.close();
		
	}

}
