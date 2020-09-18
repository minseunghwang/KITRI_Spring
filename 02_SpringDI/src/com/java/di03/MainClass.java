package com.java.di03;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("appCTX.xml");
		
		Sub sub = (Sub) ctx.getBean("sub");
		System.out.println("�����ڸ� �̿� : " + sub.toString());
				
		Sub sub2 = ctx.getBean("sub2", Sub.class);
		System.out.println("Setter�̿� : " + sub2.toString());
		
		ctx.close();

	}
}
