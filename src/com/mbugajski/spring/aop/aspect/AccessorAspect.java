package com.mbugajski.spring.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(-252)
public class AccessorAspect {

	@Pointcut("execution (* get*())")
	private void getter() {
	}

	@Pointcut("execution(void set*(*))")
	private void setter() {
	}
	
	@Pointcut("getter() || setter()")
	private void forGettersAndSetters() {}

	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterOrSetter() {
	}
	
	@Before("forGettersAndSetters()")
	public void beforeGetterOrSetter() {
		System.out.println("======>>> Executing @Before advice on any getter or setter method");
	}
}


