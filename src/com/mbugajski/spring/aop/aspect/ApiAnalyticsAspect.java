package com.mbugajski.spring.aop.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(9001)
public class ApiAnalyticsAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Before("execution(* callApi*())")
	public void beforeApiCall() {
		myLogger.info("======>>> Executing @Before advice on API calls");
	}
}
