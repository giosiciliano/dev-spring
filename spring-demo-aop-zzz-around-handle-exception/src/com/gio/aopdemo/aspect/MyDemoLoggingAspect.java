package com.gio.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// all related advices for logging
	
	// @Before advice
	//@Before("execution(public void addAccount())") // pointcut expression
	@Before("com.gio.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()") 
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {
		myLogger.info("\n=====>>> Executing @Before advice on method");
		
		// display method signature
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		myLogger.info("Method: " + methodSig);
		
		// display method arguments
		
		// get args
		Object[] args = joinPoint.getArgs();
		
		// loop args
		for (Object object : args) {
			myLogger.info(object.toString());
			
			if (object instanceof Account) {
				// downcast and print Account specific elements
				Account acc = (Account)object;
				myLogger.info("Account name: " + acc.getName());
				myLogger.info("Account level: " + acc.getLevel());
			}
		}
	}
	
	// after returning advice on findAccounts method
	@AfterReturning(
			pointcut="execution(* com.gio.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
		// print method we are advising on
		myLogger.info("\n=====>>> Executing"
				+ " @AfterReturning on method: " + joinPoint.getSignature().toShortString());
		
		// post-process the data (modify before returning to calling program)
		
		// print results of method call
		myLogger.info("\n=====>>> result is: " + result);
		
		// convert account names to uppercase
		convertAccountNamesToUpperCase(result);
		
		// print results of method call
		myLogger.info("\n=====>>> result is: " + result);
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {
		
		for (Account account : result) {
			account.setName(account.getName().toUpperCase());
		}
		
	}
	
	// after throwing advice on findAccounts method
	@AfterThrowing(
			pointcut="execution(* com.gio.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="exc")
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint,
			Throwable exc) {
		
		// print method advising on
		myLogger.info("\n=====>>> Executing"
				+ " @AfterThrowing on method: " + joinPoint.getSignature().toShortString());
		
		// log the exception
		myLogger.info("\n=====>>> The exception is: " + exc);
		
	}
	
	// after advice on findAccounts method
	@After("execution(* com.gio.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
		
		myLogger.info("\n=====>>> Executing @After (finally) on method: "
				+ joinPoint.getSignature());
	}
	
	// around advice for getFortune
	@Around("execution(* com.gio.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(
			ProceedingJoinPoint preJoinPoint) throws Throwable {

		// print out method
		myLogger.info("\n=====>>> Executing @Around on method: "
				+ preJoinPoint.getSignature());
		
		// get begin tstmp
		long begin = System.currentTimeMillis();
		
		// execute method
		Object result = null;
		
		try {
			result = preJoinPoint.proceed();
		} catch (Exception e) {
			// log exception
			myLogger.warning(e.getMessage());
			
			/* give user custom message
			result = "Major accident! But no worries, your "
					+ "private AOP helicopter is on the way!";
			*/
			throw e;
		}

		
		// get end tstmp
		long end = System.currentTimeMillis();
		
		// compute/display duration
		long duration = end - begin;
		myLogger.info("\n=====>>> Duration: " + duration / 1000.0 + " seconds");
		
		return result;
	}
	
}
