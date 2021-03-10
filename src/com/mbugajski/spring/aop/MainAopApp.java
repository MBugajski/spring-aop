package com.mbugajski.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mbugajski.spring.aop.dao.AccountDAO;
import com.mbugajski.spring.aop.dao.MembershipDAO;

public class MainAopApp {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
		
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		theAccountDAO.addAccount();
		
		theAccountDAO.addAccount(new Account());
		
		theAccountDAO.addAccount(new Account(), true);
		
		theAccountDAO.doWork();
		
		theAccountDAO.setName("main");
		
		theAccountDAO.getName();
		
		theAccountDAO.callApi();
		
		theMembershipDAO.addMember();
		
		theMembershipDAO.goToSleep();
		
		context.close();
	}

}
