package com.java.aop02;

import org.aspectj.lang.JoinPoint;

public class PASpect {
	public void awake(JoinPoint joinpoint) {
		System.out.println("�Ͼ� ����");
	}
	
	public void sleep(JoinPoint joinpoint) {
		System.out.println("���� �ܴ�");
	}
}
