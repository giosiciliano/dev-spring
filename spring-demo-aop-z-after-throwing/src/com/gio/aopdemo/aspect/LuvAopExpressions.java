package com.gio.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAopExpressions {

		// pointcut declarations
		@Pointcut("execution(* com.gio.aopdemo.dao.*.*(..))")
		public void forDaoPackage() {}
		
		// create pointcut for getter
		@Pointcut("execution(* com.gio.aopdemo.dao.*.get*(..))")
		public void getter() {}
		
		// create pointcut for setter
		@Pointcut("execution(* com.gio.aopdemo.dao.*.set*(..))")
		public void setter() {}
		
		// create pointcut: include package...exclude getter/setter
		@Pointcut("forDaoPackage() && !(getter() || setter())")
		public void forDaoPackageNoGetterSetter() {}
}
