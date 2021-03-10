package com.mbugajski.spring.aop.dao;

import org.springframework.stereotype.Component;

import com.mbugajski.spring.aop.Account;

@Component
public class AccountDAO {
	
		private String name;

		public void addAccount()	{
			System.out.println(getClass() + " doing my db work: adding an account");
		}
		
		public void addAccount(Account account) {
			System.out.println("adding account: " + account.getName());
		}
		
		public void addAccount(Account account, boolean flagVip) {
			System.out.println("adding account: " + account.getName() + " is VIP: " + flagVip);
		}
		
		public void doWork() {
			System.out.println("Doing work");
		}

		public String getName() {
			System.out.println(getClass() + " getName()");
			return name;
		}

		public void setName(String name) {
			System.out.println(getClass() + " setName()");
			this.name = name;
		}
		
		
}
