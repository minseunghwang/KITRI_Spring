package com.java.aop;

public class Baby implements Person{

	@Override
	public void awake() {
		System.out.println("�ῡ�� ����");
	}

	@Override
	public void work() {
		System.out.println("��ġ�� �");
	}

	@Override
	public void sleep() {
		System.out.println("�Ʊ��� ����");
	}

}
