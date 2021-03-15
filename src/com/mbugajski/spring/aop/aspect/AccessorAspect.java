package com.mbugajski.spring.aop.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(-252)
public class AccessorAspect {

	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Before("com.mbugajski.spring.aop.aspect.expressions.AopExpressions.forGettersAndSetters()")
	public void beforeGetterOrSetter() {
		myLogger.info("======>>> Executing @Before advice on any getter or setter method");
	}
}


