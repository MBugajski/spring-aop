package com.mbugajski.spring.aop;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mbugajski.spring.aop.dao.AccountDAO;

public class AfterThrowingMainApp {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
		
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		List<Account> theAccounts = null;
		
		try {
			boolean tripWire = true;
			theAccounts = theAccountDAO.findAccounts(tripWire);
		} catch (Exception e) {
			System.out.println("Cought exception: " + e);
		}
		
		System.out.println("Accounts: " + theAccounts);
		
		context.close();
	}

}
