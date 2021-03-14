package com.mbugajski.spring.aop;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mbugajski.spring.aop.dao.AccountDAO;
import com.mbugajski.spring.aop.dao.MembershipDAO;

public class AfterReturningMainApp {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
		
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		List<Account> theAccounts = theAccountDAO.findAccounts(false);
		
		System.out.println("Accounts: " + theAccounts);
		
		context.close();
	}

}
