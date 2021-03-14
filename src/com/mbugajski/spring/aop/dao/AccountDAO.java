package com.mbugajski.spring.aop.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mbugajski.spring.aop.Account;

@Component
public class AccountDAO {
	
		private String name;
		
		
		public List<Account> findAccounts(boolean tripWire) {
			if (tripWire) {
				throw new RuntimeException("Simulated exception: testing @AfterThrowing");
			}
			
			List<Account> myAccounts = new ArrayList<>();
			
			Account temp1 = new Account("Adam", "Gold");
			Account temp2 = new Account("Mark", "Basic");
			Account temp3 = new Account("Tom", "Plantinum");
					
			myAccounts.add(temp1);
			myAccounts.add(temp2);
			myAccounts.add(temp3);
			
			return myAccounts;
		}


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
		
		public void callApi() {
			System.out.println(getName() + " API");
		}
}
