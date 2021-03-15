package com.mbugajski.spring.aop.aspect;

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

import com.mbugajski.spring.aop.Account;

@Aspect
@Component
@Order(-5)
public class LoggingAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Before("execution(public void add*())")
	public void beforeAddAccountAdvice() {
		
		myLogger.info("======>>> Executing @Before advice on addAccount()");
	}
	
	@Before("execution(* add*(com.mbugajski.spring.aop.Account))")
	public void beforeAddAccountWithParamAdvice() {
		
		myLogger.info("======>>> Executing @Before advice on addAccount(Account)");
	}
	
	@Before("execution(* add*(com.mbugajski.spring.aop.Account, ..))")
	public void beforeAddAccountWithParamsAdvice(JoinPoint theJoinPoint) {
		
		myLogger.info("======>>> Executing @Before advice on addAccount(Account, ..)");
		
		MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
		
		myLogger.info("Method: " + methodSignature);
		
		Object[] args = theJoinPoint.getArgs();
		
		for (Object tempArg : args) {
			myLogger.info(tempArg.toString());
			
			if (tempArg instanceof Account) {
				
				//downcast and print Account class specific details
				Account theAccount = (Account) tempArg;
				
				myLogger.info("Account name: " + theAccount.getName());
				myLogger.info("Account level: " + theAccount.getLevel());
			}
		}
	}
	
	@Before("execution(* com.mbugajski.spring.aop.dao.*.*(..))")
	public void beforeAnyMethodInThePackageAdvice() {
		
		myLogger.info("======>>> Executing @Before advice on any method in chosen package");
	}
	
	@Before("com.mbugajski.spring.aop.aspect.expressions.AopExpressions.forDaoPackageNoGetterOrSetter()")
	public void beforeAnyMethodInThePackageAdviceWithDeclaration() {
		
		myLogger.info("======>>> Executing @Before advice on any method in chosen package with declaration");
	}
	
	@AfterReturning(
			pointcut = "execution(* com.mbugajski.spring.aop.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdvice(List<Account> result) {
		
		myLogger.info("======>>> Executing @AfterReturning advice on AccountDAO.findAccounts() method");
		myLogger.info("======>>> result is: " + result);
	}
	@AfterReturning(
			pointcut = "execution(* com.mbugajski.spring.aop.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdviceWithJoinPointMetadata(JoinPoint theJoinPoint, List<Account> result) {
		
		myLogger.info("======>>> Executing @AfterReturning advice on " + theJoinPoint.getSignature().toShortString());
		myLogger.info("======>>> result is: " + result);
	}
	
	@AfterReturning(
			pointcut = "execution(* com.mbugajski.spring.aop.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdviceWithPostprocessing(JoinPoint theJoinPoint, List<Account> result) {
		
		myLogger.info("======>>> Executing @AfterReturning advice on " + theJoinPoint.getSignature().toShortString());
		myLogger.info("======>>> result is: " + result);
		
		for(Account tempAccount : result) {
			tempAccount.setName(tempAccount.getName().toUpperCase());
		}
		myLogger.info("======>>> Post-processed result is: " + result);
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.mbugajski.spring.aop.dao.AccountDAO.findAccounts(..))",
			throwing = "e")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable e) {
		
		myLogger.info("======>>> Executing @AfterThrowing advice on " + theJoinPoint.getSignature().toShortString());
		myLogger.info("======>>> Exception occured: " + e);
	}
	
	@After("execution(* com.mbugajski.spring.aop.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		myLogger.info("======>>> Executing @After (Finally) advice on " + theJoinPoint.getSignature().toShortString());
	}
	
	@Around("execution(* com.mbugajski.spring.aop.service.*.doWork(..))")
	public Object aroundDoWorkAdvice(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{
		
		myLogger.info("======>>> Executing @Around advice on " + theProceedingJoinPoint.getSignature().toShortString());
		
		long begin = System.currentTimeMillis();
		
		Object result = theProceedingJoinPoint.proceed();
		
		long end = System.currentTimeMillis();
		
		myLogger.info("======>>> Duration: " + (end - begin)/1000.0 + " seconds."); 
		
		return result;
	}
}

