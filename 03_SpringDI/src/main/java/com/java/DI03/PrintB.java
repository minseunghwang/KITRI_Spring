package com.java.DI03;

import org.springframework.stereotype.Component;

@Component("print-B")
public class PrintB implements Print{
	@Override
	public void print() {
		System.out.println("print B ¿‘¥œ¥Ÿ.");
	}
}
