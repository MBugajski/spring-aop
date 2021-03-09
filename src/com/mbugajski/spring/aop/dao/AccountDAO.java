package com.mbugajski.spring.aop.dao;

import org.springframework.stereotype.Component;

import com.mbugajski.spring.aop.Account;

@Component
public class AccountDAO {

		public void addAccount()	{
			System.out.println(getClass() + " doing my db work: adding an account");
		}
		
		public void addAccount(Account account) {
			System.out.println("adding account: " + account.getName());
		}
}
