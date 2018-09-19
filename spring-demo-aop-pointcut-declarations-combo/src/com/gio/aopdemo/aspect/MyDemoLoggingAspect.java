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
	
	// create pointcut for getter
	@Pointcut("execution(* com.gio.aopdemo.dao.*.get*(..))")
	private void getter() {}
	
	// create pointcut for setter
	@Pointcut("execution(* com.gio.aopdemo.dao.*.set*(..))")
	private void setter() {}
	
	// create pointcut: include package...exclude getter/setter
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterSetter() {}
	
	// @Before advice
	//@Before("execution(public void addAccount())") // pointcut expression
	@Before("forDaoPackageNoGetterSetter()") 
	public void beforeAddAccountAdvice() {
		System.out.println("\n=====>>> Executing @Before advice on method");
	}

	@Before("forDaoPackageNoGetterSetter()") 
	public void performApiAnalytics() {
		System.out.println("\n=====>>> Performing API analytics");
	}

	
}
