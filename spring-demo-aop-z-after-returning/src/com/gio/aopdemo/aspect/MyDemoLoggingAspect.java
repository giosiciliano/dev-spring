package com.gio.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.gio.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	// all related advices for logging
	
	// @Before advice
	//@Before("execution(public void addAccount())") // pointcut expression
	@Before("com.gio.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()") 
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {
		System.out.println("\n=====>>> Executing @Before advice on method");
		
		// display method signature
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		System.out.println("Method: " + methodSig);
		
		// display method arguments
		
		// get args
		Object[] args = joinPoint.getArgs();
		
		// loop args
		for (Object object : args) {
			System.out.println(object);
			
			if (object instanceof Account) {
				// downcast and print Account specific elements
				Account acc = (Account)object;
				System.out.println("Account name: " + acc.getName());
				System.out.println("Account level: " + acc.getLevel());
			}
		}
	}
	
	// after returning advice on findAccounts method
	@AfterReturning(
			pointcut="execution(* com.gio.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
		// print method we are advising on
		System.out.println("\n=====>>> Executing"
				+ " @AfterReturning on method: " + joinPoint.getSignature().toShortString());
		
		// post-process the data (modify before returning to calling program)
		
		// print results of method call
		System.out.println("\n=====>>> result is: " + result);
		
		// convert account names to uppercase
		convertAccountNamesToUpperCase(result);
		
		// print results of method call
		System.out.println("\n=====>>> result is: " + result);
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {
		
		for (Account account : result) {
			account.setName(account.getName().toUpperCase());
		}
		
	}
	
}
