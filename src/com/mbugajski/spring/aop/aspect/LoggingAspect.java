package com.mbugajski.spring.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.jdbc.core.support.AbstractInterruptibleBatchPreparedStatementSetter;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	@Pointcut("execution(* com.mbugajski.spring.aop.dao.*.*(..))") 
		private void forDaoPackage() {}
	
	@Pointcut("execution (* get*())")
		private void getter() {}
	
	@Pointcut("execution(void set*(*))")
		private void setter() {}
	
	@Pointcut("forDaoPackage() && !(getter() || setter())")
		private void forDaoPackageNoGetterOrSetter() {}
	
	@Pointcut("getter() || setter()")
		private void forGettersAndSetters() {}

	@Before("execution(public void add*())")
	public void beforeAddAccountAdvice() {
		
		System.out.println("======>>> Executing @Before adviceon addAccount()");
	}
	
	@Before("execution(* add*(com.mbugajski.spring.aop.Account))")
	public void beforeAddAccountWithParamAdvice() {
		
		System.out.println("======>>> Executing @Before advice on addAccount(Account)");
	}
	
	@Before("execution(* add*(com.mbugajski.spring.aop.Account, ..))")
	public void beforeAddAccountWithParamsAdvice() {
		
		System.out.println("======>>> Executing @Before advice on addAccount(Account, ..)");
	}
	
	@Before("execution(* com.mbugajski.spring.aop.dao.*.*(..))")
	public void beforeAnyMethodInThePackageAdvice() {
		
		System.out.println("======>>> Executing @Before advice on any method in chosen package");
	}
	
	@Before("forDaoPackage() && !(getter() || setter())")
//	@Before(forDaoPackageNoGetterOrSetter())
	public void beforeAnyMethodInThePackageAdviceWithDeclaration() {
		
		System.out.println("======>>> Executing @Before advice on any method in chosen package with declaration");
	}
	
	@Before("forGettersAndSetters()")
	public void beforeGetterOrSetter() {
		System.out.println("======>>> Executing @Before advice on any getter or setter method");
	}
}
