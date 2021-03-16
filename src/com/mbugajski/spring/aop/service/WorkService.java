package com.mbugajski.spring.aop.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class WorkService {

	public String doWork(Boolean tripWire) {
		if (tripWire) {
			throw new RuntimeException("Can't be arsed today!");
		}
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "The work has been done.";
	}
	
}
