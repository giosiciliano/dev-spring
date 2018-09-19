package com.gio.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	// all related advices for logging
	
	// pointcut declarations
	@Pointcut("execution(* com.gio.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	// @Before advice
	//@Before("execution(public void addAccount())") // pointcut expression
	@Before("forDaoPackage()") 
	public void beforeAddAccountAdvice() {
		System.out.println("\n=====>>> Executing @Before advice on method");
	}

	@Before("forDaoPackage()") 
	public void performApiAnalytics() {
		System.out.println("\n=====>>> Performing API analytics");
	}

	
}
