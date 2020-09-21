package com.java.aop;

public class Mom implements Person{

	@Override
	public void awake() {
		System.out.println("엄마의 기상");
	}

	@Override
	public void work() {
		System.out.println("엄마의 수업");
	}

	@Override
	public void sleep() {
		System.out.println("엄마의 수면");
	}

}
