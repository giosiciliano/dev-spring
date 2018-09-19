package com.gio.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gio.aopdemo.dao.AccountDAO;
import com.gio.aopdemo.dao.MembershipDAO;

public class MainDemoApp {
	
	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// get bean from spring container
		MembershipDAO membershipDAO = 
				context.getBean("membershipDAO", MembershipDAO.class);
		
		Account account = new Account();
		account.setName("gio");
		account.setLevel("99");
		
		// call business method
		accountDAO.addAccount(account, true);
		accountDAO.doWork();
		
		// call membership business method
		membershipDAO.addSillyMember();
		membershipDAO.goToSleep();
		
		// close context
		context.close();
		
	}

}
