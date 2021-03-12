package com.mbugajski.spring.aop;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mbugajski.spring.aop.dao.AccountDAO;
import com.mbugajski.spring.aop.dao.MembershipDAO;

public class AfterReturningApp {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
		
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		List<Account> theAccounts = theAccountDAO.findAccounts();
		
		System.out.println("Accounts: " + theAccounts);
		
		context.close();
	}

}
