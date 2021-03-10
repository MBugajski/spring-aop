package com.mbugajski.spring.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(9001)
public class ApiAnalyticsAspect {
	
	@Before("execution(* callApi*())")
	public void beforeApiCall() {
		System.out.println("======>>> Executing @Before advice on API calls");
	}
}
