package com.java.di01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("appCTX.xml");
		Su su = (Su) ctx.getBean("su");		// 여기에 그 appCTX.xml의 id값이 들어가는군("su")
		su.disp();
		
		ctx.close();		// 이 아이는 반드시 닫아줘야 한다.
			
	}	
}


// 클래스가 막 40개 되 그걸 다 new 때릴기 번거로움 -> 그래서 .xml에 다 넣어놨다가 이런식으로 불러와 사용하기 위해 스프링 등장