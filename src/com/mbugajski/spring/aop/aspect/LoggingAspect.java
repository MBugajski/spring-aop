package com.mbugajski.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mbugajski.spring.aop.Account;

@Aspect
@Component
@Order(-5)
public class LoggingAspect {
	
	@Before("execution(public void add*())")
	public void beforeAddAccountAdvice() {
		
		System.out.println("======>>> Executing @Before advice on addAccount()");
	}
	
	@Before("execution(* add*(com.mbugajski.spring.aop.Account))")
	public void beforeAddAccountWithParamAdvice() {
		
		System.out.println("======>>> Executing @Before advice on addAccount(Account)");
	}
	
	@Before("execution(* add*(com.mbugajski.spring.aop.Account, ..))")
	public void beforeAddAccountWithParamsAdvice(JoinPoint theJoinPoint) {
		
		System.out.println("======>>> Executing @Before advice on addAccount(Account, ..)");
		
		MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
		
		System.out.println("Method: " + methodSignature);
		
		Object[] args = theJoinPoint.getArgs();
		
		for (Object tempArg : args) {
			System.out.println(tempArg);
			
			if (tempArg instanceof Account) {
				
				//downcast and print Account class specific details
				Account theAccount = (Account) tempArg;
				
				System.out.println("Account name: " + theAccount.getName());
				System.out.println("Account level: " + theAccount.getLevel());
			}
		}
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

