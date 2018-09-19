package com.gio.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	// logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// pointcut declarations
	@Pointcut("execution (* com.gio.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution (* com.gio.springdemo.service.*.*(..))")
	private void forServicePackage() {}
			
	@Pointcut("execution (* com.gio.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	// @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		
		// display method we are calling
		myLogger.info("=====>> in @Before: callling method: " + joinPoint.getSignature().toShortString());
		
		// display arguemts to the method
		
		// get arguments
		Object[] args = joinPoint.getArgs();
		
		// loop thru and display args
		for (Object object : args) {
			myLogger.info("=====>> argument: " + object);
		}
			
	}
	
	// @AfterRetruning advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="result") 
	public void after(JoinPoint joinPoint, Object result) {
		
		// display method we are returning from
		myLogger.info("=====>> in @AfterReturning: from method: " + joinPoint.getSignature().toShortString());
		
		// display data returned
		myLogger.info("=====>> result: " + result);
		
	}

}
