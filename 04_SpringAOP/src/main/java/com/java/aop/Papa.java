package com.java.aop;

public class Papa implements Person{

	@Override
	public void awake() {
		System.out.println("�ƺ��� ���");
	}

	@Override
	public void work() {
		System.out.println("�ƺ��� ȸ�����");
	}

	@Override
	public void sleep() {
		System.out.println("�ƺ��� �ڰ���");
	}
}
