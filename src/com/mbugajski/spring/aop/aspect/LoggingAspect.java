package com.mbugajski.spring.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Before("execution(public void add*())")
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n======>>> Executing @Before adviceon addAccount()");
	}
	
	@Before("execution(* add*(com.mbugajski.spring.aop.Account))")
	public void beforeAddAccountWithParamAdvice() {
		
		System.out.println("\n======>>> Executing @Before advice on addAccount(Account)");
	}
}
