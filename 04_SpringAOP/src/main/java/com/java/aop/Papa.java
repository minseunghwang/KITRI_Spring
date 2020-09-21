package com.java.aop;

public class Papa implements Person{

	@Override
	public void awake() {
		System.out.println("아빠의 기상");
	}

	@Override
	public void work() {
		System.out.println("아빠의 회사출근");
	}

	@Override
	public void sleep() {
		System.out.println("아빠의 코골이");
	}
}
