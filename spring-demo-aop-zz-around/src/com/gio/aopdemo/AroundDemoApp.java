package com.gio.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gio.aopdemo.service.TrafficFortuneService;

public class AroundDemoApp {
	
	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get bean from spring container
		TrafficFortuneService tfs = context.getBean("trafficFortuneService", 
				TrafficFortuneService.class);
		
		System.out.println("\nMain Program: AroundDemoApp");
		
		System.out.println("Calling getFortune");
		
		System.out.println("\nMy fortune is: " + tfs.getFortune());
		
		// close context
		context.close();
		
	}

}
