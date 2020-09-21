package com.java.aop;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/java/aop/config.xml");
		
		Person mom = ctx.getBean("mom", Person.class);
		mom.awake();
		mom.work();
		mom.sleep();
		
		System.out.println("=====================");
		
		Person papa = ctx.getBean("papa", Person.class);
		papa.awake();
		papa.work();
		papa.sleep();
		
		System.out.println("=====================");
		
		Person baby = ctx.getBean("baby", Person.class);
		baby.awake();
		baby.work();
		baby.sleep();
		
		System.out.println("=====================");
		
	}
}
