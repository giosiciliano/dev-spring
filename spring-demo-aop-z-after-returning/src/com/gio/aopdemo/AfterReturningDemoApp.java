package com.gio.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gio.aopdemo.dao.AccountDAO;

public class AfterReturningDemoApp {
	
	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// call metod to find accounts
		List<Account> accounts = accountDAO.findAccounts();
		
		// display accounts
		System.out.println("\n\nMain Program: AfterReturningDemoApp");
		System.out.println("----");
		System.out.println(accounts + "\n");
		
		// close context
		context.close();
		
	}

}
