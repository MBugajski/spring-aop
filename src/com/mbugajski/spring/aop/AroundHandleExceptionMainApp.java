package com.mbugajski.spring.aop;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mbugajski.spring.aop.service.WorkService;

public class AroundHandleExceptionMainApp {
	
	private static Logger myLogger = Logger.getLogger(AroundHandleExceptionMainApp.class.getName());
		
	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
		
		WorkService workService = context.getBean("workService", WorkService.class);
		myLogger.info("Setting out to do work.");
		myLogger.info(workService.doWork(true));
		
		context.close();
	}

}
