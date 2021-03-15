package com.mbugajski.spring.aop.aspect;

import java.util.List;

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
	
	@Before("com.mbugajski.spring.aop.aspect.expressions.AopExpressions.forDaoPackageNoGetterOrSetter()")
	public void beforeAnyMethodInThePackageAdviceWithDeclaration() {
		
		System.out.println("======>>> Executing @Before advice on any method in chosen package with declaration");
	}
	
	@AfterReturning(
			pointcut = "execution(* com.mbugajski.spring.aop.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdvice(List<Account> result) {
		
		System.out.println("======>>> Executing @AfterReturning advice on AccountDAO.findAccounts() method");
		System.out.println("======>>> result is: " + result);
	}
	@AfterReturning(
			pointcut = "execution(* com.mbugajski.spring.aop.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdviceWithJoinPointMetadata(JoinPoint theJoinPoint, List<Account> result) {
		
		System.out.println("======>>> Executing @AfterReturning advice on " + theJoinPoint.getSignature().toShortString());
		System.out.println("======>>> result is: " + result);
	}
	
	@AfterReturning(
			pointcut = "execution(* com.mbugajski.spring.aop.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdviceWithPostprocessing(JoinPoint theJoinPoint, List<Account> result) {
		
		System.out.println("======>>> Executing @AfterReturning advice on " + theJoinPoint.getSignature().toShortString());
		System.out.println("======>>> result is: " + result);
		
		for(Account tempAccount : result) {
			tempAccount.setName(tempAccount.getName().toUpperCase());
		}
		System.out.println("======>>> Post-processed result is: " + result);
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.mbugajski.spring.aop.dao.AccountDAO.findAccounts(..))",
			throwing = "e")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable e) {
		
		System.out.println("======>>> Executing @AfterThrowing advice on " + theJoinPoint.getSignature().toShortString());
		System.out.println("======>>> Exception occured: " + e);
	}
	
	@After("execution(* com.mbugajski.spring.aop.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		System.out.println("======>>> Executing @After (Finally) advice on " + theJoinPoint.getSignature().toShortString());
	}
	
	@Around("execution(* com.mbugajski.spring.aop.service.*.doWork(..))")
	public Object aroundDoWorkAdvice(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{
		
		System.out.println("======>>> Executing @Around advice on " + theProceedingJoinPoint.getSignature().toShortString());
		
		long begin = System.currentTimeMillis();
		
		Object result = theProceedingJoinPoint.proceed();
		
		long end = System.currentTimeMillis();
		
		System.out.println("======>>> Duration: " + (end - begin)/1000.0 + " seconds."); 
		
		return result;
	}
}

