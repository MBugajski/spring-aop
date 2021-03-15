package com.mbugajski.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mbugajski.spring.aop.service.WorkService;

public class AroundMainApp {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
		
		WorkService workService = context.getBean("workService", WorkService.class);
		System.out.println("Setting out to do work.");
		System.out.println(workService.doWork());
		
		context.close();
	}

}
