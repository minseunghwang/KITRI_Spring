package com.java.aop03;

import org.aspectj.lang.JoinPoint;

public class PAspect {
	
	public void open(JoinPoint joinpoint) {
		System.out.println("�����忡 ���´�.");
	}
	
	public void close(JoinPoint joinpoint) {
		System.out.println("������! �������� ������.");
	}
	
	public void error(JoinPoint joinPoint) {
		System.out.println("�ıǾ���! ���� ���´�.");
	}
	
	public void eat(JoinPoint joinPoint) {
		System.out.println("������ ���ְ� �Դ´�.");
	}
}
