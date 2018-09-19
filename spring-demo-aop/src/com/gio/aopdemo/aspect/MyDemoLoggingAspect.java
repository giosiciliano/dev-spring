package com.gio.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	// all related advices for logging
	
	// @Before advice
	//@Before("execution(public void addAccount())")
	@Before("execution(* com.gio.aopdemo.dao.*.*(..))") // pointcut expression
	public void beforeAddAccountAdvice() {
		System.out.println("\n=====>>> Executing @Before advice on addAccount()");
	}

}
