package com.java.aop;

public class Mom implements Person{

	@Override
	public void awake() {
		System.out.println("������ ���");
	}

	@Override
	public void work() {
		System.out.println("������ ����");
	}

	@Override
	public void sleep() {
		System.out.println("������ ����");
	}

}
