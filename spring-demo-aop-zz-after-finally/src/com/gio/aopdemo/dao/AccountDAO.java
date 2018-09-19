package com.gio.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gio.aopdemo.Account;

@Component
public class AccountDAO {

	private String name;
	private String serviceCode;
	
	public List<Account> findAccounts(boolean tripWire) {
		
		// simulate exception
		if (tripWire) {
			throw new RuntimeException("No soup for you!!!");
		}
		
		List<Account> accounts = new ArrayList<>();
		
		// create accounts
		Account acc = new Account("John", "Silver");
		Account acc2 = new Account("Madhu", "Platinum");
		Account acc3 = new Account("Luca", "Gold");
		
		// add accounts to list
		accounts.add(acc);
		accounts.add(acc2);
		accounts.add(acc3);
		
		return accounts;
	}
	
	public void addAccount(Account account, boolean vipFlag) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
	}
	
	public boolean doWork() {
		System.out.println(getClass() + ": doWork()");
		
		return false;
	}

	public String getName() {
		System.out.println(getClass() + ": getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + ": setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + ": getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + ": setServiceCode()");
		this.serviceCode = serviceCode;
	}
	
}

