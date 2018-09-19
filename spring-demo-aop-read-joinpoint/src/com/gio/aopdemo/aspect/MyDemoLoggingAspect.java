package com.gio.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
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
	
}
