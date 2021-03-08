package com.mbugajski.spring.aop.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public void addMember()	{
		System.out.println(getClass() + " doing my db work: adding a membership");
	}
}
