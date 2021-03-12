package com.mbugajski.spring.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(-252)
public class AccessorAspect {

	@Before("com.mbugajski.spring.aop.aspect.expressions.AopExpressions.forGettersAndSetters()")
	public void beforeGetterOrSetter() {
		System.out.println("======>>> Executing @Before advice on any getter or setter method");
	}
}


