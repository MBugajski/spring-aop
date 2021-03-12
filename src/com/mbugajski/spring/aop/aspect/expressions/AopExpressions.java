package com.mbugajski.spring.aop.aspect.expressions;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {
	
	@Pointcut("execution(* com.mbugajski.spring.aop.dao.*.*(..))") 
	public void forDaoPackage() {}
	
	@Pointcut("execution (* get*())")
	public void getter() {}

	@Pointcut("execution(void set*(*))")
	public void setter() {}
	
	@Pointcut("getter() || setter()")
	public void forGettersAndSetters() {}

	@Pointcut("forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNoGetterOrSetter() {}
	
	
}
