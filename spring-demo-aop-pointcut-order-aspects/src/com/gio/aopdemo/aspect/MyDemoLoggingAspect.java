package com.gio.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	// all related advices for logging
	
	// @Before advice
	//@Before("execution(public void addAccount())") // pointcut expression
	@Before("com.gio.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()") 
	public void beforeAddAccountAdvice() {
		System.out.println("\n=====>>> Executing @Before advice on method");
	}
	
}
