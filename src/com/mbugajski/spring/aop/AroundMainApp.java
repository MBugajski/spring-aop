package com.mbugajski.spring.aop;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mbugajski.spring.aop.service.WorkService;

public class AroundMainApp {
	
	private static Logger myLogger = Logger.getLogger(AroundMainApp.class.getName());
		
	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
		
		WorkService workService = context.getBean("workService", WorkService.class);
		myLogger.info("Setting out to do work.");
		myLogger.info(workService.doWork());
		
		context.close();
	}

}
