package com.java.aop02;

import org.aspectj.lang.JoinPoint;

public class PASpect {
	public void awake(JoinPoint joinpoint) {
		System.out.println("일어 난다");
	}
	
	public void sleep(JoinPoint joinpoint) {
		System.out.println("잠을 잔다");
	}
}
