package com.mbugajski.spring.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(-5)
public class LoggingAspect {
	
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
	
//	@Before("forDaoPackage() && !(getter() || setter())")
	@Before("com.mbugajski.spring.aop.aspect.expressions.AopExpressions.forDaoPackageNoGetterOrSetter()")
	public void beforeAnyMethodInThePackageAdviceWithDeclaration() {
		
		System.out.println("======>>> Executing @Before advice on any method in chosen package with declaration");
	}
}

