package com.java.aop02;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/java/aop02/config.xml");
		
		Person mom = ctx.getBean("mom", Person.class);
		mom.work();
		
		System.out.println("=====================");
		
		Person papa = ctx.getBean("papa", Person.class);
		papa.work();
		
		System.out.println("=====================");
		
		Person baby = ctx.getBean("baby", Person.class);
		baby.work();
		
		System.out.println("=====================");
		
	}
}
