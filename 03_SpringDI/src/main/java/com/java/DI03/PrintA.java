package com.java.DI03;

import org.springframework.stereotype.Component;

@Component("print-A")
public class PrintA implements Print{
	@Override
	public void print() {
		System.out.println("print A ¿‘¥œ¥Ÿ.");
	}
}
